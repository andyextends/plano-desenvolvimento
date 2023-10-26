package com.example.planodeformacao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import planodeformacao.produto.Produto;
import planodeformacao.produto.ProdutoService;
import planodeformacao.produto.exception.ProdutoNaoEncontradoException;
import planodeformacao.produto.repository.ProdutoRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class ProdutoServiceTest {
    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCriarProduto() {
        Produto produto = new Produto(null, "Produto 1", 10.0);
        when(produtoRepository.save(produto)).thenReturn(produto);
        Produto produtoCriado = produtoService.criarProduto(produto);
        assertNotNull(produtoCriado);
        assertEquals(produtoCriado.getNome(), "Produto 1");
        assertEquals(produtoCriado.getPreco(), 10.0);
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    public void testListarProdutosSemProdutos() {

        when(produtoRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(ProdutoNaoEncontradoException.class, () -> {
            produtoService.listarProdutos();
            assertTrue(produtoService.listarProdutos().isEmpty());
            verify(produtoRepository, times(1)).findAll();
        });


    }


    @Test
    public void testBuscarProduto() {
        UUID id = UUID.randomUUID();
        Produto produto = new Produto(null, "Produto 1", 10.0);
        when(produtoRepository.findById(String.valueOf(id))).thenReturn(Optional.of(produto));
        Produto produtoEncontrado = produtoService.buscarProduto(String.valueOf(id));
        assertNotNull(produtoEncontrado);
        assertEquals(produto.getId(), produtoEncontrado.getId());
        verify(produtoRepository, times(1)).findById(String.valueOf(id));
    }

    @Test
    public void testAtualizarProduto() {
        UUID id = UUID.randomUUID();
        Produto produto = new Produto(null, "Produto 1", 10.0);
        Produto produtoAtualizado = new Produto(null, "Produto Atualizado", 20.0);
        when(produtoRepository.findById(String.valueOf(id))).thenReturn(Optional.of(produto));
        when(produtoRepository.save(produto)).thenReturn(produtoAtualizado);
        Produto produtoAtualizadoResult = produtoService.atualizarProduto(String.valueOf(id), produtoAtualizado);
        assertNotNull(produtoAtualizadoResult);
        assertEquals(produtoAtualizado, produtoAtualizadoResult);
        verify(produtoRepository, times(1)).findById(String.valueOf(id));
        verify(produtoRepository, times(1)).save(produto);

    }

    @Test
    public void testDeletarProduto() {
        UUID id = UUID.randomUUID();
        Produto produto = new Produto(null, "Produto 1", 10.0);

        when(produtoRepository.findById(String.valueOf(id))).thenReturn(Optional.of(produto));
        produtoService.deletarProduto(String.valueOf(id));
        verify(produtoRepository, times(1)).delete(produto);
        verify(produtoRepository, times(1)).findById(String.valueOf(id));

    }

    @Test
    public void testBuscarProdutoPorNome() {
        String nome = "Produto 1";
        List<Produto> produtos = Arrays.asList(new Produto(null, nome, 10.0));
        when(produtoRepository.findByNomeIgnoreCase(nome)).thenReturn(produtos);
        List<Produto> produtosEncontrados = produtoService.buscarProdutosPorNome(nome);
        assertNotNull(produtosEncontrados);
        assertEquals(produtos, produtosEncontrados);
        verify(produtoRepository, times(1)).findByNomeIgnoreCase(nome);
    }

    @Test
    public void testBuscarProdutoPorUsuario() {
        UUID id = UUID.randomUUID();
        Produto produto = new Produto(null, "Produto 1", 10.0);

        when(produtoRepository.findByUserId(String.valueOf(id))).thenReturn(Optional.of(produto));
        Produto produtoEncontrado = produtoService.buscarProdutoPorUsuario(String.valueOf(id));
        assertNotNull(produtoEncontrado);
        assertEquals(produto, produtoEncontrado);
        verify(produtoRepository, times(1)).findByUserId(String.valueOf(id));
    }
}








