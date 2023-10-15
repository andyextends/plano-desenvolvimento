package planodeformacao;

import planodeformacao.produto.Camisa;
import planodeformacao.produto.Produto;
import planodeformacao.produto.ProdutoService;

import java.util.UUID;

public class MainApplication {
    public static void main(String[] args) {


        ProdutoService produtoService = new ProdutoService();


        Produto produto1 = new Produto(null, "Produto 1", 59.99);
        Produto produto2 = new Produto(null, "Produto 2", 69.99);
        Produto produto3 = new Produto(null, "Produto 3", 79.99);
        Produto produto4 = new Produto(null, "Produto 4", 89.99);

        Camisa camisa1 = new Camisa(null, "Camisa 1", 59.99, "M",
                "Azul", Camisa.TipoGola.GOLA_CARECA);
        Camisa camisa2 = new Camisa(null, "Camisa 2", 69.99, "G", "Vermelha",
                Camisa.TipoGola.GOLA_POLO);
        Camisa camisa3 = new Camisa(null, "Camisa 3", 79.99, "P", "Verde",
                Camisa.TipoGola.GOLA_ROLE);
        Camisa camisa4 = new Camisa(null, "Camisa 4", 89.99, "GG", "Amarela",
                Camisa.TipoGola.GOLA_V);

        produtoService.criarProduto(produto1);
        produtoService.criarProduto(produto2);
        Produto produtocriado3 = produtoService.criarProduto(produto3);
        Produto produtocriado4 = produtoService.criarProduto(produto4);
        produtoService.criarProduto(camisa1);
        produtoService.criarProduto(camisa2);
        produtoService.criarProduto(camisa3);
        produtoService.criarProduto(camisa4);

        System.out.println("------------------------------------------------------");

//        System.out.println("Produto cadastrado: " + produto1.getNome() + " " + produto1.getPreco()+""+ camisa1.getId());
//        System.out.println("Produto cadastrado: " + produto2.getNome() + " " + produto2.getPreco());
//        System.out.println("Produto cadastrado: " + produtocriado3.getNome() + " " + produto3.getPreco());
//        System.out.println("Produto cadastrado: " + produtocriado4.getNome() + " " + produto4.getPreco());
//        System.out.println("Produto cadastrado: " + camisa1.getNome() + " " + camisa1.getPreco());
//        System.out.println("Produto cadastrado: " + camisa2.getNome() + " " + camisa2.getPreco());
//        System.out.println("Produto cadastrado: " + camisa3.getNome() + " " + camisa3.getPreco());
//        System.out.println("Produto cadastrado: " + camisa4.getNome() + " " + camisa4.getPreco());
        System.out.println("------------------------------------------------------");

        produtoService.deletarProduto(produto1.getId());

        System.out.println("Produto deletado: " + produto1.getNome() + " " + produto1.getPreco());

        produtoService.deletarProduto(produto1.getId());


        System.out.println("Lista de produtos:");
        for (Produto produto : produtoService.listarProdutos()) {
            System.out.println(produto.getNome() + " " + produto.getPreco());
        }


        produtoService.deletarProduto(produto2.getId());
        System.out.println("Produto deletado: " + produto2.getNome() + " " + produto2.getPreco());

        produtoService.deletarProduto(produto2.getId());

        System.out.println("------------------------------------------------------");
        System.out.println("Produtos apos deletar");
        produtoService.listarProdutos().forEach(produto -> System.out.println(produto.getNome()
                + " " + produto.getPreco()));

        produtoService.listarProdutos();
        System.out.println("Lista de produtos:");
        for (Produto produto : produtoService.listarProdutos()) {
            System.out.println(produto.getNome() + " " + produto.getPreco());
        }
        System.out.println("------------------------------------------------------");
        Produto produto5 = new Produto(null, "Produto 5", 59.99);
        Produto produtocadastrado5 = produtoService.criarProduto(produto5);
        System.out.println("Produto cadastrado: " + produtocadastrado5.getNome() + " " + produtocadastrado5.getPreco());

        produtoService.listarProdutos();
        System.out.println("Lista de produtos:");
        for (Produto produto : produtoService.listarProdutos()) {
            System.out.println(produto.getNome() + " " + produto.getPreco());
        }
        produto5.setNome("Produto 5 atualizado");
        produto5.setPreco(99.99);
        produtoService.atualizarProduto(produto5.getId(), produto5);
        System.out.println("Produto atualizado: " + produtocadastrado5.getNome()
                + " " + produtocadastrado5.getPreco());
        System.out.println("------------------------------------------------------");
        produtoService.listarProdutos();
        System.out.println("Lista de produtos:");
        for (Produto produto : produtoService.listarProdutos()) {
            System.out.println(produto.getNome() + " " + produto.getPreco());
        }
        System.out.println("------------------------------------------------------");
        produtoService.deletarProduto(produto4.getId());
        System.out.println("Produto deletado: " + produto4.getNome() + " "
                + produto4.getPreco());
        produtoService.deletarProduto(produto4.getId());
        produtoService.deletarProduto(produto3.getId());
        System.out.println("------------------------------------------------------");

        produtoService.listarProdutos();
        System.out.println("Lista de produtos:");
        for (Produto produto : produtoService.listarProdutos()) {
            System.out.println(produto.getNome() + " " + produto.getPreco());
        }
        produtoService.deletarProduto(camisa1.getId());
        System.out.println("Produto deletado: " + camisa1.getNome() + " " + camisa1.getPreco());
        produtoService.deletarProduto(camisa1.getId());
        produtoService.deletarProduto(camisa2.getId());
        produtoService.deletarProduto(camisa3.getId());
        produtoService.deletarProduto(camisa4.getId());
        System.out.println("------------------------------------------------------");
        produtoService.listarProdutos();
        System.out.println("Lista de produtos:");
        for (Produto produto : produtoService.listarProdutos()) {
            System.out.println(produto.getNome() + " " + produto.getPreco());
        }


    }
}













