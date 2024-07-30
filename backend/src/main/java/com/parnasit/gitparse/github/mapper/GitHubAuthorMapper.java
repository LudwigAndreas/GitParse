package com.parnasit.gitparse.github.mapper;

import com.parnasit.gitparse.core.dto.AuthorResponse;
import com.parnasit.gitparse.github.dto.GithubCommitResponse;
import com.parnasit.gitparse.github.dto.GithubUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GitHubAuthorMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "login", target = "username")
    @Mapping(source = "htmlUrl", target = "webUrl")
    @Mapping(source = "avatarUrl", target = "avatarUrl")
    AuthorResponse mapToCoreAuthorResponse(GithubUserResponse gitHubUser);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "login", target = "username")
    @Mapping(source = "htmlUrl", target = "webUrl")
    @Mapping(source = "avatarUrl", target = "avatarUrl")
    AuthorResponse mapToCoreAuthorResponse(GithubCommitResponse.ShallowAuthor githubUser);

    List<AuthorResponse> mapListToCoreAuthorResponse(List<GithubUserResponse> gitHubUsers);
}
