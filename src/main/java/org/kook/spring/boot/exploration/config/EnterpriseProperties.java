package org.kook.spring.boot.exploration.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties("enterprise")
@RequiredArgsConstructor
@Getter
@ToString
public class EnterpriseProperties {
    private final String name;
    private final String greeting;
}
