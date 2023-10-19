package planodeformacao.produto;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

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

    @GetMapping("/{id}")
    public Produto buscarProdutoPorId(@PathVariable UUID id) {
        Produto produto = produtoService.buscarProduto(id);
        if (produto != null) {
            return produto;
        } else {
            throw new IllegalArgumentException("Produto não encontrado");
        }

    }
    @GetMapping("/buscar")
    public List<Produto> buscarProdutosPorNome(@RequestParam(name = "nome") String nome) {
        List<Produto> produtos = produtoService.buscarProdutosPorNome(nome);
        if (produtos != null) {
            return produtos;
        } else {
            throw new IllegalArgumentException("Produto não encontrado");
        }

    }

}
