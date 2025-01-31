package br.com.alura;

import br.com.alura.client.ClientHttp;
import br.com.alura.domain.Abrigo;
import br.com.alura.domain.AbrigoDTO;
import br.com.alura.service.AbrigoService;
import br.com.alura.service.PetService;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Scanner;

public class AdopetConsoleApplication {

    private static ClientHttp client = new ClientHttp();
    private static AbrigoService abrigoService = new AbrigoService(client);
    private static PetService petService = new PetService(client);
    private static Scanner teclado = new Scanner(System.in);
    private static Scanner tecladoTexto = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            int opcaoEscolhida = 0;
            while (opcaoEscolhida != 5) {

                mostrarMenu();
                opcaoEscolhida = teclado.nextInt();

                switch (opcaoEscolhida) {
                    case 1:
                       listarAbrigos();
                        break;
                    case 2:
                        cadastrarAbrigo();
                        break;
                    case 3:
                        listarPets();
                        break;
                    case 4:
                        importarPets();
                        break;
                    case 5:
                        break;
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

    public static void listarAbrigos() {
        List<Abrigo> listaAbrigos = abrigoService.listarAbrigos("http://localhost:8080/abrigos");
        System.out.println(listaAbrigos);
    }

    private static void cadastrarAbrigo() {
        System.out.println("Digite o nome do abrigo:");
        String nome = tecladoTexto.nextLine();
        System.out.println("Digite o telefone do abrigo:");
        String telefone = tecladoTexto.nextLine();
        System.out.println("Digite o email do abrigo:");
        String email = tecladoTexto.nextLine();

        String uri = "http://localhost:8080/abrigos";
        abrigoService.cadastrarAbrigo(new AbrigoDTO(nome,telefone,email), uri);
    }

    public static void listarPets() {
        System.out.println("Digite o id ou nome do abrigo:");
        String idOuNome = tecladoTexto.nextLine();

        String uri = "http://localhost:8080/abrigos/" + idOuNome + "/pets";

        petService.listarPets(uri);

    }

    public static void importarPets() {
        System.out.println("Digite o id ou nome do abrigo:");
        String idOuNome = tecladoTexto.nextLine();

        System.out.println("Digite o nome do arquivo CSV:");
        String nomeArquivo = tecladoTexto.nextLine();

       petService.importarPets(idOuNome, nomeArquivo);
    }
}
