package com.parnasit.gitparse.core.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "com.parnasit.gitparse.core",
        "com.parnasit.gitparse.gitlab",
        "com.parnasit.gitparse.github"})
public class GitlabServiceConfiguration {

//    @Bean
//    public ObjectMapper objectMapper() {
//        return new ObjectMapper().registerModule(new JavaTimeModule());
//    }
}
