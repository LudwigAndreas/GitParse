package com.parnasit.gitparse.gitlab.mapper;


import com.parnasit.gitparse.core.dto.UserResponse;
import com.parnasit.gitparse.gitlab.dto.UserGitlabClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GitLabUserMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "locked", target = "locked")
    @Mapping(source = "avatarUrl", target = "avatarUrl")
    @Mapping(source = "webUrl", target = "webUrl")
    UserResponse mapToCoreUserResponse(UserGitlabClientResponse userGitlabClientResponse);

    List<UserResponse> mapToCoreUserResponseList(List<UserGitlabClientResponse> gitLabUsers);
}
