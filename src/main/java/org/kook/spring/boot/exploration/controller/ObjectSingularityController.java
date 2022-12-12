package org.kook.spring.boot.exploration.controller;

import lombok.RequiredArgsConstructor;
import org.kook.spring.boot.exploration.singularity.starter.ObjectSingularity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ObjectSingularityController {

    private final ObjectSingularity objectSingularity;

    @GetMapping("/singularity")
    public String singularity() {
        return objectSingularity.get();
    }
}
