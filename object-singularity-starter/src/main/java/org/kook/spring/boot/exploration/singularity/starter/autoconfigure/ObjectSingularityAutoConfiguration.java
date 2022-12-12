package org.kook.spring.boot.exploration.singularity.starter.autoconfigure;

import org.kook.spring.boot.exploration.singularity.starter.DummyObjectSingularity;
import org.kook.spring.boot.exploration.singularity.starter.ObjectSingularity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(ObjectSingularityProperties.class)
@ConditionalOnProperty(value = "org.kook.spring.boot.exploration.object.singularity.enabled", havingValue = "true", matchIfMissing = true)
public class ObjectSingularityAutoConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(ObjectSingularityAutoConfiguration.class);

    @Bean
    @ConditionalOnMissingBean
    public ObjectSingularity objectSingularity(ObjectSingularityProperties properties,
                                               @Value("${org.kook.spring.boot.exploration.object.singularity.author:unknown}") String author) {
        String prefix = properties.getPrefix();
        int magicNumber = properties.getSecret().getMagicNumber();

        LOG.info("Object singularity configs: prefix - {}; magic-number - {}; author - {}", prefix, magicNumber, author);

        return new DummyObjectSingularity(prefix, magicNumber);
    }
}
