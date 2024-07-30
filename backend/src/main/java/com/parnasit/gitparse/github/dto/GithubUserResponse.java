package com.parnasit.gitparse.github.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class GithubUserResponse {
    private Long id;
    private String login;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("html_url")
    private String htmlUrl;
    private String name;
    private String email;
    private Boolean hireable;
    private String bio;
    @JsonProperty("public_repos")
    private Integer publicRepos;
    private Integer followers;
    private Integer following;
    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime updatedAt;
}
