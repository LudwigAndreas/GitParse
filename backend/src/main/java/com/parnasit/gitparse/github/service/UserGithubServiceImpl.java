package com.parnasit.gitparse.github.service;

import com.parnasit.gitparse.core.dto.AuthorResponse;
import com.parnasit.gitparse.core.dto.BranchResponse;
import com.parnasit.gitparse.core.dto.UserResponse;
import com.parnasit.gitparse.core.service.git.BranchGitService;
import com.parnasit.gitparse.core.service.git.UserGitService;
import com.parnasit.gitparse.github.dto.GithubUserResponse;
import com.parnasit.gitparse.github.mapper.GitHubAuthorMapper;
import com.parnasit.gitparse.github.service.util.GithubApiRequestCreator;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGithubServiceImpl extends GitHubService implements UserGitService<AuthorResponse> {

    private final GitHubAuthorMapper authorGithubMapper;

    public UserGithubServiceImpl(GithubApiRequestCreator githubApiRequestCreator, GitHubAuthorMapper authorGithubMapper) {
        super(githubApiRequestCreator);
        this.authorGithubMapper = authorGithubMapper;
    }

    @Override
    public AuthorResponse getAuthenticatedUser() {
        return authorGithubMapper.mapToCoreAuthorResponse(
                githubApiRequestCreator.sendRequestForObject(
                        "/user",
                        GithubUserResponse.class
                )
        );
    }

    @Override
    public List<AuthorResponse> getRepositoryMembers(String owner, String repo, Pageable pageable) {
        return authorGithubMapper.mapListToCoreAuthorResponse(
                githubApiRequestCreator.sendRequestForList(
                        "/repos/%s/%s/collaborators".formatted(owner, repo),
                        GithubUserResponse.class,
                        HttpMethod.GET,
                        pageableToQueryParams(pageable)
                )
        );
    }

    @Override
    public AuthorResponse getUserByUsername(String username) {
        return authorGithubMapper.mapToCoreAuthorResponse(
                githubApiRequestCreator.sendRequestForObject(
                        "/users/%s".formatted(username),
                        GithubUserResponse.class
                )
        );
    }

}
