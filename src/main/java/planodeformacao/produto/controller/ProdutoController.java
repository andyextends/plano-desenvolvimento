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

@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "404", description = "Não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")})
@Tag(name = "Produto", description = "API de produtos")
@Validated
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {

        this.produtoService = produtoService;
    }

    @Operation(summary = "Cria um novo produto",
            description = "Esta opção cria um novo o produto e inclui no nosso cadastrado .")
    @PostMapping
    public Produto criarProduto(@Valid @RequestBody Produto produto) {

        return produtoService.criarProduto(produto);
    }

    @Operation(summary = "Lista todos produtos",
            description = "Esta opção lista todos o produtos que temos cadastrados .")
    @GetMapping
    public List<Produto> listarProdutos() {

        return produtoService.listarProdutos();
    }

    @Operation(summary = "Atualiza por ID o produto",
            description = "Ao incluir um id no path da url o produto pode ser editado e atualizado .")
    @PutMapping("/{id}")
    public Produto atualizarProduto(@NotBlank @PathVariable String id, @Valid @RequestBody Produto produto) {

        return produtoService.atualizarProduto(id, produto);
    }

    @Operation(summary = "Deleta por ID o produto",
            description = "Ao incluir um id no path da url o produto pode ser deletado .")
    @DeleteMapping("/{id}")
    public void deletarProduto(@NotBlank @PathVariable String id) {

        produtoService.deletarProduto(id);
    }

    @Operation(summary = "Buscar por ID o produto",
            description = "Ao incluir um id no path da url retorna dados do produto.")
    @GetMapping("/{id}")
    public Produto buscarProdutoPorId(@NotBlank @PathVariable String id) {

        return produtoService.buscarProduto(id);

    }

    @Operation(summary = "Busca por Nome o produto",
            description = "Ao fazer a busca do Nome de um produto retorna dados deste produto.")
    @GetMapping("/nome")
    public List<Produto> buscarProdutosPorNome(@Valid @RequestParam(name = "nome") String nome) {

        return produtoService.buscarProdutosPorNome(nome);

    }


    @Operation(summary = "Busca por ID do produto",
            description = "Ao fazer a busca do Id no header retorna dados deste produto.")
    @GetMapping("/id")
    public Produto buscarprodutoPorUserId(@Valid @RequestHeader(name = "id") String userId) {

        return produtoService.buscarProdutoPorUsuario(userId);

    }
}






