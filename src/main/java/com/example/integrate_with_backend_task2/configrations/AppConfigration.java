package com.example.integrate_with_backend_task2.configrations;

import com.example.integrate_with_backend_task2.entity.InputData;
import com.example.integrate_with_backend_task2.model.OutputData;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfigration {
    @Bean
    public OutputData outputData() {
        return new OutputData();
    }
    @Bean
    public InputData inputData() {
        return new InputData();
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public OpenApiCustomizer myOpenApiCustomiser() {
        return openApi -> {

            // Add a global tag
            Tag tag = new Tag();
            tag.setName("My Custom Tag");
            tag.setDescription("This is a custom tag for the API");

            openApi.addTagsItem(tag);
        };
    }

}
