package planodeformacao.produto.repository;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import planodeformacao.produto.Produto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoRepository extends MongoRepository<Produto, String> {
    Optional<Produto> findById(UUID id);

    @Query("{ 'id' : ?0 }")
    Optional<Produto> findByUserId(UUID id);


    List<Produto> findByNomeIgnoreCase(String nome);
}
