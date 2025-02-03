package br.com.alura;

import java.util.Scanner;

public class AdopetConsoleApplication {

    private static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        CommandExecutor executor = new CommandExecutor();

        try {
            int opcaoEscolhida = 0;
            while (opcaoEscolhida != 5) {

                mostrarMenu();
                opcaoEscolhida = teclado.nextInt();

                switch (opcaoEscolhida) {
                    case 1 -> executor.executeCommand(new ListarAbrigoCommand());
                    case 2 -> executor.executeCommand(new CadastrarAbrigoCommand());
                    case 3 -> executor.executeCommand(new ListarPetsCommand());
                    case 4 -> executor.executeCommand(new ImportarPetsCommand());
                    case 5 -> System.exit(0);
                    default -> opcaoEscolhida = 0;
                }
            }
            System.out.println("Finalizando o programa...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void mostrarMenu() {
        System.out.println("""
                ##### BOAS VINDAS AO SISTEMA ADOPET CONSOLE #####
                       DIGITE O NÚMERO DA OPERAÇÃO DESEJADA:
                       1 -> Listar abrigos cadastrados
                       2 -> Cadastrar novo abrigo
                       3 -> Listar pets do abrigo
                       4 -> Importar pets do abrigo
                       5 -> Sair
                """);
    }

}
