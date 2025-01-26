package br.com.alura.teste;

public class Conta implements Comparable<Conta> {

    private int numero;
    private String titular;

    public Conta(String titular, int numero) {
        this.titular = titular;
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    @Override
    public int compareTo(Conta outraConta) {

        if (this.numero < outraConta.getNumero()) {
            return -1;
        } else if (this.numero > outraConta.getNumero()) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "O Titular da conta é " + this.getTitular();
    }
}
