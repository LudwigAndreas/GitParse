package com.parnasit.gitparse.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsResponse {
    private String url;
    private StatsResponse stats;
    private LocalDateTime datetime;
    private AuthorResponse committer;
    private BranchResponse branch;
    private String sha;
}
