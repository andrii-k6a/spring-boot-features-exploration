package org.kook.spring.boot.exploration.singularity.starter.autoconfigure;

import org.junit.jupiter.api.Test;
import org.kook.spring.boot.exploration.singularity.starter.DummyObjectSingularity;
import org.kook.spring.boot.exploration.singularity.starter.ObjectSingularity;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.context.properties.bind.validation.BindValidationException;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

class ObjectSingularityAutoConfigurationIntegrationTest {

    @Test
    void shouldLoadContextWithObjectSingularityAutoConfigs() {
        new ApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(ObjectSingularityAutoConfiguration.class))
                .run(context -> assertThat(context).hasNotFailed()
                        .hasSingleBean(DummyObjectSingularity.class)
                        .hasSingleBean(ObjectSingularityProperties.class)
                        .getBean(ObjectSingularityProperties.class).hasNoNullFieldsOrProperties());
    }

    @Test
    void shouldFailWebContextLoadingWithInvalidPrefix() {
        new ApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(ObjectSingularityAutoConfiguration.class))
                .withPropertyValues(
                        "org.kook.spring.boot.exploration.object.singularity.secret.magic-number=420000"
                )
                .run(context -> assertThat(context).hasFailed()
                        .getFailure()
                        .hasRootCauseInstanceOf(BindValidationException.class)
                        .hasStackTraceContaining("magic-number"));
    }

    @Test
    void shouldApplyObjectSingularityProvidedByUser() {
        new ApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(ObjectSingularityAutoConfiguration.class))
                .withUserConfiguration(ObjectSingularityTestConfigs.class)
                .run(context -> assertThat(context).hasNotFailed()
                        .hasSingleBean(ObjectSingularity.class)
                        .hasSingleBean(TestObjectSingularity.class));
    }

    private static class ObjectSingularityTestConfigs {
        @Bean
        public ObjectSingularity objectSingularity() {
            return new TestObjectSingularity();
        }
    }

    private static class TestObjectSingularity implements ObjectSingularity {
        @Override
        public String get() {
            return "singularity";
        }
    }
}
