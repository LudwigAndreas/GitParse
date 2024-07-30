package com.parnasit.gitparse.gitlab.mapper;

import com.parnasit.gitparse.core.dto.AuthorResponse;
import com.parnasit.gitparse.gitlab.dto.UserGitlabClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GitLabAuthorMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "avatarUrl", target = "avatarUrl")
    @Mapping(source = "webUrl", target = "webUrl")
    AuthorResponse mapToCoreAuthorResponse(UserGitlabClientResponse userGitlabClientResponse);

    List<AuthorResponse> mapListToCoreAuthorResponse(List<UserGitlabClientResponse> gitLabUsers);
}
