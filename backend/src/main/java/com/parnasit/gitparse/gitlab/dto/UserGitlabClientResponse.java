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
public class UserGitlabClientResponse {
    private Long id;
    private String username;
    private String name;
    @JsonProperty("public_email")
    private String publicEmail;
    private String state;
    private Boolean locked;
    private Long followers;
    private Long following;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("web_url")
    private String webUrl;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
}
