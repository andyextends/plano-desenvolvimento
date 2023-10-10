package planodeformacao.produto;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProdutoService {
  

    public ProdutoService() {


    }

    public List<Produto> produtos = new ArrayList<>();

    public Produto criarProduto(Produto produto) {
        long novoId = Objects.hash(produto.getNome(), produto.getPreco());
        produto.setId(novoId);
        produtos.add(produto);
        return produto;
    }

    public List<Produto> listarProdutos() {

        return produtos;
    }

    private Produto buscarProduto(Long id) {
        for (Produto produto : produtos) {
            if (produto.getId().equals(id)) {
                return produto;
            }
        }
        return null;
    }

    public Produto atualizarProduto(Long id, Produto produto) {
        try {
            Produto produtoAtualizado = buscarProduto(id);
            if (produtoAtualizado != null) {
                produtoAtualizado.setNome(produto.getNome());
                produtoAtualizado.setPreco(produto.getPreco());
            } else {
                throw new IllegalArgumentException("Produto não encontrado com o ID" + id);
            }
            return produtoAtualizado;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar produto" + e.getMessage());
        }
    }

    public boolean deletarProduto(Long id) {
        try {


            Produto produtoDeletado = buscarProduto(id);
            if (produtoDeletado != null) {

                produtos.remove(produtoDeletado);

                System.out.println(produtoDeletado.getNome() + " foi removido com sucesso.");
                return true;
            } else {
                System.out.println("Produto já foi deletado com o ID" + "" + id);
                return false;
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar produto" + e.getMessage());
        }
    }
}








