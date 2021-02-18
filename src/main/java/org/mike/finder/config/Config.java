package org.mike.finder.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "some.config")
public class Config {

    private String value = "DEFAULT VALUE";
}
