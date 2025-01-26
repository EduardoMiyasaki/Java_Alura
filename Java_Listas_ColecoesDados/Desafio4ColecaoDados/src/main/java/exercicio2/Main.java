package exercicio2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Titulo> listaTitulos = new ArrayList<>();
        List<Titulo> listaTitulos2 = new LinkedList<>();

        List<Titulo> novaLista = listaTitulos2;

        Titulo titulo1 = new Titulo("Stranger things");
        Titulo titulo2 = new Titulo("Suits");
        Titulo titulo3 = new Titulo("Avatar");

        listaTitulos2.add(titulo1);
        listaTitulos2.add(titulo2);
        listaTitulos2.add(titulo3);

        listaTitulos.add(titulo1);
        listaTitulos.add(titulo2);
        listaTitulos.add(titulo3);

        System.out.println("Lista de títulos antes de ordenar");
        System.out.println(listaTitulos2);

        System.out.println("Lista de Títulos depois de ordenar");
        Collections.sort(listaTitulos2);
        System.out.println(listaTitulos2);

        System.out.println("Lista De Título 1");
        System.out.println("Lista de títulos antes de ordenar");
        System.out.println(listaTitulos2);

        System.out.println("Lista de Títulos depois de ordenar");
        Collections.sort(listaTitulos2);
        System.out.println(listaTitulos2);

        System.out.println("---------------------------");
        System.out.println(novaLista);
    }
}
