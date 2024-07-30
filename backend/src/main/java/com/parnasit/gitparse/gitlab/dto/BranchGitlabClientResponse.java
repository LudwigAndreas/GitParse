package com.parnasit.gitparse.gitlab.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchGitlabClientResponse {
    private String name;
    private Boolean merged;
    @JsonProperty("protected")
    private Boolean branchProtected;
    @JsonProperty("default")
    private Boolean branchDefault;
    @JsonProperty("developers_can_push")
    private Boolean developersCanPush;
    @JsonProperty("developers_can_merge")
    private Boolean developersCanMerge;
    @JsonProperty("can_push")
    private Boolean canPush;
    @JsonProperty("web_url")
    private String webUrl;
    private CommitGitlabClientResponse commit;
}
