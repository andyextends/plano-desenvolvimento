package planodeformacao.produto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;


public class Produto {
    @NotBlank(message = "O nome não pode ser vazio")
    @NotNull(message = "O nome não pode ser null")
    private UUID id;
    @NotBlank(message = "O nome não pode ser vazio")
    @NotNull(message = "O nome não pode ser null")
    private String nome;
    @NotBlank(message = "O preço não pode ser null")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero")
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
