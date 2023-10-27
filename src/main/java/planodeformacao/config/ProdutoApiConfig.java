package planodeformacao.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration

public class ProdutoApiConfig {
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("API Plano de Formacao - Esta API permite gerenciar produtos e praticar os conceitos de Spring Boot")
                        .version("V1")
                        .description("API de Produtos"));

    }
}
