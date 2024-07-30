package com.parnasit.gitparse.gitlab.mapper;

import com.parnasit.gitparse.core.dto.BranchResponse;
import com.parnasit.gitparse.core.dto.CommitResponse;
import com.parnasit.gitparse.gitlab.dto.BranchGitlabClientResponse;
import com.parnasit.gitparse.gitlab.dto.CommitGitlabClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class GitLabBranchMapper {
    @Autowired
    private GitLabCommitMapper gitLabCommitMapper;

    @Mapping(source = "name", target = "name")
    @Mapping(source = "webUrl", target = "webUrl")
    @Mapping(source = "commit", target = "commit", qualifiedByName = "mapToCoreCommitResponse")
    public abstract BranchResponse mapToCoreBranchResponse(BranchGitlabClientResponse gitLabBranch);

    @Named("mapToCoreCommitResponse")
    public CommitResponse mapToCoreCommitResponse(CommitGitlabClientResponse gitLabCommit) {
        return gitLabCommitMapper.mapToCoreCommitResponse(gitLabCommit);
    }

    public List<BranchResponse> mapToCoreBranchResponseList(List<BranchGitlabClientResponse> gitLabBranches) {
        return gitLabBranches.stream()
                .map(this::mapToCoreBranchResponse)
                .toList();
    }
}
