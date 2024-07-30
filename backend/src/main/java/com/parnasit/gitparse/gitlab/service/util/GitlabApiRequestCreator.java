package com.parnasit.gitparse.gitlab.service.util;

import com.parnasit.gitparse.core.security.oauth2.OAuth2AccessTokenService;
import com.parnasit.gitparse.core.service.util.ApiRequestCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Component
@Slf4j
public class GitlabApiRequestCreator extends ApiRequestCreator {

    private final OAuth2AccessTokenService tokenService;

    protected GitlabApiRequestCreator(
            @Qualifier("gitlabWebClient") WebClient webClient,
            OAuth2AccessTokenService tokenService) {
        super(webClient);
        this.tokenService = tokenService;
    }

    protected WebClient.ResponseSpec sendRequest(String path, HttpMethod method,
                                                 Map<String, String> queryParams,
                                                 MultiValueMap<String, String> formData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION,
                "Bearer " + tokenService.getAccessToken("gitlab"));
        return sendRequest(path, method, queryParams, headers, formData);
    }

}
