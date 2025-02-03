package br.com.alura.service;

import br.com.alura.client.ClientHttp;
import br.com.alura.domain.Abrigo;
import br.com.alura.domain.AbrigoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class AbrigoService {

    private ClientHttp client;

    public AbrigoService(ClientHttp client) {
        this.client = client;
    }

    public void listarAbrigos(String uri) throws JsonProcessingException {
        HttpResponse<String> response = client.dispararRequisicaoGET(uri);
        // Pegando o retorno da requisição
        String responseBody = response.body();
        // Passando os valores do JSON para dentro da classe Abrigo, porem se tiver
        // mais de um abrigo tem um Array
        // ObjectMapper objeto que vai deserializar
        // read.value()primeiro passa o json que sera deserializado e depois a classe que espera esse dados
        Abrigo[] abrigos = new ObjectMapper().readValue(responseBody, Abrigo[].class);
        // transforma um array em List para ter mais métodos
        List<Abrigo> abrigoList = Arrays.stream(abrigos).toList();
        if (abrigoList.isEmpty()) {
            System.out.println("Sem abrigos");
        } else {
            System.out.println(abrigoList);
        }
    }

    public void cadastrarAbrigo(AbrigoDTO abrigoDTO, String uri) {

        HttpResponse<String> response = client.dispararRequisicaoPost(abrigoDTO, uri);
        int statusCode = response.statusCode();
        String responseBody = response.body();

        if (statusCode == 200) {
            System.out.println("Abrigo cadastrado com sucesso!");
            System.out.println(responseBody);
        } else if (statusCode == 400 || statusCode == 500) {
            System.out.println("Erro ao cadastrar o abrigo:");
            System.out.println(responseBody);
        }
    }
}
