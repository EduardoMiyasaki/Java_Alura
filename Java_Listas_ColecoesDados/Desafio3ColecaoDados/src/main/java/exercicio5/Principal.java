package exercicio5;

import java.util.ArrayList;

public class Principal {

    public static void main(String[] args) {

        ArrayList<ContaBancaria> listaContas = new ArrayList<>();

        double maiorSaldo = 0;

        ContaBancaria conta1 = new ContaBancaria(101);
        ContaBancaria conta2 = new ContaBancaria(102);
        ContaBancaria conta3 = new ContaBancaria(103);
        ContaBancaria conta4 = new ContaBancaria(104);

        conta1.depositar(100);
        conta2.depositar(400);
        conta3.depositar(1000);
        conta4.depositar(900);

        listaContas.add(conta1);
        listaContas.add(conta2);
        listaContas.add(conta3);
        listaContas.add(conta4);

        for (int i = 0; i < listaContas.size(); i++) {
            if (listaContas.get(i).getSaldo() > maiorSaldo) {
                maiorSaldo = listaContas.get(i).getSaldo();
            }
        }

        System.out.println("O maior saldo é de R$ " + maiorSaldo);


    }
}
