package com.parnasit.gitparse.github.service;

import com.parnasit.gitparse.core.service.git.BaseGitService;
import com.parnasit.gitparse.github.service.util.GithubApiRequestCreator;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GitHubService implements BaseGitService {

    private final static String GITHUB_PLATFORM = "github";

    protected final GithubApiRequestCreator githubApiRequestCreator;

    public GitHubService(GithubApiRequestCreator githubApiRequestCreator) {
        this.githubApiRequestCreator = githubApiRequestCreator;
    }

    @Override
    public boolean supports(String gitPlatform) {
        return GITHUB_PLATFORM.equals(gitPlatform.toLowerCase());
    }

    protected Map<String, String> pageableToQueryParams(Pageable pageable) {
        return Map.of(
                "page", String.valueOf(pageable.getPageNumber()),
                "per_page", String.valueOf(pageable.getPageSize())
        );
    }
}
