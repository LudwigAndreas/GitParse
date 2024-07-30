package com.parnasit.gitparse.core.controller;


import com.parnasit.gitparse.core.dto.IssueResponse;
import com.parnasit.gitparse.core.dto.PipelineResponse;
import com.parnasit.gitparse.core.dto.ProjectResponse;
import com.parnasit.gitparse.core.dto.StatisticsResponse;
import com.parnasit.gitparse.core.service.CoreFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class CoreController {

    private final Logger logger = LoggerFactory.getLogger(CoreController.class);

    private final CoreFacade coreFacade;

    public CoreController(CoreFacade coreFacade) {
        this.coreFacade = coreFacade;
    }


    @GetMapping("/{gitPlatform}")
    public ResponseEntity<List<ProjectResponse>> listProjects(@PathVariable String gitPlatform, Pageable pageable) {
        List<ProjectResponse> projectResponses = coreFacade.listProjects(gitPlatform, pageable);
        return ResponseEntity.ok(projectResponses);
    }


    @GetMapping("/{gitPlatform}/{owner}/{repository}")
    public ResponseEntity<ProjectResponse> getProject(@PathVariable String gitPlatform, @PathVariable String owner, @PathVariable String repository) {
        ProjectResponse projectResponse = coreFacade.getRepository(gitPlatform, owner, repository);
        return ResponseEntity.ok(projectResponse);
    }


    @PostMapping("/{gitPlatform}/{owner}/{repository}/members")
    public ResponseEntity<ProjectResponse> addContributor(@PathVariable String gitPlatform,
                                                          @PathVariable String owner,
                                                          @PathVariable String repository,
                                                          @RequestParam String username,
                                                          @RequestParam(required = false) String role,
                                                          @RequestParam(required = false) String expiration) {
        return ResponseEntity.ok(coreFacade.addContributor(gitPlatform, owner, repository, username, role, expiration));
    }

    //TODO: add sort, filter
    @GetMapping("/{gitPlatform}/{owner}/{repository}/statistics")
    public ResponseEntity<List<StatisticsResponse>> getRepositoryStatistics(@PathVariable String gitPlatform,
                                                                            @PathVariable String owner,
                                                                            @PathVariable String repository,
//                                                                            @RequestParam(required = false) Integer page,
//                                                                            @RequestParam(required = false) Integer per_page,
//                                                                            @RequestParam(required = false) String sort,
//                                                                            @RequestParam(required = false) String committer,
//                                                                            @RequestParam(required = false) String branch,
//                                                                            @RequestParam(required = false) Date from,
//                                                                            @RequestParam(required = false) Date to,
//                                                                            @RequestParam(required = false) Date ,
                                                                            Pageable pageable
    ) {
//        logger.info("getRepositoryStatistics: gitPlatform={}, owner={}, repository={}, page={}, per_page={}, sort={}, filters={}", gitPlatform, owner, repository, page, per_page, sort, filters);
        List<StatisticsResponse> statisticsResponse = coreFacade.getRepositoryStatistics(gitPlatform, owner, repository, pageable);
        return ResponseEntity.ok(statisticsResponse);
    }


    //TODO: add sort, filter
    @GetMapping("/{gitPlatform}/{owner}/{repository}/issues")
    public ResponseEntity<List<IssueResponse>> getRepositoryIssues(@PathVariable String gitPlatform, @PathVariable String owner, @PathVariable String repository, Pageable pageable) {
//        Page<IssueResponse> issueResponses = coreFacade.getRepositoryIssues(gitPlatform, owner, repository, pageable);
//        return ResponseEntity.ok(issueResponses);
        return ResponseEntity.ok(null);
    }


    @GetMapping("/{gitPlatform}/{owner}/{repository}/issues/{issueId}")
    public ResponseEntity<IssueResponse> getIssueInfo(@PathVariable String gitPlatform, @PathVariable String owner, @PathVariable String repository, String issueId) {
//        IssueResponse issueResponse = coreFacade.getIssueInfo(repositoryId, issueId);
        return ResponseEntity.ok(null);
    }

    //TODO: add sort, filter, pagination
    @GetMapping("/{gitPlatform}/{owner}/{repository}/pipelines")
    public ResponseEntity<List<PipelineResponse>> getRepositoryPipelines(@PathVariable String gitPlatform, @PathVariable String owner, @PathVariable String repository, Pageable pageable) {
//        Page<PipelineResponse> pipelineResponses = coreFacade.getRepositoryPipelines(repositoryId, pageable);
        return ResponseEntity.ok(null);
    }


    @GetMapping("/{gitPlatform}/{owner}/{repository}/pipelines/{pipelineId}")
    public ResponseEntity<PipelineResponse> getPipelineInfo(@PathVariable String gitPlatform, @PathVariable String owner, @PathVariable String repository, String pipelineId) {
//        PipelineResponse pipelineResponse = coreFacade.getPipelineInfo(repositoryId, pipelineId);
        return ResponseEntity.ok(null);
    }
}
