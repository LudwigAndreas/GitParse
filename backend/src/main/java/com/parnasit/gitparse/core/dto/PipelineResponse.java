package com.parnasit.gitparse.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PipelineResponse {
    private Integer id;
    private Integer iid;
    private Integer projectId;
    private String status;
    private String source;
    private String ref;
    private String sha;
    private String name;
    private String webUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
