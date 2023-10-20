package planodeformacao.produto;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public Produto atualizarProduto(@PathVariable UUID id, @RequestBody Produto produto) {

        return produtoService.atualizarProduto(id, produto);
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable UUID id) {

        produtoService.deletarProduto(id);
    }

    @SuppressWarnings("unused")
    @GetMapping("/{id}")
    public Produto buscarProdutoPorId(@PathVariable UUID id) {

        return produtoService.buscarProduto(id);

    }

    @SuppressWarnings("unused")
    @GetMapping("/nome{nome}")
    public List<Produto> buscarProdutosPorNome(@RequestParam(name = "nome") String nome) {

        return produtoService.buscarProdutosPorNome(nome);

    }

    @SuppressWarnings("unused")
    @GetMapping("/id{userId}")
    public Produto buscarprodutoPorUserId(@RequestHeader(name = "userId") String userId) {

        return produtoService.buscarProdutoPorUsuario(UUID.fromString(userId));

    }
}






