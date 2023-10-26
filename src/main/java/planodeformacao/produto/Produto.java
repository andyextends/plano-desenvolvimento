package planodeformacao.produto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;


@Document(collection = "produtos")
public class Produto {

    @NotBlank(message = "O nome não pode ser vazio")
    private String id;
    @NotBlank(message = "O nome não pode ser vazio")
    private String nome;
    @NotNull(message = "O preço não pode ser null")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero")
    private double preco;

    public Produto(String id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public Produto() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

