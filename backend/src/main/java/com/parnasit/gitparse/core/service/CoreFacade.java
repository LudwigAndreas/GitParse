package com.parnasit.gitparse.core.service;


import com.parnasit.gitparse.core.dto.IssueResponse;
import com.parnasit.gitparse.core.dto.PipelineResponse;
import com.parnasit.gitparse.core.dto.ProjectResponse;
import com.parnasit.gitparse.core.dto.StatisticsResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Interface defining operations for managing projects and repositories.
 */
public interface CoreFacade {

    /**
     * Retrieves a list of all projects (repositories).
     *
     * @param gitPlatform The git platform to retrieve the project from.
     * @return List of projects.
     */
    List<ProjectResponse> listProjects(String gitPlatform, Pageable pageable);

    /**
     * Retrieves information about a specific project(repository) by its ID.
     *
     * @param gitPlatform The git platform to retrieve the project from.
     * @param owner       The owner of the project(repository) to retrieve. (Unused for some git platforms)
     * @param repository  The ID or name of the project(repository) to retrieve.
     * @return The project object.
     */

    ProjectResponse getRepository(String gitPlatform, String owner, String repository);

    /**
     * Adds a contributor to a specific project(repository).
     *
     * @param gitPlatform The git platform to retrieve the project from.
     * @param owner       The owner of the project(repository) to add the contributor to. (Unused for some git platforms)
     * @param repository  The ID or name of the project(repository) to add the contributor to.
     * @param contributor The username of the contributor to add.
     * @param role        The role of the contributor. (Unused for some git platforms)
     * @param expiration  The expiration date for the contributor's access. (Unused for some git platforms)
     * @return A confirmation message.
     */
    ProjectResponse addContributor(String gitPlatform, String owner, String repository, String contributor, String role, String expiration);

    /**
     * Retrieves statistics for a specific project(repository).
     *
     * @param gitPlatform The git platform to retrieve the project from.
     * @param owner       The owner of the project(repository) to retrieve issues for.
     * @param repository  The ID of the project(repository) to retrieve statistics for.
     * @param pageable    Pageable object.
     * @return Statistics information.
     */
    List<StatisticsResponse> getRepositoryStatistics(String gitPlatform, String owner, String repository, Pageable pageable);

    /**
     * Retrieves a list of issues for a specific project(repository).
     *
     * @param gitPlatform The git platform to retrieve the project from.
     * @param owner       The owner of the project(repository) to retrieve issues for.
     * @param repository  The ID of the project(repository) to retrieve issues for.
     * @param pageable    Pageable object.
     * @return List of issues.
     */
    List<IssueResponse> getRepositoryIssues(String gitPlatform, String owner, String repository, Pageable pageable);

    /**
     * Retrieves information about a specific issue within a project(repository).
     *
     * @param gitPlatform  The git platform to retrieve the project from.
     * @param owner        The owner of the project(repository) to retrieve issues for.
     * @param repositoryId The ID of the project(repository) containing the issue.
     * @param issueId      The ID of the issue.
     * @return Information about the issue.
     */
    IssueResponse getIssueInfo(String gitPlatform, String owner, Long repositoryId, Long issueId);

    /**
     * Retrieves a list of pipelines for a specific project(repository).
     *
     * @param gitPlatform The git platform to retrieve the project from.
     * @param owner       The owner of the project(repository) to retrieve issues for.
     * @param repository  The ID of the project(repository) to retrieve pipelines for.
     * @return List of pipelines.
     */
    List<PipelineResponse> getRepositoryPipelines(String gitPlatform, String owner, Long repository, Pageable pageable);

    /**
     * Retrieves information about a specific pipeline within a project(repository).
     *
     * @param gitPlatform The git platform to retrieve the project from.
     * @param owner       The owner of the project(repository) to retrieve issues for.
     * @param repository  The ID of the project(repository) containing the pipeline.
     * @param pipelineId  The ID of the pipeline to retrieve information for.
     * @return Information about the pipeline.
     */
    PipelineResponse getPipelineInfo(String gitPlatform, String owner, Long repository, Long pipelineId);


}
