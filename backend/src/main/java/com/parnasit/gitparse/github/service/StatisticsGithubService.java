package com.parnasit.gitparse.github.service;

import com.parnasit.gitparse.core.dto.StatisticsResponse;
import com.parnasit.gitparse.core.security.oauth2.OAuth2AccessTokenService;
import com.parnasit.gitparse.core.service.git.StatisticsGitService;
import com.parnasit.gitparse.github.dto.GithubCommitResponse;
import com.parnasit.gitparse.github.exception.GitHubApiException;
import com.parnasit.gitparse.github.mapper.GitHubStatisticsMapper;
import com.parnasit.gitparse.github.service.util.GithubApiRequestCreator;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class StatisticsGithubService extends GitHubService implements StatisticsGitService<StatisticsResponse> {

    private final GitHubStatisticsMapper gitHubStatisticsMapper;
    private final OAuth2AccessTokenService tokenService;


    public StatisticsGithubService(GithubApiRequestCreator githubApiRequestCreator, GitHubStatisticsMapper gitHubStatisticsMapper, OAuth2AccessTokenService tokenService) {
        super(githubApiRequestCreator);
        this.gitHubStatisticsMapper = gitHubStatisticsMapper;
        this.tokenService = tokenService;
    }

    private Flux<GithubCommitResponse> getAsyncRepositoryStatistics(String owner, String repository, Pageable pageable, HttpHeaders headers) {
        return githubApiRequestCreator.getWebClient().method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder
                        .path("/repos/%s/%s/commits".formatted(owner, repository))
                        .queryParam("per_page", pageable.getPageSize())
                        .queryParam("page", pageable.getPageNumber())
                        .build())
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        clientResponse -> clientResponse
                                .bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new GitHubApiException("Error fetching from Git: " + errorBody)))
                )
                .bodyToFlux(GithubCommitResponse.class);
    }

    private Mono<GithubCommitResponse> getAsyncSingleCommit(String owner, String repository, String sha, HttpHeaders headers) {
        return githubApiRequestCreator.getWebClient().method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder
                        .path("/repos/%s/%s/commits/%s".formatted(owner, repository, sha))
                        .build())
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        clientResponse -> clientResponse
                                .bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new GitHubApiException("Error fetching from Git: " + errorBody)))
                )
                .bodyToMono(GithubCommitResponse.class);
    }

    private Flux<StatisticsResponse> getFilledStatistics(String owner, String repository, Pageable pageable, HttpHeaders headers) {
        return getAsyncRepositoryStatistics(owner, repository, pageable, headers)
                .flatMap(commit -> getAsyncSingleCommit(owner, repository, commit.getSha(), headers)
                        .map(gitHubStatisticsMapper::mapToStatistics)
                );
    }

    @Override
    public List<StatisticsResponse> getRepositoryStatistics(String owner, String repository, Pageable pageable) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + tokenService.getAccessToken("github"));
        tokenService.getAccessToken("github");

        if (pageable.getPageSize() > 100) {
            throw new IllegalArgumentException("Page size cannot be greater than 100");
        } else if (pageable.getPageNumber() < 0) {
            throw new IllegalArgumentException("Page number cannot be less than 0");
        }

        return getFilledStatistics(owner, repository, pageable, headers).collectList().block();

    }

    @Override
    public List<StatisticsResponse> getRepositoryStatisticsByBranch(String owner, String repository, Pageable pageable, String branch) {
        return null;
    }
}
