package exercicio1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Integer> listaInteiros = new ArrayList<>();

        listaInteiros.add(90);
        listaInteiros.add(50);
        listaInteiros.add(70);
        listaInteiros.add(76);
        listaInteiros.add(932);

        System.out.println("Lista de inteiro antes de ordenar:");
        System.out.println(listaInteiros);

        System.out.println("Lista de inteiro depois de ordenar");
        Collections.sort(listaInteiros);
        System.out.println(listaInteiros);
    }
}
