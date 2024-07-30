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
public class CommitDiffGitlabClientResponse {
    private String diff;
    @JsonProperty("new_path")
    private String newPath;
    @JsonProperty("old_path")
    private String oldPath;
    @JsonProperty("a_mode")
    private String aMode;
    @JsonProperty("b_mode")
    private String bMode;
    @JsonProperty("new_file")
    private String newFile;
    @JsonProperty("renamed_file")
    private String renamedFile;
    @JsonProperty("deleted_file")
    private String deletedFile;
}
