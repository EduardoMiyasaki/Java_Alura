package br.com.alura.service;

import br.com.alura.client.ClientHttp;
import br.com.alura.domain.Pet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class PetService {

    private ClientHttp client;
    static Scanner tecladoTexto = new Scanner(System.in);

    public PetService(ClientHttp client) {
        this.client = client;
    }

    public List<Pet> listarPets(String uri) {
        try {
            HttpResponse<String> response = client.dispararRequisicaoGET(uri);
            int statusCode = response.statusCode();
            if (statusCode == 404 || statusCode == 500) {
                System.out.println("ID ou nome não cadastrado!");
            }
            String json = response.body();
            Pet[] listaPets = new ObjectMapper().readValue(json, Pet[].class);
            List<Pet> petsList = Arrays.stream(listaPets).toList();
            System.out.println(petsList);
            return Arrays.stream(listaPets).toList();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
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
                if (response.statusCode() == 200) {
                    System.out.println("Pet " + pet.getNome() + " cadastrado com sucesso");
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void importarPets() {
        System.out.println("Digite o id ou nome do abrigo:");
        String idOuNome = tecladoTexto.nextLine();

        System.out.println("Digite o nome do arquivo CSV:");
        String nomeArquivo = tecladoTexto.nextLine();
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
                if (response.statusCode() == 200) {
                    System.out.println("Pet " + pet.getNome() + " cadastrado com sucesso");
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
