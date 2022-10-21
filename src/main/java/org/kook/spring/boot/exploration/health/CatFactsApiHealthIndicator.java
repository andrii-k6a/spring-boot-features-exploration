package org.kook.spring.boot.exploration.health;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.ExecutionException;

/**
 * Custom health check component for checking 3rd party service availability.
 * The result will affect the general health status (${basePath}/actuator/health) of the app.
 * <p>
 * In order to see health details the following property has to be present in configs:<br>
 * <i>management.endpoint.health.show-details=always</i>
 * <p>
 * @see org.springframework.boot.actuate.health.HealthIndicator
 * @see Component
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class CatFactsApiHealthIndicator implements HealthIndicator {

    public static final String CAT_FACTS_API = "Cat Facts API";
    public static final String OK_STATUS = "Up and Running";
    public static final String DOWN_STATUS = "Oops, cat facts service is unavailable";

    private final HttpClient httpClient;
    private final HttpRequest catFactsHealthCheckRequest;

    @Override
    public Health health() {
        if (isCatFactsServiceUp()) {
            return Health.up()
                    .withDetail(CAT_FACTS_API, OK_STATUS)
                    .build();
        }
        return Health.down()
                .withDetail(CAT_FACTS_API, DOWN_STATUS)
                .build();
    }

    private boolean isCatFactsServiceUp() {
        try {
            log.info("Getting cat facts service status...");
            return httpClient.sendAsync(catFactsHealthCheckRequest, BodyHandlers.ofString())
                    .thenApply(HttpResponse::statusCode)
                    .thenApply(this::isOkStatus)
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Cannot get cat facts service status", e);
            return false;
        }
    }

    private boolean isOkStatus(int statusCode) {
        log.info("Cat facts service status - {}", statusCode);
        return statusCode == HttpStatus.OK.value();
    }

}
