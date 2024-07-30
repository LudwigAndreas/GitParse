package com.parnasit.gitparse.github.mapper;

import com.parnasit.gitparse.core.dto.BranchResponse;
import com.parnasit.gitparse.core.dto.CommitResponse;
import com.parnasit.gitparse.github.dto.GithubBranchResponse;
import com.parnasit.gitparse.github.dto.GithubCommitResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class GitHubBranchMapper {
    @Autowired
    private GitHubCommitMapper gitHubCommitMapper;

    @Mapping(source = "name", target = "name")
    @Mapping(source = "htmlUrl", target = "webUrl")
    @Mapping(source = "commit", target = "commit", qualifiedByName = "mapToCoreCommitResponse")
    public abstract BranchResponse mapToCoreBranchResponse(GithubBranchResponse gitHubBranch);

    @Named("mapToCoreCommitResponse")
    public CommitResponse mapToCoreCommitResponse(GithubCommitResponse gitHubCommit) {
        return gitHubCommitMapper.mapToCoreCommitResponse(gitHubCommit);
    }

    public List<BranchResponse> mapToCoreBranchResponseList(List<GithubBranchResponse> gitHubBranches) {
        return gitHubBranches.stream()
                .map(this::mapToCoreBranchResponse)
                .toList();
    }
}
