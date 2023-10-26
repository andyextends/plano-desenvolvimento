package planodeformacao.produto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public Produto criarProduto(@Valid @RequestBody Produto produto) {

        return produtoService.criarProduto(produto);
    }

    @GetMapping
    public List<Produto> listarProdutos() {

        return produtoService.listarProdutos();
    }

    @PutMapping("/{id}")
    public Produto atualizarProduto(@Valid @PathVariable String id, @RequestBody Produto produto) {

        return produtoService.atualizarProduto(id, produto);
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(@NotBlank @PathVariable String id) {

        produtoService.deletarProduto(id);
    }

    @SuppressWarnings("unused")
    @GetMapping("/{id}")
    public Produto buscarProdutoPorId(@NotBlank @PathVariable String id) {

        return produtoService.buscarProduto(id);

    }

    @SuppressWarnings("unused")
    @GetMapping("/nome")
    public List<Produto> buscarProdutosPorNome(@Valid @RequestParam(name = "nome") String nome) {

        return produtoService.buscarProdutosPorNome(nome);

    }

    @SuppressWarnings("unused")
    @GetMapping("/id")
    public Produto buscarprodutoPorUserId( @Valid @RequestHeader(name = "id") String userId) {

        return produtoService.buscarProdutoPorUsuario(userId);

    }
}






