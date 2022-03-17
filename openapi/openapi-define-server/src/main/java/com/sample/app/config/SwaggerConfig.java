package com.sample.app.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Demo Appication",
                version = "1.0.0",
                description = "Demo Appication",
                contact = @Contact(name = "Java tutorial team", email = "test@test.com")
        ),
        servers = {
        		@Server(url = "http://localhost:8080", description = "local server"),
        		@Server(url = "https://chat.dev.com", description = "Development server"),
                @Server(url = "https://chat.qa.com", description = "QA server"),
                @Server(url = "https://chat.prod.com", description = "prod server")
                },
        security = {
                @SecurityRequirement(name = "serverName"),
                @SecurityRequirement(name ="key")
        }
)
@SecuritySchemes(value = {
        @SecurityScheme(name = "serverName",
                type = SecuritySchemeType.APIKEY,
                in = SecuritySchemeIn.HEADER,
                paramName = "server.name",
                description = "server name to authenticate"),
        @SecurityScheme(name = "key",
                type = SecuritySchemeType.APIKEY,
                in = SecuritySchemeIn.HEADER,
                paramName = "server.key",
                description = "server key to authenticate")
})
public class SwaggerConfig {

}
