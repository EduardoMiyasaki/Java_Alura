package exercicio4;

public class Circulo implements Forma {

    private double area;
    private double raio;

    public Circulo(double raio) {
        this.raio = raio;
    }

    public double getRaio() {
        return raio;
    }

    public double getArea() {
        return area;
    }

    @Override
    public double calcularArea() {
        this.area = Math.PI * this.raio * this.raio;
        return this.area;
    }
}
