package com.parnasit.gitparse.github.mapper;

import com.parnasit.gitparse.core.dto.StatsResponse;
import com.parnasit.gitparse.github.dto.GithubCommitResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GitHubStatsMapper {
    default StatsResponse mapToCoreStatsResponse(GithubCommitResponse.Stats gitHubStats) {
        return StatsResponse.builder()
                .additions(gitHubStats.getAdditions())
                .deletions(gitHubStats.getDeletions())
                .total(gitHubStats.getTotal())
                .build();
    }
}
