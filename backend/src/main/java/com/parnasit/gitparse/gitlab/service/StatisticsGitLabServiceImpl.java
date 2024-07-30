package com.parnasit.gitparse.gitlab.service;

import com.parnasit.gitparse.core.dto.StatisticsResponse;
import com.parnasit.gitparse.core.security.oauth2.OAuth2AccessTokenService;
import com.parnasit.gitparse.core.service.git.StatisticsGitService;
import com.parnasit.gitparse.gitlab.dto.CommitGitlabClientResponse;
import com.parnasit.gitparse.gitlab.exception.GitlabApiException;
import com.parnasit.gitparse.gitlab.mapper.GitLabStatisticsMapper;
import com.parnasit.gitparse.gitlab.service.util.GitlabApiRequestCreator;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;

@Service
public class StatisticsGitLabServiceImpl extends GitLabService
        implements StatisticsGitService<StatisticsResponse> {

    private final GitLabStatisticsMapper gitLabStatisticsMapper;
    private final OAuth2AccessTokenService tokenService;

    public StatisticsGitLabServiceImpl(
            GitlabApiRequestCreator gitlabApiRequestCreator,
            GitLabStatisticsMapper gitLabStatisticsMapper,
            OAuth2AccessTokenService tokenService) {
        super(gitlabApiRequestCreator);
        this.gitLabStatisticsMapper = gitLabStatisticsMapper;
        this.tokenService = tokenService;
    }

    private Flux<CommitGitlabClientResponse> getAsyncRepositoryStatistics(
            String owner, String repo, Pageable pageable, HttpHeaders headers) {
        return gitlabApiRequestCreator.getWebClient().method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder
                        .path("/projects/%s/%s/commits".formatted(owner, repo))
                        .queryParam("per_page", pageable.getPageSize())
                        .queryParam("page", pageable.getPageNumber() + 1)
                        .build())
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() ||
                                status.is5xxServerError(),
                        clientResponse -> clientResponse
                                .bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(
                                        new GitlabApiException(
                                                "Error fetching from GitLab: " +
                                                        errorBody)))
                )
                .bodyToFlux(CommitGitlabClientResponse.class);
    }

    private Mono<CommitGitlabClientResponse> getAsyncSingleCommit(String owner,
                                                                  String repo,
                                                                  HttpHeaders headers,
                                                                  String sha) {
        return gitlabApiRequestCreator.getWebClient().method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder
                        .path("/projects/%s/%s/repository/commits/%s".formatted(
                                owner, repo, sha))
                        .build())
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() ||
                                status.is5xxServerError(),
                        clientResponse -> clientResponse
                                .bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(
                                        new GitlabApiException(
                                                "Error fetching from GitLab: " +
                                                        errorBody)))
                )
                .bodyToMono(CommitGitlabClientResponse.class);
    }

    private Flux<StatisticsResponse> getFilledStatistics(String owner,
                                                         String repository,
                                                         Pageable pageable,
                                                         HttpHeaders headers) {
        return getAsyncRepositoryStatistics(owner, repository, pageable,
                headers)
                .flatMap(commit -> getAsyncSingleCommit(owner, repository,
                        headers, commit.getId())
                        .map(gitLabStatisticsMapper::mapToStatisticsResponse)
                );
    }

    @Override
    public List<StatisticsResponse> getRepositoryStatistics(String owner,
                                                            String repository,
                                                            Pageable pageable) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION,
                "Bearer " + tokenService.getAccessToken("gitlab"));

        if (pageable.getPageSize() > 100) {
            throw new IllegalArgumentException(
                    "Page size cannot be greater than 100");
        } else if (pageable.getPageNumber() < 0) {
            throw new IllegalArgumentException(
                    "Page number cannot be less than 0");
        }

        return getFilledStatistics(owner, repository, pageable,
                headers).collectList().block();
    }

    @Override
    public List<StatisticsResponse> getRepositoryStatisticsByBranch(
            String owner, String repository, Pageable pageable, String branch) {
        return List.of();
    }
}
