package com.parnasit.gitparse.github.dto;

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
public class GithubRepositoryResponse {
    private Long id;
    private String name;
    private String description;
    private Boolean fork;
    private GithubUserResponse owner;
    @JsonProperty("html_url")
    private String htmlUrl;
    private Boolean privateRepo;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    @JsonProperty("pushed_at")
    private LocalDateTime pushedAt;
    private String language;
    @JsonProperty("forks_count")
    private Integer forksCount;
    @JsonProperty("open_issues_count")
    private Integer openIssuesCount;
}
