package com.parnasit.gitparse.gitlab.mapper;

import com.parnasit.gitparse.core.dto.CommitResponse;
import com.parnasit.gitparse.core.dto.StatsResponse;
import com.parnasit.gitparse.gitlab.dto.CommitGitlabClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class GitLabCommitMapper {

    private GitLabStatsMapper gitLabStatsMapper;

    @Mapping(source = "id", target = "sha")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "authorName", target = "committerName")
    @Mapping(source = "authorEmail", target = "committerEmail")
    @Mapping(source = "message", target = "message")
    @Mapping(source = "webUrl", target = "webUrl")
    @Mapping(source = "stats", target = "stats", qualifiedByName = "mapToCoreStatsResponse")
    public abstract CommitResponse mapToCoreCommitResponse(CommitGitlabClientResponse gitLabCommit);


    @Named(value = "mapToCoreStatsResponse")
    public StatsResponse mapToCoreStatsResponse(CommitGitlabClientResponse.CommitStats gitLabStats) {
        return gitLabStatsMapper.mapToCoreStatsResponse(gitLabStats);
    }

    public abstract List<CommitResponse> mapToListCoreCommitResponse(List<CommitGitlabClientResponse> gitLabCommits);

}
