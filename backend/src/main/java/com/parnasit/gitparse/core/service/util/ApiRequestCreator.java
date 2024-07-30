package com.parnasit.gitparse.core.service.util;

import com.parnasit.gitparse.github.exception.GitHubApiException;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for sending API requests.
 */
@Getter
public abstract class ApiRequestCreator {

    protected final WebClient webClient;

    protected ApiRequestCreator(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Sends a request and receives a list
     *
     * @param path        Endpoint
     * @param clazz       Class of expected elements
     * @param method      Http method
     * @param queryParams Query params
     * @return List of type clazz elements
     */
    public <T> List<T> sendRequestForList(String path, Class<T> clazz,
                                          HttpMethod method,
                                          Map<String, String> queryParams) {
        return sendRequest(path, method, queryParams,
                new LinkedMultiValueMap<>())
                .bodyToFlux(clazz)
                .collectList()
                .block();
    }

    /**
     * Sends a request and receives a list
     *
     * @param path        Endpoint
     * @param clazz       Class of expected elements
     * @param method      Http method
     * @param queryParams Query params
     * @param fromData    Form data
     * @return List of type clazz elements
     */
    public <T> List<T> sendRequestForList(String path, Class<T> clazz,
                                          HttpMethod method,
                                          Map<String, String> queryParams,
                                          MultiValueMap<String, String> fromData) {
        return sendRequest(path, method, queryParams, fromData)
                .bodyToFlux(clazz)
                .collectList()
                .block();
    }

    /**
     * Sends a request and receives a list
     *
     * @param path  Endpoint
     * @param clazz Class of expected elements
     * @return List of type clazz elements
     */
    public <T> List<T> sendRequestForList(String path, Class<T> clazz) {
        return sendRequest(path, HttpMethod.GET, new HashMap<>(),
                new LinkedMultiValueMap<>())
                .bodyToFlux(clazz)
                .collectList()
                .block();
    }

    /**
     * Sends a request and receives an object
     *
     * @param path        Endpoint
     * @param clazz       Class of expected element
     * @param method      Http method
     * @param queryParams Query params
     * @return Object of type clazz
     */
    public <T> T sendRequestForObject(String path, Class<T> clazz,
                                      HttpMethod method,
                                      Map<String, String> queryParams) {
        return sendRequest(path, method, queryParams, null)
                .bodyToMono(clazz)
                .block();
    }




    /**
     * Sends a request and receives an object
     *
     * @param path  Endpoint
     * @param clazz Class of expected element
     * @param <T>   Type of object
     * @return Object of type clazz
     */
    public <T> T sendRequestForObject(String path, Class<T> clazz) {
        return sendRequest(path, HttpMethod.GET, new HashMap<>(),
                new LinkedMultiValueMap<>())
                .bodyToMono(clazz)
                .block();
    }


    public <T> T sendRequestForObject(String path, Class<T> clazz, Map<String, String> requestParams) {
        return sendRequest(path, HttpMethod.POST, requestParams, new LinkedMultiValueMap<>())
                .bodyToMono(clazz)
                .block();
    }




    /**
     * Sends a request and receives an object
     *
     * @param path   Endpoint
     * @param method Http method
     * @return Object of type clazz
     */
    protected abstract WebClient.ResponseSpec sendRequest(String path,
                                                          HttpMethod method,
                                                          Map<String, String> queryParams,
                                                          MultiValueMap<String, String> formData);

    /**
     * Sends a request and receives an object
     *
     * @param path        Endpoint
     * @param method      Http method
     * @param queryParams Query params
     * @param headers
     * @param formData    Form data
     * @return Object of type clazz
     */
    protected WebClient.ResponseSpec sendRequest(String path, HttpMethod method,
                                                 Map<String, String> queryParams,
                                                 HttpHeaders headers,
                                                 MultiValueMap<String,
                                                         String> formData) {
        return sendRequest(path, method, queryParams, headers, formData, true);
    }

    /**
     * Sends a request and receives an object
     *
     * @param path        Endpoint
     * @param method      Http method
     * @param queryParams Query params
     * @param formData    Form data
     * @return Object of type clazz
     */
    protected WebClient.ResponseSpec sendRequest(String path, HttpMethod method,
                                                 Map<String, String> queryParams,
                                                 HttpHeaders headers,
                                                 MultiValueMap<String,
                                                         String> formData,
                                                 boolean encodeUri) {
        MultiValueMap<String,
                String> multiQueryParams = new LinkedMultiValueMap<>();
        multiQueryParams.setAll(queryParams);
        return webClient
                .method(method)
                .uri(uriBuilder -> UriComponentsBuilder.fromUri(
                                uriBuilder.build())
                        .path(path)
                        .queryParams(multiQueryParams)
                        .build(encodeUri).toUri())
                .headers(httpHeaders -> {
                    httpHeaders.addAll(headers);
                })
                .bodyValue(formData)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() ||
                                status.is5xxServerError(),
                        clientResponse -> clientResponse
                                .bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(
                                        new GitHubApiException(
                                                "Error fetching from Git: " +
                                                        errorBody)))
                );
    }

}
