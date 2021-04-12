package com.aaron.AA2_AD.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BibliotecaConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Biblioteca API")
                            .description("Ejercicio de API REST")
                            .contact(new Contact()
                                        .name("Aarón")
                                        .email("a24922@svalero.com"))
                .version("1.0"));
    }
}