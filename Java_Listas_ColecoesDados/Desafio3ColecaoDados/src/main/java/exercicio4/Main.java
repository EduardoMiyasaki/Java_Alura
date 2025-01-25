package exercicio4;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Forma> listaFormas = new ArrayList<>();

        Circulo circulo1 = new Circulo(5);
        Circulo circulo2 = new Circulo(10);

        Quadrado quadrado1 = new Quadrado(4);
        Quadrado quadrado2 = new Quadrado(5);

        listaFormas.add(circulo1);
        listaFormas.add(circulo2);

        listaFormas.add(quadrado1);
        listaFormas.add(quadrado2);

        listaFormas.forEach(forma -> {
            if (forma instanceof Circulo) {
                System.out.println("A área do circulo é de " + forma.calcularArea() + "Com seu raio de " + ((Circulo) forma).getRaio());
            } else {
                System.out.println("A área do Quadrado é de " + forma.calcularArea() + "Com seu comprimento de " + ((Quadrado) forma).getComprimento());
            }
        });
    }
}
