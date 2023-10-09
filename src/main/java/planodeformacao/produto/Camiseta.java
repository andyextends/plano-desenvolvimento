package planodeformacao.produto;



public class Camiseta extends Produto {
    public enum TipoGola {
        GOLA_V, GOLA_ROLE, GOLA_CARECA, GOLA_POLO
    }

    private String tamanho;
    private String cor;
    private TipoGola tipoGola;

    public Camiseta(Long id, String nome, double preco, String tamanho, String cor, TipoGola tipoGola) {
        super(id, nome, preco);
        this.tamanho = tamanho;
        this.cor = cor;
        this.tipoGola = tipoGola;
    }



    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public TipoGola getTipoGola() {
        return tipoGola;
    }

    public void setTipoGola(TipoGola tipoGola) {
        this.tipoGola = tipoGola;
    }
}