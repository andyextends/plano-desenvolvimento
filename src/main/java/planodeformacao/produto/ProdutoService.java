package planodeformacao.produto;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import planodeformacao.produto.exception.ParametrosInvalidosException;
import planodeformacao.produto.exception.ProdutoNaoEncontradoException;
import planodeformacao.produto.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final Logger logger = LogManager.getLogger(ProdutoService.class);


    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }


    public Produto criarProduto(Produto produto) {

        if (produto.getId() == null) {
            produto.setId(UUID.randomUUID());
        } else if (produtoRepository.findById(produto.getId()).isPresent()) {
            throw new ParametrosInvalidosException("Produto já existe com mesmo ID cadastrado");
        }
        logger.info("Produto criado: " + produto.getId() + " " + produto.getNome() + " " + produto.getPreco());
        return produtoRepository.save(produto);


    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        if (produtos.isEmpty()) {
            logger.info("Não há produtos cadastrados");
            throw new ProdutoNaoEncontradoException("Não há produtos cadastrados");
        }
        return produtos;
    }

    public Produto buscarProduto(UUID id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto com ID " + id + " não foi encontrado"));

    }

    public Produto atualizarProduto(UUID id, Produto produto) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto com ID " + id + " não foi encontrado"));
        if(!produto.getNome().equals(produtoExistente.getNome()) || produto.getPreco() != produtoExistente.getPreco()) {
            produtoExistente.setNome(produto.getNome());
            produtoExistente.setPreco(produto.getPreco());
            logger.info("Produto atualizado: " + produtoExistente.getId() + " "
                    + produtoExistente.getNome() + " " + produtoExistente.getPreco());
            return produtoRepository.save(produtoExistente);
        } else {
            logger.info("Produto não atualizado: " + produtoExistente.getId() + " "
                    + produtoExistente.getNome() + " " + produtoExistente.getPreco());
            throw new ParametrosInvalidosException("Produto já existe com mesmo nome e preço cadastrado");
        }

    }


    public void deletarProduto(UUID id) {
        if (id == null) {
            throw new ParametrosInvalidosException("ID não pode ser vazio ou inválido");
        }
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isPresent()) {
            Produto produtoDeletado = produtoOptional.get();
            produtoRepository.delete(produtoDeletado);
            logger.info("Produto deletado: " + produtoDeletado.getId() + " "
                    + produtoDeletado.getNome() + " " + produtoDeletado.getPreco());
        } else {
            logger.info("Produto não encontrado ou já deletado " + id);
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
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto com ID " + id + " não foi encontrado"));
    }

}








