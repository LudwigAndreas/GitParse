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
public class ProjectGitlabClientResponse {
    private Long id;
    private String name;
    private String description;
    private String path;
    @JsonProperty("default_branch")
    private String defaultBranch;
    @JsonProperty("issues_enabled")
    private Boolean issuesEnabled;
    private Owner owner;
    private Namespace namespace;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("open_issues_count")
    private Integer openIssuesCount;
    @JsonProperty("merge_requests_enabled")
    private Boolean mergeRequestsEnabled;
    private String visibility;
    @JsonProperty("ssh_url_to_repo")
    private String sshUrlToRepo;
    @JsonProperty("http_url_to_repo")
    private String httpUrlToRepo;
    @JsonProperty("readme_url")
    private String readmeUrl;
    @JsonProperty("web_url")
    private String webUrl;
    @JsonProperty("creator_id")
    private Long creatorId;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    @JsonProperty("last_activity_at")
    private LocalDateTime lastActivityAt;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Owner {
        private Long id;
        private String name;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Namespace {
        private Long id;
        private String name;
        @JsonProperty("full_path")
        private String fullPath;
    }
}
