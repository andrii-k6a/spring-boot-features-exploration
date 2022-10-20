package org.kook.spring.boot.exploration.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.String.format;
import static java.lang.String.join;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WelcomeController {

    public static final String WELCOME_MESSAGE_TEMPLATE = "Welcome you at %s! %s";

    private final Environment env;
    @Value("${enterprise.name}")
    private String name;
    @Value("${enterprise.greeting}")
    private String greeting;

    @GetMapping
    public String welcome() {
        log.info("Getting welcome message for the following profiles: {}", activeProfiles());
        return format(WELCOME_MESSAGE_TEMPLATE, name, greeting);
    }

    private String activeProfiles() {
        String allActiveProfiles = join(", ", env.getActiveProfiles());
        return allActiveProfiles.isBlank() ? "None, using default params" : allActiveProfiles;
    }

}
