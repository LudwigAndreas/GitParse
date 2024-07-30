package com.parnasit.gitparse.gitlab.mapper;

import com.parnasit.gitparse.core.dto.ProjectResponse;
import com.parnasit.gitparse.gitlab.dto.ProjectGitlabClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GitLabProjectMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "defaultBranch", target = "defaultBranch")
    @Mapping(source = "webUrl", target = "webUrl")
    @Mapping(source = "visibility", target = "visibility")
    @Mapping(source = "avatarUrl", target = "avatarUrl")
    @Mapping(source = "namespace.fullPath", target = "ownerUsername", defaultValue = "undefined")
    ProjectResponse mapToProjectResponse(ProjectGitlabClientResponse gitLabProjectResponse);

    List<ProjectResponse> mapToProjectResponses(List<ProjectGitlabClientResponse> gitLabProjectResponses);

}
