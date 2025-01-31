package br.com.alura.service;

import br.com.alura.client.ClientHttp;
import br.com.alura.domain.Pet;
import br.com.alura.domain.PetDTO;
import com.google.gson.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class PetService {

    private ClientHttp client;
    private Gson gson;
    private List<Pet> listaPets = new ArrayList<>();

    public PetService(ClientHttp client) {
        this.client = client;
        this.gson = new Gson().newBuilder().setPrettyPrinting().create();
    }

    public void listarPets(String uri) {

        HttpResponse<String> response = client.dispararRequisicaoGET(uri);
        int statusCode = response.statusCode();
        if (statusCode == 404 || statusCode == 500) {
            System.out.println("ID ou nome não cadastrado!");
        }
        String json = response.body();
        PetDTO petDTO = gson.fromJson(json, PetDTO.class);
        Pet pet = new Pet(petDTO);
        listaPets.add(pet);

        listaPets.forEach(System.out::println);
    }

    public void importarPets(String idOuNome, String nomeArquivo) {

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(nomeArquivo));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] campos = line.split(",");
                String tipo = campos[0];
                String nome = campos[1];
                String raca = campos[2];
                int idade = Integer.parseInt(campos[3]);
                String cor = campos[4];
                Float peso = (Float) Float.parseFloat(campos[5]);

                Pet pet = new Pet(tipo, nome, raca, idade, cor, peso);

                String uri = "http://localhost:8080/abrigos/" + idOuNome + "/pets";

                HttpResponse<String> response = client.dispararRequisicaoPost(pet, uri);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
