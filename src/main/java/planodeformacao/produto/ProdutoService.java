package planodeformacao.produto;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        if (id == null) {
            throw new IllegalArgumentException("ID do produto não pode ser nulo.");
        }

        for (Produto produto : produtos) {
            UUID idProduto = produto.getId();
            if (idProduto != null && idProduto.equals(id)) {
                return produto;
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado com o ID: " + id);
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

    public void deletarProduto(UUID id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID do produto é necessário para realizar a exclusão.");
        }
        Produto produtoDeletado = buscarProduto(id);
        if (produtoDeletado != null) {
            if (!produtos.contains(produtoDeletado)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto com ID" + id + "ja foi deletado anteriormente");

            }
            produtos.remove(produtoDeletado);
            logger.info("ID: " + produtoDeletado.getId() + " " + produtoDeletado.getNome() + " " + produtoDeletado.getPreco() + " foi deletado com sucesso");

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto com ID" + id + "não foi encontrado");

        }
    }

    public List<Produto> buscarProdutosPorNome(String nome) {
        List<Produto> produtosEncontrados = new ArrayList<>();
        boolean produtoEncontrado = false;
        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nome)) {
                produtosEncontrados.add(produto);
                produtoEncontrado = true;
            }
        }
        if (!produtoEncontrado) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto com nome " + nome + " não foi encontrado");
        }
        return produtosEncontrados;
    }

    public Produto buscarProdutoPorUsuario(UUID userId) {
        for (Produto produto : produtos) {
            if (produto.getId().equals(userId)) {
                return produto;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto com ID " + userId + " não foi encontrado");
    }
}








