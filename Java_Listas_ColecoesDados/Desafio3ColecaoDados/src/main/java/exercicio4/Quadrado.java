package exercicio4;

public class Quadrado implements Forma {

    private double area;
    private double comprimento;

    public Quadrado(double comprimento) {
        this.comprimento = comprimento;
    }

    public double getArea() {
        return area;
    }

    public double getComprimento() {
        return comprimento;
    }

    @Override
    public double calcularArea() {
        this.area = comprimento * comprimento;
        return this.area;
    }
}
