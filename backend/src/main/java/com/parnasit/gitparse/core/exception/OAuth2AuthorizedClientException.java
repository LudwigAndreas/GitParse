package com.parnasit.gitparse.core.exception;

public class OAuth2AuthorizedClientException extends RuntimeException {
    public OAuth2AuthorizedClientException() {
        super();
    }

    public OAuth2AuthorizedClientException(String message) {
        super(message);
    }

    public OAuth2AuthorizedClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public OAuth2AuthorizedClientException(Throwable cause) {
        super(cause);
    }

    protected OAuth2AuthorizedClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
