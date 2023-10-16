package planodeformacao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"planodeformacao.produto"})
public class PlanoDeFormacaoApplication {
    private static final Logger logger = LoggerFactory.getLogger(PlanoDeFormacaoApplication.class);

    public static void main(String[] args) {
        logger.info("Produto não encontrado");
        SpringApplication.run(PlanoDeFormacaoApplication.class, args);

    }

}
