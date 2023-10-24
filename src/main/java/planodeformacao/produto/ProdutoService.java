package planodeformacao.produto;


import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import planodeformacao.produto.exception.ParametrosInvalidosException;
import planodeformacao.produto.exception.ProdutoNaoEncontradoException;
import planodeformacao.produto.repository.ProdutoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final Logger logger = LogManager.getLogger(ProdutoService.class);
    private final List<Produto> produtos = new ArrayList<>();

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostConstruct
    public void carregarDadosIniciaisDoBancoParaListaLocal() {
        produtos.clear();
        produtos.addAll(produtoRepository.findAll());
    }

    public Produto criarProduto(Produto produto) {

        if (produto.getId() == null) {
            produto.setId(UUID.randomUUID());

        }

        if (produtos.stream().anyMatch(p -> p.getId().equals(produto.getId()))) {
            throw new ParametrosInvalidosException("Produto já existe com mesmo ID cadastrado");
        }


        produtos.add(produto);
        logger.info("Produto cadastrado: " + produto.getId() + " " + produto.getNome() + " " + produto.getPreco());
        return produtoRepository.save(produto);
    }

    public List<Produto> listarProdutos() {
        if (produtos.isEmpty()) {
            logger.info("Não existem produtos cadastrados");
            throw new ProdutoNaoEncontradoException("Não existem produtos cadastrados");
        }
        return produtoRepository.findAll();
    }

    public Produto buscarProduto(UUID id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto com ID " + id + " não foi encontrado"));

    }

    public Produto atualizarProduto(UUID id, Produto produto) {

        Produto produtoAtualizado = buscarProduto(id);

        produtoAtualizado.setNome(produto.getNome());
        produtoAtualizado.setPreco(produto.getPreco());
        logger.info("Produto atualizado: " + produtoAtualizado.getId() + " " + produtoAtualizado.getNome()
                + " " + produtoAtualizado.getPreco());
        return produtoRepository.save(produtoAtualizado);

    }


    public void deletarProduto(UUID id) {
        if (id == null) {
            throw new ParametrosInvalidosException("ID não pode ser vazio ou inválido");
        }
        Produto produtoDeletado = buscarProduto(id);
        if (produtoDeletado != null) {
            produtoRepository.delete(produtoDeletado);
            produtos.remove(produtoDeletado);
            logger.info("Produto com ID: " + id + " foi deletado com sucesso");
        } else {
            throw new ProdutoNaoEncontradoException("Produto com ID " + id + " não foi encontrado");

        }
    }

    public List<Produto> buscarProdutosPorNome(String nome) {
        List<Produto> produtos = produtoRepository.findByNomeIgnoreCase(nome);
        if (produtos.isEmpty()) {
            throw new ProdutoNaoEncontradoException("Produto com nome " + nome + " não foi encontrado");
        }
        return produtos;
    }


    public Produto buscarProdutoPorUsuario(UUID id) {

        try {
            UUID uuid = UUID.fromString(id.toString());
        } catch (IllegalArgumentException e) {
            throw new ParametrosInvalidosException("ID não pode ser vazio ou inválido");
        }

        return produtoRepository.findByUserId(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto com ID " + id+ " não foi encontrado"));
    }

}








