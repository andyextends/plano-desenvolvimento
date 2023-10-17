package planodeformacao.produto;


import jakarta.validation.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Service
public class ProdutoService {
    private final Validator validator;

    @Autowired
    public ProdutoService(Validator validator) {
        this.validator = validator;


    }

    public void validarProduto(Produto produto) {
        Set<jakarta.validation.ConstraintViolation<Produto>> violations = validator.validate(produto);
        if (!violations.isEmpty()) {
            throw new ValidacaoException("Produto inválido");
        }
    }

    private final Logger logger = LogManager.getLogger(ProdutoService.class);
    private final List<Produto> produtos = new ArrayList<>();

    public Produto criarProduto(Produto produto) {

        if (produto.getId() == null) {
            produto.setId(UUID.randomUUID());
        } else {
            throw new IllegalArgumentException("Produto já existe");
        }


        produtos.add(produto);
        logger.info("Produto cadastrado: " + produto.getId() + " " + produto.getNome() + " " + produto.getPreco());
        return produto;
    }

    public List<Produto> listarProdutos() {

        return produtos;
    }

    public Produto buscarProduto(UUID id) {
        for (Produto produto : produtos) {
            UUID idProduto = produto.getId();
            if (idProduto != null && produto.getId().equals(id)) {
                return produto;
            }
        }
        return null;
    }

    public Produto atualizarProduto(UUID id, Produto produto) {
        try {
            Produto produtoAtualizado = buscarProduto(id);
            if (produtoAtualizado != null) {
                produtoAtualizado.setNome(produto.getNome());
                produtoAtualizado.setPreco(produto.getPreco());
                logger.info("Produto atualizado: " + produtoAtualizado.getId() + " " + produtoAtualizado.getNome()
                        + " " + produtoAtualizado.getPreco());
            } else {

                throw new IllegalArgumentException(" Produto não encontrado com o ID" + id);
            }
            return produtoAtualizado;
        } catch (IllegalArgumentException | IllegalStateException e) {

            throw new RuntimeException("Erro ao atualizar produto" + e.getMessage());
        }
    }

    public boolean deletarProduto(UUID id) {
        try {


            Produto produtoDeletado = buscarProduto(id);
            if (produtoDeletado != null) {

                produtos.remove(produtoDeletado);

                logger.info(produtoDeletado.getNome() + " foi removido com sucesso.");
                return true;
            } else {
                logger.info("Produto já foi deletado com o ID" + "" + id);
                return false;
            }

        } catch (IllegalArgumentException | IllegalStateException e) {

            throw new RuntimeException("Erro ao deletar produto" + e.getMessage());
        }
    }
}








