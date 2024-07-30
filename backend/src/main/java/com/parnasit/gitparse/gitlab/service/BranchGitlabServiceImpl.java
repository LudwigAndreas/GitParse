package com.parnasit.gitparse.gitlab.service;

import com.parnasit.gitparse.core.dto.BranchResponse;
import com.parnasit.gitparse.core.service.git.BranchGitService;
import com.parnasit.gitparse.gitlab.dto.BranchGitlabClientResponse;
import com.parnasit.gitparse.gitlab.mapper.GitLabBranchMapper;
import com.parnasit.gitparse.gitlab.service.util.GitlabApiRequestCreator;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchGitlabServiceImpl extends GitLabService implements BranchGitService<BranchResponse> {

    private final GitLabBranchMapper gitLabBranchMapper;

    public BranchGitlabServiceImpl(GitlabApiRequestCreator gitlabApiRequestCreator, GitLabBranchMapper gitLabBranchMapper) {
        super(gitlabApiRequestCreator);
        this.gitLabBranchMapper = gitLabBranchMapper;
    }

    @Override
    public List<BranchResponse> getRepositoryBranches(String owner, String repo, Pageable pageable) {
        return gitLabBranchMapper.mapToCoreBranchResponseList(
                gitlabApiRequestCreator.sendRequestForList(
                        "/repos/%s/%s/branches".formatted(owner, repo),
                        BranchGitlabClientResponse.class,
                        HttpMethod.GET,
                        pageableToQueryParams(pageable)
                )
        );
    }

    @Override
    public BranchResponse getRepositoryBranch(String owner, String repo, String branch) {
        return gitLabBranchMapper.mapToCoreBranchResponse(
                gitlabApiRequestCreator.sendRequestForObject(
                        "/repos/%s/%s/branches/%s".formatted(owner, repo, branch),
                        BranchGitlabClientResponse.class
                )
        );
    }

    //    @Override
//    public List<BranchGitlabClientResponse> getBranchesByProjectId(Long projectId) {
//        return gitlabApiRequestCreator.sendRequestForList(
//                        "/projects/%d/repository/branches".formatted(projectId),
//                        BranchGitlabClientResponse.class);
//    }
//
//    @Override
//    public BranchGitlabClientResponse getBranchByProjectIdAndBranchId(Long projectId, String branchName) {
//        return gitlabApiRequestCreator.sendRequestForObject(
//                        "/projects/%d/repository/branches/%s".formatted(projectId, branchName),
//                        BranchGitlabClientResponse.class);
//
//    }

}
