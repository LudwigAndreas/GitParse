package com.parnasit.gitparse.github.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GithubWorkflowResponse {
    private String id;
    private String name;
    private String state;
    private String createdAt;
    private String updatedAt;
    private String htmlUrl;
}
