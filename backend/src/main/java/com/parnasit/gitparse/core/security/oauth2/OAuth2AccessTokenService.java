package com.parnasit.gitparse.core.security.oauth2;

public interface OAuth2AccessTokenService {
    /**
     * The method returns access token that client gets after oauth2 authorization.
     * @param registrationId registrationId form {@link org.springframework.security.oauth2.client.registration.ClientRegistration}
     * that equals to client identifier at oauth2 authorization server.
     * @return an access token as {@code String}. The access token is contained at {@link org.springframework.security.oauth2.client.OAuth2AuthorizedClient}.
     */
    String getAccessToken(String registrationId);
}
