package com.parnasit.gitparse.github.service;


import com.parnasit.gitparse.github.dto.GithubWorkflowResponse;

import java.util.List;

public interface PipelineGithubService {
    /**
     * Получение Workflows для указанного репозитория и workflow ID.
     * @param workflowId идентификатор workflow
     * @return список workflow
     */
    List<GithubWorkflowResponse> getRepositoryWorkflow(String owner, String repo, Long workflowId);

    /**
     * Получение списка Workflows для указанного репозитория.
     * @return список workflows
     */
    List<GithubWorkflowResponse> getRepositoryWorkflows(String owner, String repo);
}
