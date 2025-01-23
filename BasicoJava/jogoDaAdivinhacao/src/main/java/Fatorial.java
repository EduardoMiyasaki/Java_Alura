import java.util.Scanner;

public class Fatorial {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Informe um número:");
        int fatorial = teclado.nextInt();

        int numero = fatorial;

        for (int i = numero - 1; i != 0; i--) {
            fatorial = fatorial * i;
        }

        System.out.println("O valor da fatorial de " + numero + " é " + fatorial);
    }
}
