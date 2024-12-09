package gift.exchange.infrastructure.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("\uD83D\uDD6F️ Backend Challenge (Java): Gift Exchange API \uD83C\uDF81")
                        .description("\uD83D\uDC9F RESTful API to organize a Christmas gift exchange ⭐")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Orli Dun")
                                .email("support@giftexchange.com")
                                .url("http://localhost:8080/swagger-ui.html"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("Complete Documentation")
                        .url("https://github.com/Orliluq/exchange.git"));
    }
}
