package com.parnasit.gitparse.core.service.git;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StatisticsGitService<T> extends BaseGitService {

    List<T> getRepositoryStatistics(String owner, String repository, Pageable pageable);

    List<T> getRepositoryStatisticsByBranch(String owner, String repository, Pageable pageable, String branch);
}
