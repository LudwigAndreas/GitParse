package com.parnasit.gitparse.github.service;

import com.parnasit.gitparse.core.dto.BranchResponse;
import com.parnasit.gitparse.core.service.git.BranchGitService;
import com.parnasit.gitparse.github.dto.GithubBranchResponse;
import com.parnasit.gitparse.github.mapper.GitHubBranchMapper;
import com.parnasit.gitparse.github.service.util.GithubApiRequestCreator;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchGithubServiceImpl extends GitHubService implements BranchGitService<BranchResponse> {

    private final GitHubBranchMapper branchGithubMapper;

    public BranchGithubServiceImpl(GithubApiRequestCreator githubApiRequestCreator, GitHubBranchMapper branchGithubMapper) {
        super(githubApiRequestCreator);
        this.branchGithubMapper = branchGithubMapper;
    }

    @Override
    public List<BranchResponse> getRepositoryBranches(String owner, String repo, Pageable pageable) {
        return branchGithubMapper.mapToCoreBranchResponseList(
                githubApiRequestCreator.sendRequestForList(
                        "/repos/%s/%s/branches".formatted(owner, repo),
                        GithubBranchResponse.class,
                        HttpMethod.GET,
                        pageableToQueryParams(pageable)
                )
        );
    }

    @Override
    public BranchResponse getRepositoryBranch(String owner, String repo, String branch) {
        return branchGithubMapper.mapToCoreBranchResponse(
                githubApiRequestCreator.sendRequestForObject(
                        "/repos/%s/%s/branches/%s".formatted(owner, repo, branch),
                        GithubBranchResponse.class
                )
        );
    }
}
