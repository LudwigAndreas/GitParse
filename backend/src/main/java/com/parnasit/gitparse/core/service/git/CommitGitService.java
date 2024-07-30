package com.parnasit.gitparse.core.service.git;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommitGitService<T> extends BaseGitService {

    List<T> getRepositoryCommits(String owner, String repo, Pageable pageable);

    T getCommit(String owner, String repo, String sha);

    T compareCommits(String owner, String repo, String base, String head);

}
