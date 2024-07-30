package com.parnasit.gitparse.github.mapper;

import com.parnasit.gitparse.core.dto.AuthorResponse;
import com.parnasit.gitparse.core.dto.BranchResponse;
import com.parnasit.gitparse.core.dto.StatisticsResponse;
import com.parnasit.gitparse.core.dto.StatsResponse;
import com.parnasit.gitparse.github.dto.GithubBranchResponse;
import com.parnasit.gitparse.github.dto.GithubCommitResponse;
import com.parnasit.gitparse.github.dto.GithubUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class GitHubStatisticsMapper {

    @Autowired
    private GitHubStatsMapper gitHubStatsMapper;
    @Autowired
    private GitHubAuthorMapper gitHubAuthorMapper;
    @Autowired
    private GitHubBranchMapper gitHubBranchMapper;

    @Mapping(source = "stats", target = "stats", qualifiedByName = "mapToCoreStatsResponse")
    @Mapping(source = "commit.committer", target = "committer", qualifiedByName = "mapToCoreAuthorResponse")
    @Mapping(source = "htmlUrl", target = "url")
    @Mapping(source = "commit.committer.date", target = "datetime")
    public abstract StatisticsResponse mapToStatistics(GithubCommitResponse gitHubBranch);

    @Named(value = "mapToCoreStatsResponse")
    public StatsResponse mapToCoreStatsResponse(GithubCommitResponse.Stats gitHubStats) {
        return gitHubStatsMapper.mapToCoreStatsResponse(gitHubStats);
    }

    @Named(value = "mapToCoreAuthorResponse")
    public AuthorResponse mapToCoreAuthorResponse(GithubCommitResponse.ShallowAuthor githubUser) {
        return gitHubAuthorMapper.mapToCoreAuthorResponse(githubUser);
    }

    @Named(value = "mapToCoreBranchResponse")
    public BranchResponse mapToCoreBranchResponse(GithubBranchResponse gitHubBranch) {
        return gitHubBranchMapper.mapToCoreBranchResponse(gitHubBranch);
    }

    public List<StatisticsResponse> mapToStatistics(List<GithubCommitResponse> gitHubBranch) {
        return gitHubBranch.stream().map(this::mapToStatistics).collect(Collectors.toList());
    }
}
