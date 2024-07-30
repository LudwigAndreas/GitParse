package com.parnasit.gitparse.gitlab.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PipelineGitlabClientResponse {
    private Long id;
    private Long iid;
    @JsonProperty("project_id")
    private Long projectId;
    private String name;
    private String status;
    private String source;
    private String ref;
    private Integer duration;
    @JsonProperty("queued_duration")
    private Integer queuedDuration;
    private String sha;
    @JsonProperty("before_sha")
    private String beforeSha;
    private Boolean tag;
    private UserGitlabClientResponse user;
    @JsonProperty("web_url")
    private String webUrl;
    @JsonProperty("started_at")
    private LocalDateTime startedAt;
    @JsonProperty("finished_at")
    private LocalDateTime finishedAt;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
