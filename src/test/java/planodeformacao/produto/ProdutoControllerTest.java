package planodeformacao.produto;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import planodeformacao.produto.controller.ProdutoController;
import planodeformacao.produto.exception.ProdutoNaoEncontradoException;
import planodeformacao.produto.model.Produto;
import planodeformacao.produto.service.ProdutoService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class ProdutoControllerTest {
    @InjectMocks
    ProdutoController produtoController;
    @Mock
    ProdutoService produtoService;

    public ProdutoControllerTest() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testCriarProduto() {
        Produto produto = new Produto(null, "Produto 1", 10.0);
        when(produtoService.criarProduto(any(Produto.class))).thenReturn(produto);
        Produto produtoCriado = produtoController.criarProduto(produto);
        verify(produtoService).criarProduto(any(Produto.class));
        assertEquals(produtoCriado.getNome(), "Produto 1");
        assertEquals(produtoCriado.getPreco(), 10.0);


    }

    @Test
    void listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(null, "Produto 1", 19.99));
        produtos.add(new Produto(null, "Produto 2", 24.99));
        when(produtoService.listarProdutos()).thenReturn(produtos);
        List<Produto> produtosListados = produtoController.listarProdutos();
        verify(produtoService, times(1)).listarProdutos();
        assertEquals(produtosListados.size(), 2);
        assertEquals(produtosListados.get(0).getNome(), "Produto 1");
    }

    @Test
    void testAtualizarProduto() {
        UUID id = UUID.randomUUID();
        Produto produto = new Produto(id.toString(), "Produto 1", 10.0);
        when(produtoService.atualizarProduto(anyString(), any(Produto.class))).thenReturn(produto);
        Produto produtoAtualizado = produtoController.atualizarProduto(id.toString(), produto);
        verify(produtoService).atualizarProduto(anyString(), any(Produto.class));
        assertEquals(produtoAtualizado.getNome(), "Produto 1");
        assertEquals(produtoAtualizado.getPreco(), 10.0);
        verify(produtoService, times(1)).atualizarProduto(anyString(), any(Produto.class));
    }

    @Test
    void testDeletarProduto() {
        UUID id = UUID.randomUUID();
        doThrow(ProdutoNaoEncontradoException.class).when(produtoService).buscarProduto(anyString());
        produtoController.deletarProduto(id.toString());
        verify(produtoService).deletarProduto(anyString());
        verify(produtoService, times(1)).deletarProduto(anyString());

    }
}