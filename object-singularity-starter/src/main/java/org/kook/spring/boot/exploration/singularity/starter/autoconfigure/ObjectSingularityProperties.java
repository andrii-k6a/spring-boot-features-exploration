package org.kook.spring.boot.exploration.singularity.starter.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

// Javadocs could be seen in application.properties file as well as default values.
// spring-boot-configuration-processor generates metadata about classes in application
// that are annotated with @ConfigurationProperties. This metadata is used by IDE to provide auto-completion
// and documentation for the properties
@ConfigurationProperties("org.kook.spring.boot.exploration.object.singularity")
@Validated
public class ObjectSingularityProperties {

    public static class Secret {
        /**
         * Magic number to be used by object singularity
         */
        @Positive
        @Max(1_000)
        private int magicNumber = 42;

        public int getMagicNumber() {
            return magicNumber;
        }

        public void setMagicNumber(int magicNumber) {
            this.magicNumber = magicNumber;
        }
    }

    /**
     * Prefix for object singularity id
     */
    @NotBlank
    @Size(min = 2, max = 50)
    private String prefix = "obj-singularity = ";
    @Valid
    private Secret secret = new Secret();

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Secret getSecret() {
        return secret;
    }

    public void setSecret(Secret secret) {
        this.secret = secret;
    }
}
