package br.com.alura;

import br.com.alura.client.ClientHttp;
import br.com.alura.domain.AbrigoDTO;
import br.com.alura.service.AbrigoService;

import java.util.Scanner;

public class CadastrarAbrigoCommand implements Command {

    @Override
    public void execute() {
        Scanner tecladoTexto = new Scanner(System.in);
        ClientHttp client = new ClientHttp();
        AbrigoService abrigoService = new AbrigoService(client);

        System.out.println("Digite o nome do abrigo:");
        String nome = tecladoTexto.nextLine();
        System.out.println("Digite o telefone do abrigo:");
        String telefone = tecladoTexto.nextLine();
        System.out.println("Digite o email do abrigo:");
        String email = tecladoTexto.nextLine();

        String uri = "http://localhost:8080/abrigos";

        abrigoService.cadastrarAbrigo(new AbrigoDTO(nome, telefone, email), uri);
    }
}
