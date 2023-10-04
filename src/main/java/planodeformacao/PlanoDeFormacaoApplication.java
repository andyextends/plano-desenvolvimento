package planodeformacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import planodeformacao.produto.Produto;
import planodeformacao.produto.ProdutoService;

@SpringBootApplication
@ComponentScan (basePackages = {"planodeformacao.produto"})
public class PlanoDeFormacaoApplication {

	public static void main(String[] args) {

		SpringApplication.run(PlanoDeFormacaoApplication.class, args);

	}

}
