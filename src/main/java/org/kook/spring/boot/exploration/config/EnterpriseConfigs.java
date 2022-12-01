package org.kook.spring.boot.exploration.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
@EnableConfigurationProperties(EnterpriseProperties.class)
public class EnterpriseConfigs {

    private final EnterpriseProperties enterpriseProperties;

    public EnterpriseConfigs(EnterpriseProperties enterpriseProperties) {
        this.enterpriseProperties = enterpriseProperties;
    }

    @Bean
    @Profile("dev")
    public String enterpriseInfoDev() {
        log.info("Enterprise properties for DEV: {}", enterpriseProperties);
        return enterpriseProperties.toString();
    }

    @Bean
    @Profile("qa")
    public String enterpriseInfoQa() {
        log.info("Enterprise properties for QA: {}", enterpriseProperties);
        return enterpriseProperties.toString();
    }

    @Bean
    @Profile("prod")
    public String enterpriseInfoProd() {
        log.info("Enterprise properties for PROD: {}", enterpriseProperties);
        return enterpriseProperties.toString();
    }

}
