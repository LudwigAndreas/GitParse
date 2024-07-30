package com.parnasit.gitparse.core.service.impl;

import com.parnasit.gitparse.core.dto.*;
import com.parnasit.gitparse.core.service.CoreFacade;
import com.parnasit.gitparse.core.security.oauth2.OAuth2AccessTokenService;
import com.parnasit.gitparse.core.service.git.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoreFacadeImpl implements CoreFacade {

    private final OAuth2AccessTokenService accessTokenService;

    private final List<ProjectGitService<ProjectResponse>> projectGitServices;
    private final List<CommitGitService<CommitResponse>> commitGitServices;
    private final List<StatisticsGitService<StatisticsResponse>> statisticsGitServices;
    private final List<IssueGitService<IssueResponse>> issueGitServices;
    private final List<BranchGitService<BranchResponse>> branchGitServices;
    private final List<UserGitService<UserResponse>> pipelineGitServices;

    public CoreFacadeImpl(OAuth2AccessTokenService accessTokenService, List<ProjectGitService<ProjectResponse>> projectGitServices, List<CommitGitService<CommitResponse>> commitGitServices, List<StatisticsGitService<StatisticsResponse>> statisticsGitServices, List<IssueGitService<IssueResponse>> issueGitServices, List<BranchGitService<BranchResponse>> branchGitServices, List<UserGitService<UserResponse>> pipelineGitServices) {
        this.accessTokenService = accessTokenService;
        this.projectGitServices = projectGitServices;
        this.commitGitServices = commitGitServices;
        this.statisticsGitServices = statisticsGitServices;
        this.issueGitServices = issueGitServices;
        this.branchGitServices = branchGitServices;
        this.pipelineGitServices = pipelineGitServices;
    }

    @Override
    public List<ProjectResponse> listProjects(String gitPlatform, Pageable pageable) {
        for (ProjectGitService<ProjectResponse> projectGitService : projectGitServices) {
            if (projectGitService.supports(gitPlatform)) {
                return projectGitService.getRepositoriesForAuthenticatedUser(pageable);
            }
        }
        return null;
    }

    @Override
    public ProjectResponse getRepository(String gitPlatform, String owner, String repository) {
        for (ProjectGitService<ProjectResponse> projectGitService : projectGitServices) {
            if (projectGitService.supports(gitPlatform)) {
                return projectGitService.getRepository(owner, repository);
            }
        }
        return null;
    }

    @Override
    public ProjectResponse addContributor(String gitPlatform, String owner, String repository, String contributor, String role, String expiration) {
        for (ProjectGitService<ProjectResponse> projectGitService : projectGitServices) {
            if (projectGitService.supports(gitPlatform)) {
                return projectGitService.addUserToRepository(owner, repository, contributor, role, expiration);
            }
        }
        return null;
    }

    @Override
    public List<StatisticsResponse> getRepositoryStatistics(String gitPlatform, String owner, String repository, Pageable pageable) {
        for (StatisticsGitService<StatisticsResponse> statisticsGitService : statisticsGitServices) {
            if (statisticsGitService.supports(gitPlatform)) {
                return statisticsGitService.getRepositoryStatistics(owner, repository, pageable);
            }
        }
        return null;
    }

    @Override
    public List<IssueResponse> getRepositoryIssues(String gitPlatform, String owner, String repository, Pageable pageable) {
        for (IssueGitService<IssueResponse> issueGitService : issueGitServices) {
            if (issueGitService.supports(gitPlatform)) {
                return issueGitService.getRepositoryIssues(owner, repository, pageable);
            }
        }
        return null;
    }

    @Override
    public IssueResponse getIssueInfo(String gitPlatform, String owner, Long repositoryId, Long issueId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<PipelineResponse> getRepositoryPipelines(String gitPlatform, String owner, Long repository, Pageable pageable) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public PipelineResponse getPipelineInfo(String gitPlatform, String owner, Long repository, Long pipelineId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
