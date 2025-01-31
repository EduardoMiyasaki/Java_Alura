package br.com.alura.service;

import br.com.alura.domain.Abrigo;
import br.com.alura.domain.AbrigoDTO;
import br.com.alura.client.ClientHttp;
import com.google.gson.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class AbrigoService {

    private ClientHttp client;
    private List<Abrigo> listaAbrigos = new ArrayList<>();
    private Gson gson;

    public AbrigoService(ClientHttp client) {
        this.gson = new Gson().newBuilder().setPrettyPrinting().create();
        this.client = client;
    }

    public List<Abrigo> listarAbrigos(String uri) {

        HttpResponse<String> response = client.dispararRequisicaoGET(uri);

        String json = response.body();
        AbrigoDTO abrigoDTO = gson.fromJson(json, AbrigoDTO.class);
        Abrigo abrigo = new Abrigo(abrigoDTO);
        listaAbrigos.add(abrigo);

        return listaAbrigos;
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
