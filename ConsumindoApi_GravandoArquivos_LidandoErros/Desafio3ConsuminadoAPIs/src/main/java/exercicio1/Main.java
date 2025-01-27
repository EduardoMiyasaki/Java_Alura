package exercicio1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        try {
            System.out.print("Informe um número:");
            int n1 = teclado.nextInt();

            System.out.print("Informe outro número:");
            int n2 = teclado.nextInt();

            int divisor = n1 / n2;
            System.out.println(+n1 + " / " + n2 + " = " + divisor);
        }
        catch (ArithmeticException e) {
            e.getMessage();
            System.out.println("Não pode dividir por 0");
        }

        System.out.println("Programa Finalizado!");
    }
}
