package com.parnasit.gitparse.gitlab.service;

import com.parnasit.gitparse.core.dto.AuthorResponse;
import com.parnasit.gitparse.core.service.git.UserGitService;
import com.parnasit.gitparse.gitlab.dto.UserGitlabClientResponse;
import com.parnasit.gitparse.gitlab.mapper.GitLabAuthorMapper;
import com.parnasit.gitparse.gitlab.mapper.GitLabUserMapper;
import com.parnasit.gitparse.gitlab.service.GitLabService;
import com.parnasit.gitparse.gitlab.service.util.GitlabApiRequestCreator;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGitlabServiceImpl extends GitLabService implements UserGitService<AuthorResponse> {

    private final GitLabAuthorMapper gitLabAuthorMapper;

    public UserGitlabServiceImpl(GitlabApiRequestCreator gitlabApiRequestCreator, GitLabUserMapper gitLabUserMapper, GitLabAuthorMapper gitLabAuthorMapper) {
        super(gitlabApiRequestCreator);
        this.gitLabAuthorMapper = gitLabAuthorMapper;
    }

    @Override
    public AuthorResponse getAuthenticatedUser() {
        return gitLabAuthorMapper.mapToCoreAuthorResponse(
                gitlabApiRequestCreator.sendRequestForObject(
                        "/user",
                        UserGitlabClientResponse.class
                )
        );
    }

    @Override
    public List<AuthorResponse> getRepositoryMembers(String owner, String repo, Pageable pageable) {
        return gitLabAuthorMapper.mapListToCoreAuthorResponse(
                gitlabApiRequestCreator.sendRequestForList(
                        "/repos/%s/%s/collaborators".formatted(owner, repo),
                        UserGitlabClientResponse.class,
                        HttpMethod.GET,
                        pageableToQueryParams(pageable)
                )
        );
    }

    @Override
    public AuthorResponse getUserByUsername(String username) {
        return gitLabAuthorMapper.mapToCoreAuthorResponse(
                gitlabApiRequestCreator.sendRequestForObject(
                        "/users/%s".formatted(username),
                        UserGitlabClientResponse.class
                )
        );
    }

    //    @Override
//    public UserGitlabClientResponse getUserById(Long userId) {
//        return gitlabApiRequestCreator.sendRequestForObject(
//                "/users/%d".formatted(userId),
//                UserGitlabClientResponse.class);
//    }
//
//    @Override
//    public List<UserGitlabClientResponse> getUsersByProjectId(Long projectId) {
//        return gitlabApiRequestCreator.sendRequestForList(
//                "/projects/%d/users".formatted(projectId),
//                UserGitlabClientResponse.class);
//    }

}
