package com.parnasit.gitparse.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchResponse {
    private String name;
    private String webUrl;
    private CommitResponse commit;
}
