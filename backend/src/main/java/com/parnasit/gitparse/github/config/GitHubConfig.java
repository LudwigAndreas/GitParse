package com.parnasit.gitparse.github.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GitHubConfig {

    @Bean(name = "githubWebClient")
    public WebClient githubWebClient(@Value("${api.github.uri}") String githubUri) {
        return WebClient.builder()
                .baseUrl(githubUri)
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(clientCodecConfigurer ->
                                clientCodecConfigurer
                                        .defaultCodecs()
                                        .maxInMemorySize(16 * 1024 * 1024)
                        )
                        .build())
                .build();
    }

}
