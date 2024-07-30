package com.parnasit.gitparse.github.mapper;


import com.parnasit.gitparse.core.dto.UserResponse;
import com.parnasit.gitparse.github.dto.GithubUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GitHubUserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "login", target = "username")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "avatarUrl", target = "avatarUrl")
    @Mapping(source = "htmlUrl", target = "webUrl")
    UserResponse mapToCoreUserResponse(GithubUserResponse githubUserResponse);
}
