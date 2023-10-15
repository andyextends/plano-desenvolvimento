package planodeformacao.produto;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProdutoService {


    public ProdutoService() {


    }

    public List<Produto> produtos = new ArrayList<>();

    public Produto criarProduto(Produto produto) {
        if (produto.getId() == null) {
            produto.setId(UUID.randomUUID());
        }

        produtos.add(produto);
        System.out.println("Produto cadastrado: " + produto.getId() + " " + produto.getNome() + " " + produto.getPreco());
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
            } else {
                throw new IllegalArgumentException("Produto não encontrado com o ID" + id);
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

                System.out.println(produtoDeletado.getNome() + " foi removido com sucesso.");
                return true;
            } else {
                System.out.println("Produto já foi deletado com o ID" + "" + id);
                return false;
            }

        } catch (IllegalArgumentException | IllegalStateException e) {
            throw new RuntimeException("Erro ao deletar produto" + e.getMessage());
        }
    }
}








