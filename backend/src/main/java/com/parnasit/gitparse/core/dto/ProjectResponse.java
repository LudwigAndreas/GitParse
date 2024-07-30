package com.parnasit.gitparse.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponse {
    private Long id;
    private String description;
    private String name;
    private LocalDateTime createdAt;
    private String defaultBranch;
    private String webUrl;
    private String visibility;
    private String avatarUrl;
    private String ownerUsername;
}
