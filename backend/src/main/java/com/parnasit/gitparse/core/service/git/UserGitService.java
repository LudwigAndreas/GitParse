package com.parnasit.gitparse.core.service.git;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserGitService<T> extends BaseGitService {

    T getAuthenticatedUser();

    T getUserByUsername(String username);

    List<T> getRepositoryMembers(String owner, String repo, Pageable pageable);
}
