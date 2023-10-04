package com.example.planodeformacao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import planodeformacao.produto.Produto;
import planodeformacao.produto.ProdutoService;

import java.util.List;

public class ProdutoServiceTest {
    private ProdutoService produtoService;

    @BeforeEach
    public void setUp() {
        produtoService = new ProdutoService();
    }

    @Test
    public void criarProduto() {
        Produto produto = new Produto(null, null, 0.0);
        produto.setNome("Produto 1");
        produto.setPreco(10.0);
        Produto produtoCriado = produtoService.criarProduto(produto);
        assertNotNull(produtoCriado);
        assertNotNull(produtoCriado.getId());
        assertEquals(produto.getNome(), produtoCriado.getNome());
        assertEquals(produto.getPreco(), produtoCriado.getPreco());
        assertEquals(produto, produtoCriado);
        assertEquals(produto.getId(), produtoCriado.getId());
        assertEquals(produtoService.criarProduto(produto), produtoCriado);
    }

    @Test
    public void listarProdutos() {
        Produto produto1 = new Produto(null, "Produto 1", 19.99);
        Produto produto2 = new Produto(null, "Produto 2", 29.99);
        Produto produto3 = new Produto(null, "Produto 3", 39.99);
        Produto produto4 = new Produto(null, "Produto 4", 49.99);
        produtoService.criarProduto(produto1);
        produtoService.criarProduto(produto2);
        produtoService.criarProduto(produto3);
        produtoService.criarProduto(produto4);
        List<Produto> produtos = produtoService.listarProdutos();
        assertNotNull(produtos);
        assertEquals(4, produtos.size());
        assertEquals(produto1, produtos.get(0));
        assertEquals(produto2, produtos.get(1));
        assertEquals(produto3, produtos.get(2));
        assertEquals(produto4, produtos.get(3));
    }

    @Test
    public void buscarProduto() {
        Produto produto1 = new Produto(null, "Produto 1", 19.99);
        Produto produto2 = new Produto(null, "Produto 2", 29.99);
        Produto produto3 = new Produto(null, "Produto 3", 39.99);
        Produto produto4 = new Produto(null, "Produto 4", 49.99);
        produtoService.criarProduto(produto1);
        produtoService.criarProduto(produto2);
        produtoService.criarProduto(produto3);
        produtoService.criarProduto(produto4);
        Produto produtoBuscado = produtoService.buscarProduto(produto2.getId());
        assertNotNull(produtoBuscado);
        assertEquals(produto3.getId(), produtoBuscado.getId());
        assertEquals(produto3.getNome(), produtoBuscado.getNome());
        assertEquals(produto2.getPreco(), produtoBuscado.getPreco());
    }

    @Test
    public void atualizarProduto() {
        Produto produto1 = new Produto(null, "Produto 1", 19.99);
        Produto produto2 = new Produto(null, "Produto 2", 29.99);
        Produto produto3 = new Produto(null, "Produto 3", 39.99);
        Produto produto4 = new Produto(null, "Produto 4", 49.99);
        produtoService.criarProduto(produto1);
        produtoService.criarProduto(produto2);
        produtoService.criarProduto(produto3);
        produtoService.criarProduto(produto4);
        Produto produtoAtualizado = new Produto(null, "Produto 2 atualizado", 99.99);
        Produto produto = produtoService.atualizarProduto(produto2.getId(), produtoAtualizado);
        assertNotNull(produto);
        assertEquals(produto2.getId(), produto.getId());
        assertEquals(produtoAtualizado.getNome(), produto.getNome());
        assertEquals(produtoAtualizado.getPreco(), produto.getPreco());
    }

    @Test
    public void deletarProduto() {
        Produto produto1 = new Produto(null, "Produto 1", 19.99);
        Produto produto2 = new Produto(null, "Produto 2", 29.99);
        Produto produto3 = new Produto(null, "Produto 3", 39.99);
        Produto produto4 = new Produto(null, "Produto 4", 49.99);
        produtoService.criarProduto(produto1);
        produtoService.criarProduto(produto2);
        produtoService.criarProduto(produto3);
        produtoService.criarProduto(produto4);
        produtoService.deletarProduto(produto2.getId());
        List<Produto> produtos = produtoService.listarProdutos();
        assertNotNull(produtos);
        assertEquals(3, produtos.size());


    }

}
