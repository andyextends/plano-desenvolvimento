package planodeformacao;

import planodeformacao.produto.Camisa;
import planodeformacao.produto.Produto;
import planodeformacao.produto.ProdutoService;

public class MainApplication {
    public static void main(String[] args) {


        ProdutoService produtoService = new ProdutoService();

        Produto produto1 = new Produto(null, "Produto 1", 19.99);


        Produto produtocadastrado1 = produtoService.criarProduto(produto1);
        Produto produto2 = new Produto(null, "Produto 2", 29.99);
        Produto produtocadastrado2 = produtoService.criarProduto(produto2);
        Produto produto3 = new Produto(null, "Produto 3", 39.99);
        Produto produtocadastrado3 = produtoService.criarProduto(produto3);
        Produto produto4 = new Produto(null, "Produto 4", 49.99);
        Produto produtocadastrado4 = produtoService.criarProduto(produto4);
        Camisa camisa1 = new Camisa(null, "Camisa 1", 19.99, "M", "Azul");
        Produto produtocadastrado6 = produtoService.criarProduto(camisa1);

        System.out.println("Produto cadastrado: " + produtocadastrado1.getNome() + " " + produtocadastrado1.getPreco());
        System.out.println("Produto cadastrado: " + produtocadastrado2.getNome() + " " + produtocadastrado2.getPreco());
        System.out.println("Produto cadastrado: " + produtocadastrado3.getNome() + " " + produtocadastrado3.getPreco());
        System.out.println("Produto cadastrado: " + produtocadastrado4.getNome() + " " + produtocadastrado4.getPreco());
        System.out.println("Produto cadastrado: " + produtocadastrado6.getNome() + " " + produtocadastrado6.getPreco()
                +" Tamanho "+((Camisa) produtocadastrado6).getTamanho()+" Cor "+((Camisa) produtocadastrado6).getCor());
        System.out.println("------------------------------------------------------");
        produtoService.deletarProduto(produtocadastrado1.getId());
        produtoService.deletarProduto(produtocadastrado2.getId());
        produtoService.deletarProduto(produtocadastrado3.getId());
        System.out.println("------------------------------------------------------");

        System.out.println("Lista de produtos:");
        for (Produto produto : produtoService.listarProdutos()) {
            System.out.println(produto.getNome() + " " + produto.getPreco());
        }

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
        produtoService.atualizarProduto(produtocadastrado5.getId(), produto5);
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
        produtoService.listarProdutos();
        System.out.println("Lista de produtos:");
        for (Produto produto : produtoService.listarProdutos()) {
            System.out.println(produto.getNome() + " " + produto.getPreco());
        }

    }
}













