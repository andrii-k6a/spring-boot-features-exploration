package org.kook.spring.boot.exploration.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties("spring.datasource")
@RequiredArgsConstructor
@Getter
@ToString
public class DBProperties {
    private final String driverClassName;
    private final String url;
    private final String userName;
    @ToString.Exclude
    private final String password;
}
