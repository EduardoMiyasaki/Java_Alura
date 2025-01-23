import java.util.Scanner;

public class ParImpar {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        System.out.print("Informe um número:");
        int n1 = teclado.nextInt();

        int resto = n1 % 2;

        if (resto == 0) {
            System.out.println("O número " + n1 + " é par");
        }
        else {
            System.out.println("O número " + n1 + " é impar");
        }
    }
}
