package exercicio3;

import java.io.EOFException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner teclado = new Scanner(System.in);

        try {
            Pessoa pessoa1 = new Pessoa();

            System.out.print("Informe o nome do usuário:");
            pessoa1.setNome(teclado.nextLine());

            GitHubUser usuario = new GitHubUser(pessoa1.getNome());

            GitHubUser gitHubUser = usuario.consultarUsuario(pessoa1.getNome());
            System.out.println(gitHubUser);
        }
        catch (ErroConsultaGitHubException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            teclado.close();
        }

    }
}
