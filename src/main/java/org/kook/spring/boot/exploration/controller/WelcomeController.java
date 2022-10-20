package org.kook.spring.boot.exploration.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class WelcomeController {

    @GetMapping
    public String welcome() {
        log.info("Getting welcome message...");
        return "Welcome!";
    }

}
