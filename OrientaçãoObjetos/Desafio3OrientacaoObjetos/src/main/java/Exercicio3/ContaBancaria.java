package Exercicio3;

public class ContaBancaria {

    protected double saldo;

    public void depositar(double deposito){
        this.saldo += deposito;
        System.out.println("Seu saldo atual é R$" + saldo);
    }

    public void sacar(double saque){
        this.saldo -= saque;
        System.out.println("Seu saldo atual é R$" + saldo);
    }

    public String consultarSaldo(){
        return "Seu saldo atual é R$" + saldo;

    }
}
