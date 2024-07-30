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
public class CommitResponse {
    private String sha;
    private String title;
    private String committerName;
    private String committerEmail;
    private LocalDateTime committedDate;
    private String message;
    private String webUrl;
    private StatsResponse stats;
}
