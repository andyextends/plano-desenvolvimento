package planodeformacao.produto;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
        Produto produto = new Produto(UUID.randomUUID(), "Produto 1", 10.0);
        when(produtoService.criarProduto(any(Produto.class))).thenReturn(produto);
        Produto produtoCriado = produtoController.criarProduto(produto);
        verify(produtoService).criarProduto(any(Produto.class));
        assertEquals(produtoCriado.getNome(), "Produto 1");
        assertEquals(produtoCriado.getPreco(), 10.0);


    }

    @Test
    void listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(UUID.randomUUID(), "Produto 1", 19.99));
        produtos.add(new Produto(UUID.randomUUID(), "Produto 2", 24.99));
        when(produtoService.listarProdutos()).thenReturn(produtos);
        List<Produto> produtosListados = produtoController.listarProdutos();
        verify(produtoService).listarProdutos();
        verify(produtoService, times(1)).listarProdutos();
        assertEquals(produtosListados.size(), 2);
        assertEquals(produtosListados.get(0).getNome(), "Produto 1");
    }

    @Test
    void testAtualizarProduto() {
        UUID id = UUID.randomUUID();
        Produto produto = new Produto(id, "Produto Antigo", 39.99);
        Produto produtoAtualizado = new Produto(id, "Produto Atualizado", 49.99);
        when(produtoService.atualizarProduto(any(UUID.class), any(Produto.class))).thenReturn(produtoAtualizado);
        Produto produtoRetornado = produtoController.atualizarProduto(id, produtoAtualizado);
        verify(produtoService).atualizarProduto(any(UUID.class), any(Produto.class));
        assertEquals(produtoRetornado.getNome(), "Produto Atualizado");
        assertNotEquals(produtoRetornado.getNome(), "Produto Antigo");

    }
    @Test
    void testDeletarProduto() {
        UUID id = UUID.randomUUID();
        produtoController.deletarProduto(id);
        verify(produtoService).deletarProduto(any(UUID.class));
        assertNotEquals(produtoService.buscarProduto(id), new Produto());
        assertTrue(produtoService.buscarProduto(id) == null);
    }

}