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
        Produto produtoSalvo = produtoService.criarProduto(produto);
        assertNotNull(produtoSalvo.getId());
        assertEquals(produto.getNome(), produtoSalvo.getNome());
        assertEquals(produto.getPreco(), produtoSalvo.getPreco());
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
        Produto produto = new Produto(id, "Produto 1", 10.0);
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));
        Produto produtoEncontrado = produtoService.buscarProduto(id);
        assertNotNull(produtoEncontrado);
        assertEquals(produto.getId(), produtoEncontrado.getId());
        verify(produtoRepository, times(1)).findById(id);
    }

    @Test
    public void testAtualizarProduto() {
        UUID id = UUID.randomUUID();
        Produto produto = new Produto(id, "Produto 1", 10.0);
        Produto produtoAtualizado = new Produto(id, "Produto Atualizado", 20.0);
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));
        when(produtoRepository.save(produto)).thenReturn(produtoAtualizado);
        Produto produtoAtualizadoResult = produtoService.atualizarProduto(id, produtoAtualizado);
        assertNotNull(produtoAtualizadoResult);
        assertEquals(produtoAtualizado, produtoAtualizadoResult);
        verify(produtoRepository, times(1)).findById(id);
        verify(produtoRepository, times(1)).save(produto);

    }

    @Test
    public void testDeletarProduto() {
        UUID id = UUID.randomUUID();
        Produto produto = new Produto(id, "Produto 1", 10.0);

        when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));
        produtoService.deletarProduto(id);
        verify(produtoRepository, times(1)).delete(produto);
        verify(produtoRepository, times(1)).findById(id);

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
        Produto produto = new Produto(id, "Produto 1", 10.0);

        when(produtoRepository.findByUserId(id)).thenReturn(Optional.of(produto));
        Produto produtoEncontrado = produtoService.buscarProdutoPorUsuario(id);
        assertNotNull(produtoEncontrado);
        assertEquals(produto, produtoEncontrado);
        verify(produtoRepository, times(1)).findByUserId(id);
    }
}








