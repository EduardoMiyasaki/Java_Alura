public class Cartao {

    private int numero;
    private double limite;

    public Cartao(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }
}
