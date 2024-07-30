package com.parnasit.gitparse.github.service;

import com.parnasit.gitparse.core.dto.IssueResponse;
import com.parnasit.gitparse.core.service.git.IssueGitService;
import com.parnasit.gitparse.github.service.util.GithubApiRequestCreator;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssuesGithubServiceImpl extends GitHubService implements IssueGitService<IssueResponse> {

//    private final GitHubIssueMapper issueGithubMapper;

    public IssuesGithubServiceImpl(GithubApiRequestCreator githubApiRequestCreator) {
        super(githubApiRequestCreator);
    }

    @Override
    public List<IssueResponse> getUserIssues(String username, Pageable pageable) {
        return null;
    }

    @Override
    public List<IssueResponse> getRepositoryIssues(String owner, String repo, Pageable pageable) {
        return null;
    }

    @Override
    public IssueResponse getIssue(String owner, String repo, String id) {
        return null;
    }

//    @Override
//    public List<GithubIssueResponse> getIssuesByRepository(String owner, String repo) {
//        return githubApiRequestCreator.sendRequestForList(
//                "/repos/%s/%s/issues".formatted(owner,repo),
//                GithubIssueResponse.class);
//    }
//
//    @Override
//    public List<GithubIssueResponse> getIssuesByAuthor(String owner, String repo, String author) {
//        return githubApiRequestCreator.sendRequestForList(
//                "/repos/%s/%s/issues".formatted(owner, repo),
//                GithubIssueResponse.class,
//                HttpMethod.GET,
//                Map.of(
//                        "creator", author
//                ));
//
//    }
//
//    @Override
//    public List<GithubIssueResponse> getIssuesByState(String owner, String repo, String state) {
//        return githubApiRequestCreator.sendRequestForList(
//                "/repos/%s/%s/issues".formatted(owner, repo),
//                GithubIssueResponse.class,
//                HttpMethod.GET,
//                Map.of(
//                        "state", state
//                ));
//    }
//
//    @Override
//    public List<GithubIssueResponse> getIssuesByAssignee(String owner, String repo, String assignee) {
//        return githubApiRequestCreator.sendRequestForList(
//                "/repos/%s/%s/issues".formatted(owner, repo),
//                GithubIssueResponse.class,
//                HttpMethod.GET,
//                Map.of(
//                        "assignee", assignee
//                ));
//    }
//
//    @Override
//    public GithubIssueResponse getRepositoryIssue(String owner, String repo, Long issueNumber) {
//        return githubApiRequestCreator.sendRequestForObject(
//                "/repos/%s/%s/issues/%d".formatted(owner,repo,issueNumber),
//                GithubIssueResponse.class
//        );
//    }
}
