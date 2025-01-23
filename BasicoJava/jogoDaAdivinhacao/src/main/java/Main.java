import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        int numeroAleatorio = new Random().nextInt(100);

        System.out.println("Desafio!!! Adivinhar o número aleatório");

        for (int i = 0; i <= 4; i++) {

//            System.out.println(numeroAleatorio);

            System.out.print(i + 1 + "° tentativa! Informe um número:");
            int tentativa = teclado.nextInt();

            if (tentativa > numeroAleatorio) {
                System.out.println("A sua tentativa foi maior que o número aleatório");
            }
            else if (tentativa < numeroAleatorio) {
                System.out.println("A sua tentativa foi menor que o número aleatório");
            }
            else{
                System.out.println("Parabéns!!! Você acertou");
                break;
            }

        }
    }
}
