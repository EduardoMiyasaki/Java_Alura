package Exercicio5;

public class Livro implements Calculavel{

    private double precoBase;
    private int paginas;


    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }

    @Override
    public double calcularPrecoFinal() {
       return precoBase + (paginas * 0.1);
    }
}
