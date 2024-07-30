package com.parnasit.gitparse.core.service.git;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IssueGitService<T> extends BaseGitService {

    List<T> getUserIssues(String username, Pageable pageable);

    List<T> getRepositoryIssues(String owner, String repo, Pageable pageable);

    T getIssue(String owner, String repo, String id);

}
