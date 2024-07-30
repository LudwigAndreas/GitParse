package com.parnasit.gitparse.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GithubCommitResponse {

    private String url;
    private String sha;
    @JsonProperty("node_id")
    private String nodeId;
    @JsonProperty("html_url")
    private String htmlUrl;
    @JsonProperty("comments_url")
    private String commentsUrl;
    private Commit commit;
    private ShallowAuthor author;
    private ShallowAuthor committer;
    private ArrayList<Tree> parents;
    public Stats stats;
    public ArrayList<File> files;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ShallowAuthor {
        private String name;
        private String email;
        private LocalDateTime date;
        private String login;
        private int id;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("avatar_url")
        private String avatarUrl;
        @JsonProperty("gravatar_id")
        private String gravatarId;
        private String url;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("followers_url")
        private String followersUrl;
        @JsonProperty("following_url")
        private String followingUrl;
        @JsonProperty("gists_url")
        private String gistsUrl;
        @JsonProperty("starred_url")
        private String starredUrl;
        @JsonProperty("subscriptions_url")
        private String subscriptionsUrl;
        @JsonProperty("organizations_url")
        private String organizationsUrl;
        @JsonProperty("repos_url")
        private String reposUrl;
        @JsonProperty("events_url")
        private String eventsUrl;
        @JsonProperty("received_events_url")
        private String receivedEventsUrl;
        private String type;
        @JsonProperty("site_admin")
        private boolean siteAdmin;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Commit {
        private String url;
        private ShallowAuthor author;
        private ShallowAuthor committer;
        private String message;
        private Tree tree;
        @JsonProperty("comment_count")
        private int commentCount;
        private Verification verification;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Tree {
        private String url;
        private String sha;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Verification {
        private boolean verified;
        private String reason;
        private Object signature;
        private Object payload;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Stats {
        private int additions;
        private int deletions;
        private int total;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class File {
        private String filename;
        private int additions;
        private int deletions;
        private int changes;
        private String status;
        @JsonProperty("raw_url")
        private String rawUrl;
        @JsonProperty("blob_url")
        private String blobUrl;
        private String patch;
    }
}
