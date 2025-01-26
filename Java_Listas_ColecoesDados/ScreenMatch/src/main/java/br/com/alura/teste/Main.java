package br.com.alura.teste;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {

        ArrayList<Conta> listaContas = new ArrayList<>();

        Conta conta1 = new Conta("Eduardo Miyasaki", 1801);
        Conta conta2 = new Conta("Fernando Miyasaki", 2120);
        Conta conta3 = new Conta("Rita Miyasaki", 1210);

        listaContas.add(conta1);
        listaContas.add(conta2);
        listaContas.add(conta3);

        System.out.println("Antes de ordenar pelo número da conta");
        System.out.println(listaContas);

        // Ordenando a lista pelo número da conta
        Collections.sort(listaContas);
        System.out.println("Depois de ordenar pelo número da conta");
        System.out.println(listaContas);

        TitularComparator comparator = new TitularComparator();

        Collections.sort(listaContas, comparator);
        System.out.println("Depois de ordenar pelo nome do titular");
        System.out.println(listaContas);

    }
}
