package planodeformacao.produto.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import planodeformacao.produto.model.Produto;
import planodeformacao.produto.service.ProdutoService;

import java.util.List;

//@Api(value = "API de Produtos")
@Validated
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {

        this.produtoService = produtoService;
    }


    @PostMapping
    @Tag(name = "Produto", description = "Cria um produto")
    public Produto criarProduto(@RequestBody Produto produto) {

        return produtoService.criarProduto(produto);
    }


    @GetMapping
    @Tag(name = "Produto", description = "Lista todos os produtos")
    public List<Produto> listarProdutos() {

        return produtoService.listarProdutos();
    }


    @PutMapping("/{id}")
    @Tag(name = "Produto", description = "Atualiza um produto")
    public Produto atualizarProduto(@NotBlank @PathVariable String id, @Valid @RequestBody Produto produto) {

        return produtoService.atualizarProduto(id, produto);
    }


    @DeleteMapping("/{id}")
    @Tag(name = "Produto", description = "Deleta um produto")
    public void deletarProduto(@NotBlank @PathVariable String id) {

        produtoService.deletarProduto(id);
    }


    @GetMapping("/{id}")
    @Tag(name = "Produto", description = "Busca por ID do produto")
    public Produto buscarProdutoPorId(@NotBlank @PathVariable String id) {

        return produtoService.buscarProduto(id);

    }


    @GetMapping("/nome")
    @Tag(name = "Produto", description = "Busca por nome do produto")
    public List<Produto> buscarProdutosPorNome(@Valid @RequestParam(name = "nome") String nome) {

        return produtoService.buscarProdutosPorNome(nome);

    }


    @GetMapping("/id")
    @Tag(name = "Produto", description = "Busca por ID do produto")
    public Produto buscarprodutoPorUserId(@Valid @RequestHeader(name = "id") String userId) {

        return produtoService.buscarProdutoPorUsuario(userId);

    }
}






