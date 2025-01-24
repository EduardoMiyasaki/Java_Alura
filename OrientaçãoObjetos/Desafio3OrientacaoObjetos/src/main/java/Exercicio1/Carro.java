package Exercicio1;

public class Carro {

    private String modelo;
    private double precoAno1;
    private double precoAno2;
    private double precoAno3;
    private int ano;

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void definirPrecos(double preco1, double preco2, double preco3) {
        this.precoAno1 = preco1;
        this.precoAno2 = preco2;
        this.precoAno3 = preco3;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double exibirPrecos() {

        double menorPreco = precoAno1;

        if (menorPreco > precoAno2) {
            menorPreco = precoAno2;
        }

        if (menorPreco > precoAno3) {
            menorPreco = precoAno3;
        }

        return menorPreco;
    }

    public double calcularMaiorPreco() {
        double maiorPreco = precoAno1;

        if (maiorPreco < precoAno2) {
            maiorPreco = precoAno2;
        }

        if (maiorPreco < precoAno3) {
            maiorPreco = precoAno3;
        }

        return maiorPreco;
    }
}

