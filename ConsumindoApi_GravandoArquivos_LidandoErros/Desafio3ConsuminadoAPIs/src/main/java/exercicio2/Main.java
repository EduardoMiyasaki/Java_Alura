package exercicio2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        try {
            System.out.print("Informe sua senha: ");
            String senha = teclado.nextLine();

            Pessoa pessoa1 = new Pessoa();
            pessoa1.setSenha(senha);
            System.out.println("Senha definida com sucesso!");
        } catch (SenhaInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }
}
