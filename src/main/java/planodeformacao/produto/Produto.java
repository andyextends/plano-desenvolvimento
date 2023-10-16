package planodeformacao.produto;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Produto {

    private UUID id;
    private String nome;
    private double preco;

    public Produto() {
    }

    public Produto(UUID id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
