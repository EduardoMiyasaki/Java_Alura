import java.util.Scanner;

public class LoopFor {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        double media = 0;
        double nota = 0;

        for (int i = 0; i < 3; i++) {

            System.out.print("Diga sua avaliação para o filme:");
            nota = teclado.nextDouble();

            media += nota;
        }

        media = media / 3;
        System.out.println(String.format("A avaliação média é de %.2f" , media));
    }
}
