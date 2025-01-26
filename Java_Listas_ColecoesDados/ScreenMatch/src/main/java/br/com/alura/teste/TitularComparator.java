package br.com.alura.teste;

import java.util.Comparator;

public class TitularComparator implements Comparator<Conta> {


    @Override
    public int compare(Conta conta1, Conta conta2) {
        return conta1.getTitular().compareTo(conta2.getTitular());
    }
}
