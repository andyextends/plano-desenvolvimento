package planodeformacao.produto;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    public ProdutoService() {


    }

    public List<Produto> produtos = new ArrayList<>();

    public Produto criarProduto(Produto produto) {
        produto.setId(System.currentTimeMillis());
        produtos.add(produto);
        return produto;
    }

    public List<Produto> listarProdutos() {

        return produtos;
    }

    public Produto buscarProduto(Long id) {
        for (Produto produto : produtos) {
            if (produto.getId().equals(id)) {
                return produto;
            }
        }
        return null;
    }

    public Produto atualizarProduto(Long id, Produto produto) {
        Produto produtoAtualizado = buscarProduto(id);
        if (produtoAtualizado != null) {
            produtoAtualizado.setNome(produto.getNome());
            produtoAtualizado.setPreco(produto.getPreco());
        }
        return produtoAtualizado;
    }

    public void deletarProduto(Long id) {
        Produto produtoDeletado = buscarProduto(id);
        if (produtoDeletado != null) {
            produtos.remove(produtoDeletado);
            System.out.println(produtoDeletado.getNome() + "  foi removido com sucesso.");
        } else {
            System.out.println(produtoDeletado.getNome() + " não foi encontrado.");
        }


    }
}