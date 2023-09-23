package com.assessment.wisanimabasa.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Config api.
 *  @author Wisani Mabasa
 *   @Since 22-sep-2023
 */
@Configuration
public class ConfigApi {
    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("api")
                .pathsToMatch("/api/**")
                .build();
    }
}
