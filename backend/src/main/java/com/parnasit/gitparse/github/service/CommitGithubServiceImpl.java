package com.parnasit.gitparse.github.service;

import com.parnasit.gitparse.core.dto.CommitResponse;
import com.parnasit.gitparse.core.service.git.CommitGitService;
import com.parnasit.gitparse.github.dto.GithubCommitResponse;
import com.parnasit.gitparse.github.mapper.GitHubCommitMapper;
import com.parnasit.gitparse.github.service.util.GithubApiRequestCreator;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommitGithubServiceImpl extends GitHubService implements CommitGitService<CommitResponse> {

    private final GitHubCommitMapper commitGithubMapper;

    public CommitGithubServiceImpl(GithubApiRequestCreator githubApiRequestCreator, GitHubCommitMapper commitGithubMapper) {
        super(githubApiRequestCreator);
        this.commitGithubMapper = commitGithubMapper;
    }

    @Override
    public List<CommitResponse> getRepositoryCommits(String owner, String repo, Pageable pageable) {
        return commitGithubMapper.mapToListCoreCommitResponse(
                githubApiRequestCreator.sendRequestForList(
                        "/repos/%s/%s/commits".formatted(owner, repo),
                        GithubCommitResponse.class,
                        HttpMethod.GET,
                        pageableToQueryParams(pageable)
                )
        );
    }

    @Override
    public CommitResponse getCommit(String owner, String repo, String sha) {
        return commitGithubMapper.mapToCoreCommitResponse(
                githubApiRequestCreator.sendRequestForObject(
                        "/repos/%s/%s/commits/%s".formatted(owner, repo, sha),
                        GithubCommitResponse.class
                )
        );
    }

    @Override
    public CommitResponse compareCommits(String owner, String repo, String base, String head) {
        return commitGithubMapper.mapToCoreCommitResponse(
                githubApiRequestCreator.sendRequestForObject(
                        "/repos/%s/%s/compare/%s...%s".formatted(owner, repo, base, head),
                        GithubCommitResponse.class
                )
        );
    }
}
