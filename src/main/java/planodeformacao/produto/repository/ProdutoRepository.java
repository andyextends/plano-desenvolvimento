package planodeformacao.produto.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import planodeformacao.produto.model.Produto;


import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends MongoRepository<Produto, String> {
    Optional<Produto> findById(String id);

    @Query("{ 'id' : ?0 }")
    Optional<Produto> findByUserId( String id);


    List<Produto> findByNomeIgnoreCase(String nome);
}
