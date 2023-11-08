package com.example.integrate_with_backend_task2;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "integration_mock_service",
                description = "Hello rwaaaaaaaaaan",
                version = "v1"
        ))
public class IntegrateWithBackendTask2Application {

    public static void main(String[] args) {
        SpringApplication.run(IntegrateWithBackendTask2Application.class, args);
    }

}
