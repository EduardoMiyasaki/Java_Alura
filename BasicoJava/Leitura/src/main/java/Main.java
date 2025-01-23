import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        System.out.print("Digite seu filme favorito:");
        String filmeFavorito = teclado.nextLine();

        System.out.print("Informe o ano de lançamento desse filme:");
        int anoLancamento = teclado.nextInt();

        System.out.print("Diga sua avaliação para o filme:");
        double avaliacao = teclado.nextDouble();

        System.out.println(String.format("%s foi lançado em %d e sua avaliação para o filme foi de %.2f" , filmeFavorito, anoLancamento , avaliacao));
    }
}
