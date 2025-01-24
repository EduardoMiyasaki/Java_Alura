package Exercicio5;

public class ProdutoFisico  implements Calculavel{

    int precoBase = 10;
    double peso;

    public int getPrecoBase() {
        return precoBase;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public double calcularPrecoFinal() {
        return precoBase + (peso * 0.12);
    }
}
