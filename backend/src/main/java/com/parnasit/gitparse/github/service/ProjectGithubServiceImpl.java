package com.parnasit.gitparse.github.service;

import com.parnasit.gitparse.core.dto.ProjectResponse;
import com.parnasit.gitparse.core.service.git.ProjectGitService;
import com.parnasit.gitparse.github.dto.GithubRepositoryResponse;
import com.parnasit.gitparse.github.mapper.GitHubProjectMapper;
import com.parnasit.gitparse.github.service.util.GithubApiRequestCreator;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProjectGithubServiceImpl extends GitHubService implements ProjectGitService<ProjectResponse> {

    private final GitHubProjectMapper projectGithubMapper;

    public ProjectGithubServiceImpl(GithubApiRequestCreator githubApiRequestCreator, GitHubProjectMapper projectGithubMapper) {
        super(githubApiRequestCreator);
        this.projectGithubMapper = projectGithubMapper;
    }

    @Override
    public List<ProjectResponse> getRepositoriesForAuthenticatedUser(Pageable pageable) {
        return projectGithubMapper.mapToProjectResponses(
                githubApiRequestCreator.sendRequestForList(
                        "/user/repos",
                        GithubRepositoryResponse.class,
                        HttpMethod.GET,
                        pageableToQueryParams(pageable)
                )
        );
    }


    @Override
    public ProjectResponse getRepository(String owner, String repo) {
        return projectGithubMapper.mapToProjectResponse(
                githubApiRequestCreator.sendRequestForObject(
                        "/repos/%s/%s".formatted(owner, repo),
                        GithubRepositoryResponse.class
                )
        );
    }


    @Override
    public List<ProjectResponse> getProjectsByUsername(String username, Pageable pageable) {
        return projectGithubMapper.mapToProjectResponses(
                githubApiRequestCreator.sendRequestForList(
                        "/users/%s/repos".formatted(username),
                        GithubRepositoryResponse.class,
                        HttpMethod.GET,
                        pageableToQueryParams(pageable)
                )
        );
    }


    @Override
    public ProjectResponse addUserToRepository(String owner, String repository, String username, String role, String expiration) {
        return projectGithubMapper.mapToProjectResponse(
                githubApiRequestCreator.sendRequestForObject(
                        "/repos/%s/%s/collaborators/%s".formatted(owner, repository, username),
                        GithubRepositoryResponse.class,
                        HttpMethod.POST,
                        Map.of("permission", role)
                )
        );
    }
}
