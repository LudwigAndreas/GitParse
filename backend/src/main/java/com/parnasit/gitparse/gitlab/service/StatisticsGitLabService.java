package com.parnasit.gitparse.gitlab.service;

import com.parnasit.gitparse.core.dto.StatisticsResponse;
import com.parnasit.gitparse.core.security.oauth2.OAuth2AccessTokenService;
import com.parnasit.gitparse.core.service.git.StatisticsGitService;
import com.parnasit.gitparse.gitlab.dto.CommitGitlabClientResponse;
import com.parnasit.gitparse.gitlab.exception.GitlabApiException;
import com.parnasit.gitparse.gitlab.mapper.GitLabProjectMapper;
import com.parnasit.gitparse.gitlab.mapper.GitLabStatisticsMapper;
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
public class StatisticsGitLabService extends GitLabService implements StatisticsGitService<StatisticsResponse> {

    private static final Logger log = LoggerFactory.getLogger(StatisticsGitLabService.class);
    private final GitLabStatisticsMapper gitLabStatisticsMapper;
    private final OAuth2AccessTokenService tokenService;
    private final GitLabProjectMapper gitLabProjectMapper;

    public StatisticsGitLabService(GitlabApiRequestCreator gitlabApiRequestCreator, GitLabStatisticsMapper gitLabStatisticsMapper, OAuth2AccessTokenService tokenService, GitLabProjectMapper gitLabProjectMapper) {
        super(gitlabApiRequestCreator);
        this.gitLabStatisticsMapper = gitLabStatisticsMapper;
        this.tokenService = tokenService;
        this.gitLabProjectMapper = gitLabProjectMapper;
    }



    @Override
    public List<StatisticsResponse> getRepositoryStatistics(String owner, String repository, Pageable pageable) {
        Map<String, String> params = new HashMap<>();
             params.put("with_stats", "true");

        try {
            List<CommitGitlabClientResponse> responses = gitlabApiRequestCreator.sendRequestForList(
                    "/projects/" + ownerRepoToId(owner, repository) + "/repository/commits",
                    CommitGitlabClientResponse.class,
                    HttpMethod.GET,
                    params
            );
            return gitLabStatisticsMapper.mapToCoreStatisticsList(responses);


        } catch (Exception e) {

            log.trace("Error fetching project from GitLab: {}", e.getMessage());
            throw new GitlabApiException("Error fetching from Git: " + e.getMessage());
        }

    }

    @Override
    public List<StatisticsResponse> getRepositoryStatisticsByBranch(String owner, String repository, Pageable pageable, String branch) {
        return List.of();
    }
}
