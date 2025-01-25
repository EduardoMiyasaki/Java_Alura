package exercicio1;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> listaNomes = new ArrayList<>();

        listaNomes.add("Eduardo");
        listaNomes.add("Fernando");
        listaNomes.add("Rita");

        listaNomes.forEach(nomes -> System.out.println(nomes));
    }
}
