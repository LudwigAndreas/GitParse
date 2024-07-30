package com.parnasit.gitparse.gitlab.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
@ComponentScan("com.parnasit.gitparse.gitlab")
public class GitLabConfig {

    @Bean(name = "gitlabWebClient")
    public WebClient banksWebClient(@Value("${api.gitlab.uri}") String uri) {
        return WebClient.builder()
                .baseUrl(uri)
                .build();
    }

//    @Bean(name = "gitlabObjectMapper")
//    public ObjectMapper objectMapper() {
//
//
//        return new ObjectMapper()
//                .registerModule(new JavaTimeModule());
//    }
}
