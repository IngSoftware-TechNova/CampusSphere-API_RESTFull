package com.technova.campussphereapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;

//http://localhost:8080/api/v1/swagger-ui/index.html
@Configuration
public class SwaggerAPIConfig {
    @Value("${campussphere.openapi.dev-url}")
    private String devUrl;

    @Value("${campussphere.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI(){
        //Definir el servidor de desarrollo
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Development Server");

        // Definir el servidor de producción
        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        //Informacion de contacto
        Contact contact = new Contact();
        contact.setEmail("campussphere@gmail.com");
        contact.setName("CampusSphere");
        contact.setUrl("https://ingsoftware-technova.github.io/CampusSphere-LandingPage/");

        //Licencia
        License mitLicense = new License().name("MIT License").url("https://opensource.org/licenses/MIT");

        //Informacion general de la API
        Info info = new Info()
                .title("API CampusSphere tu portal de eventos universitarios")
                .version("1.0")
                .contact(contact)
                .description("API Restful de portal de eventos universitarios")
                .termsOfService("https://www.hampcode.com/terms")
                .license(mitLicense);

        // Configuración de seguridad JWT
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .name("JWT Authentication");

        Components components = new Components()
                .addSecuritySchemes("bearerAuth", securityScheme);

        // Requerimiento de seguridad para utilizar en las operaciones
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");


        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer, prodServer))
                .addSecurityItem(securityRequirement)
                .components(components);
    }

}
