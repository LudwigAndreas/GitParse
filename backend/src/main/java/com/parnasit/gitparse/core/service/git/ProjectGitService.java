package com.parnasit.gitparse.core.service.git;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectGitService<T> extends BaseGitService {

    List<T> getRepositoriesForAuthenticatedUser(Pageable pageable);

    T getRepository(String owner, String repo);

    List<T> getProjectsByUsername(String username, Pageable pageable);

    T addUserToRepository(String owner, String repository, String username, String role, String expiration);

}
