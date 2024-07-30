//package com.parnasit.gitparse.github.service;
//
//import com.parnasit.gitparse.core.dto.PipelineResponse;
//import com.parnasit.gitparse.github.dto.GithubWorkflowResponse;
//import com.parnasit.gitparse.github.service.PipelineGithubService;
//import com.parnasit.gitparse.core.service.util.ApiRequestCreator;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class PipelineGithubServiceImpl extends GitHubService implements PipelineGitService<PipelineResponse> {
//
//    private final ApiRequestCreator githubApiRequestCreator;
//
//    @Override
//    public List<GithubWorkflowResponse> getRepositoryWorkflow(String owner, String repo, Long workflowId) {
//        return githubApiRequestCreator.sendRequestForList(
//                "/repos/%s/%s/actions/workflows/%d".formatted(owner,repo,workflowId),
//                GithubWorkflowResponse.class
//        );
//    }
//
//    @Override
//    public List<GithubWorkflowResponse> getRepositoryWorkflows(String owner, String repo) {
//        return githubApiRequestCreator.sendRequestForList(
//                "/repos/%s/%s/actions/workflows".formatted(owner,repo),
//                GithubWorkflowResponse.class
//        );
//    }
//}
