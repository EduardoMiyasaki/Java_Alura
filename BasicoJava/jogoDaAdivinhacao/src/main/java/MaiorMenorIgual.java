import java.util.Scanner;

public class MaiorMenorIgual {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        System.out.println("Informe um número");
        int n1 = teclado.nextInt();

        System.out.println("Informe outro número");
        int n2 = teclado.nextInt();

        if (n1 == n2) {
            System.out.println("Os numeros são iguais");
        }
        else {
            System.out.println("Os numeros são diferentes");
        }
    }
}
