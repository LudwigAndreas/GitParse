package com.parnasit.gitparse.gitlab.service;

import com.parnasit.gitparse.core.service.util.ApiRequestCreator;
import com.parnasit.gitparse.gitlab.dto.PipelineGitlabClientResponse;
import com.parnasit.gitparse.gitlab.service.PipelineGitlabService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PipelineGitlabServiceImpl implements PipelineGitlabService {

    private final ApiRequestCreator gitlabApiRequestCreator;

    @Override
    public List<PipelineGitlabClientResponse> getPipelinesByProjectId(Long projectId) {
        return gitlabApiRequestCreator.sendRequestForList(
                "/projects/%d/pipelines".formatted(projectId),
                PipelineGitlabClientResponse.class);
    }

    @Override
    public PipelineGitlabClientResponse getPipelineByProjectIdAndPipeLineId(Long projectId, Long pipelineId) {
        return gitlabApiRequestCreator.sendRequestForObject(
                "/projects/%d/pipelines/%d".formatted(projectId, pipelineId),
                PipelineGitlabClientResponse.class);
    }

    @Override
    public PipelineGitlabClientResponse getLatestPipelineByProjectId(Long projectId) {
        return gitlabApiRequestCreator.sendRequestForObject(
                "/projects/%d/pipelines/latest".formatted(projectId),
                PipelineGitlabClientResponse.class);
    }
}
