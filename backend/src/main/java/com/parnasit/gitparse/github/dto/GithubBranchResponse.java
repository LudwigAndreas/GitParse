package com.parnasit.gitparse.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GithubBranchResponse {
    private String name;
    private String htmlUrl;
    private GithubCommitResponse commit;
    @JsonProperty("protected")
    private Boolean branchProtected;
}
