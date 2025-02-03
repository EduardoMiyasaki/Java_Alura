package br.com.alura;

import br.com.alura.client.ClientHttp;
import br.com.alura.service.AbrigoService;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ListarAbrigoCommand implements Command {


    @Override
    public void execute() {
        try {
            ClientHttp client = new ClientHttp();
            AbrigoService abrigoService = new AbrigoService(client);

            abrigoService.listarAbrigos("http://localhost:8080/abrigos");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
