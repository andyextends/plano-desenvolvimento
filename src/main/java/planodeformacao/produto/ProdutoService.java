package planodeformacao.produto;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class ProdutoService {


    public ProdutoService() {
    }

    private final Logger logger = LogManager.getLogger(ProdutoService.class);
    private final List<Produto> produtos = new ArrayList<>();

    public Produto criarProduto(Produto produto) {

        if (produto.getId() == null) {
            produto.setId(UUID.randomUUID());

        }
        for (Produto existeProduto : produtos) {
            if (existeProduto.getId().equals(produto.getId())) {
                throw new IllegalArgumentException("Produto já existe com mesmo ID cadastrado");
            }
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

        Produto produtoAtualizado = buscarProduto(id);
        if (produtoAtualizado != null) {
            produtoAtualizado.setNome(produto.getNome());
            produtoAtualizado.setPreco(produto.getPreco());
            logger.info("Produto atualizado: " + produtoAtualizado.getId() + " " + produtoAtualizado.getNome()
                    + " " + produtoAtualizado.getPreco());
            return produtoAtualizado;
        } else {

            throw new IllegalArgumentException(" não encontramos o ID: " + id);
        }


    }

    public boolean deletarProduto(UUID id) {


        Produto produtoDeletado = buscarProduto(id);
        if (produtoDeletado != null) {

            produtos.remove(produtoDeletado);

            logger.info(produtoDeletado.getNome() + " foi removido com sucesso.");
            return true;
        } else {
            logger.info("Produto já foi deletado com o ID" + "" + id);
            return false;
        }


    }
}








