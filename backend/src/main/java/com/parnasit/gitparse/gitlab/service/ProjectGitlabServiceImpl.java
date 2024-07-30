package com.parnasit.gitparse.gitlab.service;

import com.parnasit.gitparse.core.dto.ProjectResponse;
import com.parnasit.gitparse.core.service.git.ProjectGitService;
import com.parnasit.gitparse.gitlab.dto.ProjectGitlabClientResponse;
import com.parnasit.gitparse.gitlab.exception.GitlabApiException;
import com.parnasit.gitparse.gitlab.mapper.GitLabProjectMapper;
import com.parnasit.gitparse.gitlab.service.util.GitlabApiRequestCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectGitlabServiceImpl extends GitLabService
        implements ProjectGitService<ProjectResponse> {
    private static final Logger log =
            LoggerFactory.getLogger(ProjectGitlabServiceImpl.class);
    private final GitLabProjectMapper projectGitLabMapper;

    public ProjectGitlabServiceImpl(
            GitlabApiRequestCreator gitlabApiRequestCreator,
            GitLabProjectMapper projectGitLabMapper) {
        super(gitlabApiRequestCreator);
        this.projectGitLabMapper = projectGitLabMapper;
    }

    @Override
    public List<ProjectResponse> getRepositoriesForAuthenticatedUser(
            Pageable pageable) {
        Map<String, String> params =
                new HashMap<>(pageableToQueryParams(pageable));
        params.put("membership", "true");
        return projectGitLabMapper.mapToProjectResponses(
                gitlabApiRequestCreator.sendRequestForList("/projects",
                        ProjectGitlabClientResponse.class, HttpMethod.GET,
                        params));
    }

    @Override
    public ProjectResponse getRepository(String owner, String repo) {
        return projectGitLabMapper.mapToProjectResponse(
                gitlabApiRequestCreator.sendRequestForObject(
                        "/projects/" + ownerRepoToId(owner, repo),
                        ProjectGitlabClientResponse.class));
    }


    @Override
    public List<ProjectResponse> getProjectsByUsername(String username,
                                                       Pageable pageable) {
        Map<String, String> params =
                new HashMap<>(pageableToQueryParams(pageable));


        params.put("membership", "true");
        params.put("order_by", "name");

        List<ProjectGitlabClientResponse> gitlabResponses =
                gitlabApiRequestCreator.sendRequestForList(
                        "/users/%s/projects".formatted(username),
                        ProjectGitlabClientResponse.class, HttpMethod.GET,
                        params);

        return projectGitLabMapper.mapToProjectResponses(gitlabResponses);
    }

    @Override
    public ProjectResponse addUserToRepository(String owner, String repository,
                                               String username, String role,
                                               String expiration) {

        try {
            log.trace("path: {}", "/projects/" + ownerRepoToId(owner, repository) + "/members");
            ProjectGitlabClientResponse response =
                    gitlabApiRequestCreator.sendRequestForObject("/projects/" + ownerRepoToId(owner, repository) + "/members",
                            ProjectGitlabClientResponse.class, Map.of("username", username, "access_level", role)
                    );

            return projectGitLabMapper.mapToProjectResponse(response);
        } catch (Exception e) {
            log.trace("Error adding user to repository in GitLab: {}",
                    e.getMessage());
            throw new GitlabApiException(
                    "Error adding user to repository in GitLab: " +
                            e.getMessage());
        }

    }


}
