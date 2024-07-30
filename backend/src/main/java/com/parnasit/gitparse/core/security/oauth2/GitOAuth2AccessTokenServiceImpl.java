package com.parnasit.gitparse.core.security.oauth2;

import com.parnasit.gitparse.core.exception.OAuth2AuthorizedClientException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Component;

@Component
public class GitOAuth2AccessTokenServiceImpl implements OAuth2AccessTokenService {

    private final OAuth2AuthorizedClientService service;

    public GitOAuth2AccessTokenServiceImpl(OAuth2AuthorizedClientService service) {
        this.service = service;
    }

    //TODO: if use this method twice application will throw NullPointerException
    @Override
    public String getAccessToken(String registrationId) {
        try {
            Authentication postOAuth2Authentication = getPostOAuth2Authentication();
            OAuth2AuthorizedClient authorizedClient = getOAuth2AuthorizedClient(registrationId, postOAuth2Authentication.getName());
            return authorizedClient.getAccessToken().getTokenValue();
        } catch (NullPointerException e) {
            throw new OAuth2AuthorizedClientException("Client authentication is null. Check if the client is authorized.");
        }
    }

    private Authentication getPostOAuth2Authentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private OAuth2AuthorizedClient getOAuth2AuthorizedClient(String clientRegistrationId, String principleName) {
        return service.loadAuthorizedClient(clientRegistrationId, principleName);
    }
}
