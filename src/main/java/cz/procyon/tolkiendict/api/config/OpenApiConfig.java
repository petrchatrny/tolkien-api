package cz.procyon.tolkiendict.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration of generated OpenAPI specification
 */
@Configuration
public class OpenApiConfig {

    private BuildProperties buildProperties;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Tolkien API")
                        .version(buildProperties.getVersion())
                        .description("This web is a backend REST API for my Android application")
                        .contact(
                                new Contact()
                                        .name("developer - Petr Chatrný")
                                        .email("posta@petrchatrny.cz")
                        )
                        .license(
                                new License()
                                        .name("MIT")
//                                        .url("https://akela.mendelu.cz/~xchatrny/soubory/licences/tolkien_dictionary_api.txt")
                        )
                );
    }

    @Autowired
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }
}