package com.parnasit.gitparse.gitlab.service;

import com.parnasit.gitparse.core.service.git.BaseGitService;
import com.parnasit.gitparse.gitlab.service.util.GitlabApiRequestCreator;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class GitLabService implements BaseGitService {
    private final static String GITLAB_PLATFORM = "gitlab";

    protected final GitlabApiRequestCreator gitlabApiRequestCreator;

    public GitLabService(GitlabApiRequestCreator gitlabApiRequestCreator) {
        this.gitlabApiRequestCreator = gitlabApiRequestCreator;
    }

    @Override
    public boolean supports(String gitPlatform) {
        return GITLAB_PLATFORM.equalsIgnoreCase(gitPlatform);
    }

    protected Map<String, String> pageableToQueryParams(Pageable pageable) {
        return Map.of(
                "page", String.valueOf(pageable.getPageNumber()),
                "per_page", String.valueOf(pageable.getPageSize())
        );
    }

    protected String ownerRepoToId(String owner, String repo) {
        return URLEncoder.encode(owner + "/" + repo,
                StandardCharsets.UTF_8);
    }
}
