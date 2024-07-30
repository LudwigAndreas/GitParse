package com.parnasit.gitparse.core.service.git;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BranchGitService<T> extends BaseGitService {

    List<T> getRepositoryBranches(String owner, String repo, Pageable pageable);

    T getRepositoryBranch(String owner, String repo, String branch);

}
