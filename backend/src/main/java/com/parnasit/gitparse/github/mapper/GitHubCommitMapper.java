package com.parnasit.gitparse.github.mapper;

import com.parnasit.gitparse.core.dto.CommitResponse;
import com.parnasit.gitparse.core.dto.StatsResponse;
import com.parnasit.gitparse.github.dto.GithubCommitResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class GitHubCommitMapper {
    @Autowired
    private GitHubStatsMapper gitHubStatsMapper;
    //TODO: в GithubCommitResponse нет полей: title, committedDate
    @Mapping(source = "sha", target = "sha")
    @Mapping(source = "author.name", target = "committerName")
    @Mapping(source = "author.email", target = "committerEmail")
    @Mapping(source = "commit.message", target = "message")
    @Mapping(source = "htmlUrl", target = "webUrl")
    @Mapping(source = "stats", target = "stats", qualifiedByName = "mapToCoreStatsResponse")
    public abstract CommitResponse mapToCoreCommitResponse(GithubCommitResponse gitHubCommit);

    @Named(value = "mapToCoreStatsResponse")
    public StatsResponse mapToCoreStatsResponse(GithubCommitResponse.Stats gitHubStats) {
        return gitHubStatsMapper.mapToCoreStatsResponse(gitHubStats);
    }

    public abstract List<CommitResponse> mapToListCoreCommitResponse(List<GithubCommitResponse> gitHubCommits);
}
