package planodeformacao.produto.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import planodeformacao.produto.Produto;

public interface ProdutoRepository extends MongoRepository<Produto, String> {



}
