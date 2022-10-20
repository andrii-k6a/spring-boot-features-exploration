package org.kook.spring.boot.exploration.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
@EnableConfigurationProperties(DBProperties.class)
public class DBConnectionConfigs {

    private final DBProperties dbProperties;

    public DBConnectionConfigs(DBProperties dbProperties) {
        this.dbProperties = dbProperties;
    }

    @Bean
    @Profile("dev")
    public String dbInfoDev() {
        log.info("DB properties for DEV: {}", dbProperties);
        return dbProperties.toString();
    }

    @Bean
    @Profile("qa")
    public String dbInfoQa() {
        log.info("DB properties for QA: {}", dbProperties);
        return dbProperties.toString();
    }

    @Bean
    @Profile("prod")
    public String dbInfoProd() {
        log.info("DB properties for PROD: {}", dbProperties);
        return dbProperties.toString();
    }

}
