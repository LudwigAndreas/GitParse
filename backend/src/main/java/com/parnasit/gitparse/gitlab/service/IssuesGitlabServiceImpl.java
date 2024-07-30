package com.parnasit.gitparse.gitlab.service;

import com.parnasit.gitparse.core.service.util.ApiRequestCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IssuesGitlabServiceImpl {

    private final ApiRequestCreator gitlabApiRequestCreator;

//    @Override
//    public List<IssueGitlabClientResponse> getIssuesByProject(Long projectId) {
//        return gitlabApiRequestCreator.sendRequestForList(
//                "/projects/%d/issues".formatted(projectId),
//                IssueGitlabClientResponse.class);
//    }
//
//    @Override
//    public List<IssueGitlabClientResponse> getIssuesByAuthorId(Long projectId, Long authorId) {
//        return gitlabApiRequestCreator.sendRequestForList(
//                "/projects/%d/issues".formatted(projectId),
//                IssueGitlabClientResponse.class,
//                HttpMethod.GET,
//                Map.of(
//                        "author_id", String.valueOf(authorId)
//                ));
//    }
//
//    @Override
//    public List<IssueGitlabClientResponse> getIssuesByState(Long projectId, String state) {
//        return gitlabApiRequestCreator.sendRequestForList(
//                "/projects/%d/issues".formatted(projectId),
//                IssueGitlabClientResponse.class,
//                HttpMethod.GET,
//                Map.of(
//                        "state", String.valueOf(state)
//                ));
//    }
//
//    @Override
//    public List<IssueGitlabClientResponse> getIssuesByAssigneeId(Long projectId, Long assigneeId) {
//        return gitlabApiRequestCreator.sendRequestForList(
//                "/projects/%d/issues".formatted(projectId),
//                IssueGitlabClientResponse.class,
//                HttpMethod.GET,
//                Map.of(
//                        "assignee_id", String.valueOf(assigneeId)
//                ));
//    }
//
//    @Override
//    public IssueGitlabClientResponse getIssueById(Long issueId) {
//        return gitlabApiRequestCreator.sendRequestForObject(
//                "/issues/%d".formatted(issueId),
//                IssueGitlabClientResponse.class);
//    }
//
//    @Override
//    public IssueGitlabClientResponse getIssueByProjectIdAndInternalId(Long projectId, Long internalId) {
//        return gitlabApiRequestCreator.sendRequestForObject(
//                "/projects/%d/issues/%d".formatted(projectId, internalId),
//                IssueGitlabClientResponse.class);
//    }

}
