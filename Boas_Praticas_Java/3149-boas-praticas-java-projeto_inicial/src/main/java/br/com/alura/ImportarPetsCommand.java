package br.com.alura;

import br.com.alura.client.ClientHttp;
import br.com.alura.service.PetService;

import java.util.Scanner;

public class ImportarPetsCommand implements Command {

    @Override
    public void execute() {

        Scanner tecladoTexto = new Scanner(System.in);
        ClientHttp client = new ClientHttp();
        PetService petService = new PetService(client);

        System.out.println("Digite o id ou nome do abrigo:");
        String idOuNome = tecladoTexto.nextLine();

        System.out.println("Digite o nome do arquivo CSV:");
        String nomeArquivo = tecladoTexto.nextLine();

        petService.importarPets(idOuNome, nomeArquivo);
    }
}
