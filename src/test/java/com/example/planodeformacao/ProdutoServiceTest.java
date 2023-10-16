package com.example.planodeformacao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import planodeformacao.produto.Produto;
import planodeformacao.produto.ProdutoService;

import java.util.ArrayList;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoServiceTest {
    private ProdutoService produtoService;
    private List<Produto> produtos;


    @BeforeEach
    public void setUp() {
        produtos = criarListaProdutos();
        produtoService = new ProdutoService();

        for (Produto produto : produtos) {
            produtoService.criarProduto(produto);
        }


    }

    private List<Produto> criarListaProdutos() {
        List<Produto> lista = new ArrayList<>();
        lista.add(new Produto(UUID.randomUUID(), "Produto 1", 10.0));
        lista.add(new Produto(UUID.randomUUID(), "Produto 2", 20.0));
        lista.add(new Produto(UUID.randomUUID(), "Produto 3", 30.0));
        lista.add(new Produto(UUID.randomUUID(), "Produto 4", 40.0));
        lista.add(new Produto(UUID.randomUUID(), "Produto 5", 50.0));

        return lista;

    }

    @Test
    void criarProdutoDaLista() {
        List<Produto> produtos = produtoService.listarProdutos();
        Produto produto = new Produto(null, "Produto 6", 60.0);
        produtoService.criarProduto(produto);
        assertEquals(produtos.size(), 6);
        assertEquals(produto.getNome(), "Produto 6");
        assertEquals(produto.getPreco(), 60.0);
    }

    @Test
    void atualizarProdutoDaLista() {
        List<Produto> produtos = produtoService.listarProdutos();
        Produto produto = produtos.get(4);
        produto.setNome("Produto 5 atualizado");
        produto.setPreco(99.99);
        produtoService.atualizarProduto(produto.getId(), produto);
        assertEquals(produtos.size(), 5);
        assertEquals(produto.getNome(), "Produto 5 atualizado");
        assertEquals(produto.getPreco(), 99.99);


    }

    @Test
    void retornarListaDeProdutos() {
        List<Produto> produtos = produtoService.listarProdutos();
        assertEquals(produtos, this.produtos);
        assertEquals(produtos.size(), 5);


    }

    @Test
    void removerProdutoDaLista() {

        List<Produto> produtos = produtoService.listarProdutos();
        Produto produto = produtos.get(4);
        produtoService.deletarProduto(produto.getId());
        assertEquals(4, produtos.size());


    }


    @Test
    void buscarProdutoDaLista() {
        List<Produto> produtos = produtoService.listarProdutos();
        Produto produto = produtos.get(4);
        assertEquals(produtos.size(), 5);
        assertEquals(produto.getNome(), "Produto 5");
        assertEquals(produto.getPreco(), 50.0);

    }

    @Test
    void buscarProdutoDaListaPorId() {
        List<Produto> produtos = produtoService.listarProdutos();
        Produto produto = produtos.get(4);
        assertEquals(produtos.size(), 5);
        assertEquals(produto.getNome(), "Produto 5");
        assertEquals(produto.getPreco(), 50.0);

    }


    @Test
    void atualizarProdutoNaoEncontrado() {
        assertThrows(RuntimeException.class, () -> {

            produtoService.atualizarProduto(UUID.randomUUID(), new Produto(null, "Produto Inexistente", 60.0));
        });


    }

    @Test
    void NaoExisteProdutoNaLista() {

        List<Produto> produtos = produtoService.listarProdutos();
        Produto produto = produtos.get(4);
        produtoService.deletarProduto(produto.getId());
        produtoService.buscarProduto(produto.getId());
        assertEquals(4, produtos.size());
        assertEquals(produto.getNome(), "Produto 5");
        assertEquals(produto.getPreco(), 50.0);
        assertTrue(produtoService.buscarProduto(produto.getId()) == null);


    }


}












