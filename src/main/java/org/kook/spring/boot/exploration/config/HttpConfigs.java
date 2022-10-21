package org.kook.spring.boot.exploration.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

@Configuration
public class HttpConfigs {

    @Value("${enterprise.cat.facts.uri}")
    private String catFactsUri;

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newHttpClient();
    }

    @Bean
    public HttpRequest catFactsHealthCheckRequest() {
        return HttpRequest.newBuilder()
                .uri(URI.create(catFactsUri))
                .GET()
                .build();
    }

}
