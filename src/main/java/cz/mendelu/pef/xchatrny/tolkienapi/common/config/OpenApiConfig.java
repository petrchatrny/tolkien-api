package cz.mendelu.pef.xchatrny.tolkienapi.common.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger documentation configuration
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Tolkien API")
                        .version("1.0")
                        .description("This web is a backend REST API for my Android application")
                        .contact(
                                new Contact()
                                        .name("developer - Petr Chatrný")
                                        .email("p.chatrny@protonmail.com")
                        )
                        .license(
                                new License()
                                        .name("MIT")
//                                        .url("https://akela.mendelu.cz/~xchatrny/soubory/licences/tolkien_dictionary_api.txt")
                        )
                );
    }
}