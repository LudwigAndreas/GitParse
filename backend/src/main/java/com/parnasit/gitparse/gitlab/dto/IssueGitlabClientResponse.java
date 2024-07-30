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
public class IssueGitlabClientResponse {
    private Long id;
    @JsonProperty("iid")
    private Long internalId;
    private String title;
    private String description;
    private String type;
    private String state;
    private UserGitlabClientResponse authorClientResponse;
    @JsonProperty("due_date")
    private LocalDateTime dueDate;
    @JsonProperty("web_url")
    private String webUrl;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
