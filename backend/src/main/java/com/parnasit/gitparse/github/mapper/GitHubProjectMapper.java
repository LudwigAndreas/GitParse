package com.parnasit.gitparse.github.mapper;


import com.parnasit.gitparse.core.dto.ProjectResponse;
import com.parnasit.gitparse.github.dto.GithubRepositoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GitHubProjectMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(target = "defaultBranch", ignore = true)
    @Mapping(source = "htmlUrl", target = "webUrl")
    @Mapping(source = "privateRepo", target = "visibility", qualifiedByName = "mapVisibility")
    @Mapping(source = "owner.avatarUrl", target = "avatarUrl")
    @Mapping(source = "owner.login", target = "ownerUsername")
    ProjectResponse mapToProjectResponse(GithubRepositoryResponse gitLabProjectResponse);

    @Named(value = "mapVisibility")
    default String mapVisibility(Boolean privateRepo) {
        //TODO: refactor
        return null;
    }

    List<ProjectResponse> mapToProjectResponses(List<GithubRepositoryResponse> gitHubProjectResponses);
}
