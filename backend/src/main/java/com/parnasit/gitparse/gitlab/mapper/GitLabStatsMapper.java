package com.parnasit.gitparse.gitlab.mapper;

import com.parnasit.gitparse.core.dto.StatsResponse;
import com.parnasit.gitparse.gitlab.dto.CommitGitlabClientResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GitLabStatsMapper {
    default StatsResponse mapToCoreStatsResponse(CommitGitlabClientResponse.CommitStats gitLabStats) {
        if (gitLabStats == null) {
            return StatsResponse.builder()
                    .additions(0)
                    .deletions(0)
                    .total(0)
                    .build();
        }

        return StatsResponse.builder()
                .additions(gitLabStats.getAdditions())
                .deletions(gitLabStats.getDeletions())
                .total(gitLabStats.getTotal())
                .build();

    }


}
