import java.util.Scanner;

public class Tabuada {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        System.out.print("Informe um número:");
        int n1 = teclado.nextInt();

        System.out.println("Tabuada desse número:");
        for (int i = 1; i <= 10; i++) {
            System.out.println(n1 * i);
        }
    }
}
