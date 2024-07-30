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
public class IssueResponse {
    private Long id;
    private String state;
    private String description;
    private AuthorResponse author;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime dueDate;
    private String webUrl;
}
