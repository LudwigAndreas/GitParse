package com.parnasit.gitparse.gitlab.service;


import com.parnasit.gitparse.core.dto.CommitResponse;
import com.parnasit.gitparse.core.service.git.CommitGitService;
import com.parnasit.gitparse.gitlab.dto.CommitGitlabClientResponse;
import com.parnasit.gitparse.gitlab.mapper.GitLabCommitMapper;
import com.parnasit.gitparse.gitlab.service.GitLabService;
import com.parnasit.gitparse.gitlab.service.util.GitlabApiRequestCreator;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommitGitlabServiceImpl extends GitLabService implements CommitGitService<CommitResponse> {

    private final GitLabCommitMapper commitGitlabMapper;

    public CommitGitlabServiceImpl(GitlabApiRequestCreator gitlabApiRequestCreator, GitLabCommitMapper commitGitlabMapper) {
        super(gitlabApiRequestCreator);
        this.commitGitlabMapper = commitGitlabMapper;
    }

    @Override
    public List<CommitResponse> getRepositoryCommits(String owner, String repo, Pageable pageable) {
        return commitGitlabMapper.mapToListCoreCommitResponse(gitlabApiRequestCreator.sendRequestForList("/repos/%s/%s/commits".formatted(owner, repo), CommitGitlabClientResponse.class, HttpMethod.GET, pageableToQueryParams(pageable)));
    }

    @Override
    public CommitResponse getCommit(String owner, String repo, String sha) {
        return commitGitlabMapper.mapToCoreCommitResponse(gitlabApiRequestCreator.sendRequestForObject("/repos/%s/%s/commits/%s".formatted(owner, repo, sha), CommitGitlabClientResponse.class));
    }

    @Override
    public CommitResponse compareCommits(String owner, String repo, String base, String head) {
        return commitGitlabMapper.mapToCoreCommitResponse(gitlabApiRequestCreator.sendRequestForObject("/repos/%s/%s/compare/%s...%s".formatted(owner, repo, base), CommitGitlabClientResponse.class));
    }

    //    @Override
//    public List<CommitGitlabClientResponse> getCommitsByProjectId(Long projectId) {
//        return gitlabApiRequestCreator.sendRequestForList(
//                "/projects/%d/repository/commits".formatted(projectId),
//                CommitGitlabClientResponse.class);
//    }
//
//    @Override
//    public CommitGitlabClientResponse getCommitByProjectIdAndCommitId(Long projectId, String sha) {
//        return gitlabApiRequestCreator.sendRequestForObject(
//                "/projects/%d/repository/commits/%s".formatted(projectId, sha),
//                CommitGitlabClientResponse.class);
//    }
//
//    @Override
//    public List<CommitDiffGitlabClientResponse> getCommitDiffByProjectIdAndCommitId(Long projectId, String sha) {
//        return gitlabApiRequestCreator.sendRequestForList(
//                "/projects/%d/repository/commits/%s/diff".formatted(projectId, sha),
//                CommitDiffGitlabClientResponse.class);
//    }

}
