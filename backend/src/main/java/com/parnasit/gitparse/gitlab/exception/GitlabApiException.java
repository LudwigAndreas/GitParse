package com.parnasit.gitparse.gitlab.exception;

public class GitlabApiException extends RuntimeException {

    public GitlabApiException(String message) {
        super(message);
    }
}
