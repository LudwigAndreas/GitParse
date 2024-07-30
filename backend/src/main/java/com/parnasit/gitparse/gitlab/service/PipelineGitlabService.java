package com.parnasit.gitparse.gitlab.service;


import com.parnasit.gitparse.gitlab.dto.PipelineGitlabClientResponse;

import java.util.List;

/**
 * Interface defining operations for getting Pipelines by GitLab API.
 */
public interface PipelineGitlabService {
    /**
     * Retrieves a list of pipelines by project.
     *
     * @param projectId - id of project
     * @return List of pipelines DTOs.
     */
    List<PipelineGitlabClientResponse> getPipelinesByProjectId(Long projectId);
    /**
     * Retrieves a pipeline by project and id.
     *
     * @param projectId - id of project
     * @param pipelineId - id of pipeline
     * @return Pipeline DTO.
     */
    PipelineGitlabClientResponse getPipelineByProjectIdAndPipeLineId(Long projectId, Long pipelineId);
    /**
     * Retrieves latest pipeline by project.
     *
     * @param projectId - id of project
     * @return Pipeline DTO.
     */
    PipelineGitlabClientResponse getLatestPipelineByProjectId(Long projectId);
}
