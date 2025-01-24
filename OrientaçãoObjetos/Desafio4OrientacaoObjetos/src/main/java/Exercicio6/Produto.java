package Exercicio6;

public class Produto implements Vendavel {

    private String nome;
    private double preco;
    private int quantidade;
    private boolean epoca;

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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public boolean isEpoca() {
        return epoca;
    }

    public void setEpoca(boolean epoca) {
        this.epoca = epoca;
    }

        @Override
        public double calcularPreco() {
            if (this.quantidade >= 3 && isEpoca()) {
                return this.preco - (this.preco * 0.24);
            }
            else if (this.quantidade >= 3 || isEpoca()) {
                return this.preco - (this.preco * 0.12);
            }
            else {
                return this.preco;
            }
        }
}
