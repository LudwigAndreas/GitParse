package com.parnasit.gitparse.gitlab.mapper;


import com.parnasit.gitparse.core.dto.AuthorResponse;
import com.parnasit.gitparse.core.dto.StatsResponse;
import com.parnasit.gitparse.core.dto.BranchResponse;
import com.parnasit.gitparse.core.dto.StatisticsResponse;
import com.parnasit.gitparse.gitlab.dto.BranchGitlabClientResponse;
import com.parnasit.gitparse.gitlab.dto.CommitGitlabClientResponse;
import com.parnasit.gitparse.gitlab.dto.UserGitlabClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class GitLabStatisticsMapper {

    @Autowired
    private GitLabStatsMapper gitLabStatsMapper;
    @Autowired
    private GitLabAuthorMapper gitLabAuthorMapper;
    @Autowired
    private GitLabBranchMapper gitLabBranchMapper;

    @Mapping(source = "webUrl", target = "url")
    @Mapping(source = "createdAt", target = "datetime")
    @Mapping(source = "committerName", target = "committer.name")
    @Mapping(source = "stats", target = "stats", qualifiedByName = "mapToCoreStatsResponse")
    public abstract StatisticsResponse mapToStatisticsResponse(CommitGitlabClientResponse commitGitlabClientResponse);


    public List<StatisticsResponse> mapToCoreStatisticsList(List<CommitGitlabClientResponse> gitLabCommit) {
        return gitLabCommit.stream()
                .map(this::mapToStatisticsResponse)
                .collect(Collectors.toList());
    }


    @Named(value = "mapToCoreAuthorResponse")
    public AuthorResponse mapToCoreAuthorResponse(UserGitlabClientResponse userGitlabClientResponse) {
        return gitLabAuthorMapper.mapToCoreAuthorResponse(userGitlabClientResponse);

    }

    @Named(value = "mapToCoreStatsResponse")
    public StatsResponse mapToCoreStatsResponse(CommitGitlabClientResponse.CommitStats gitLabStats) {
        return gitLabStatsMapper.mapToCoreStatsResponse(gitLabStats);
    }

    @Named(value = "mapToCoreBranchResponse")
    public BranchResponse mapToCoreBranchResponse(BranchGitlabClientResponse gitLabBranch) {
        return gitLabBranchMapper.mapToCoreBranchResponse(gitLabBranch);
    }


}
