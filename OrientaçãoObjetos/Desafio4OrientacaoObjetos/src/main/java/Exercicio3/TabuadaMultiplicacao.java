package Exercicio3;

public class TabuadaMultiplicacao implements Tabuada{

    int numero;

    @Override
    public void mostrarTabuada() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(numero + " X " + i  + " = " +numero * i);
        }
    }

    public static void main(String[] args) {
        TabuadaMultiplicacao tabu = new TabuadaMultiplicacao();

        tabu.numero = 5;

        tabu.mostrarTabuada();
    }
}
