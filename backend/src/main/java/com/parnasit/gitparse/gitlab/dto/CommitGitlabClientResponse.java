package com.parnasit.gitparse.gitlab.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommitGitlabClientResponse {
    private String id;
    @JsonProperty("short_id")
    private String shortId;
    private String title;
    private String message;
    @JsonProperty("web_url")
    private String webUrl;
    @JsonProperty("author_name")
    private String authorName;
    @JsonProperty("author_email")
    private String authorEmail;

    @JsonProperty("authored_date")
    private LocalDateTime authoredDate;

    @JsonProperty("committer_name")
    private String committerName;
    @JsonProperty("committer_email")
    private String committerEmail;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("parent_ids")
    private List<String> parentIds;
    private CommitStats stats;
    private String status;
    @JsonProperty("last_pipeline")
    private CommitLastPipeline lastPipeline;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CommitStats {
        private Integer additions;
        private Integer deletions;
        private Integer total;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CommitLastPipeline {
        private Long id;
        private String ref;
        private String sha;
        private String status;
    }
}
