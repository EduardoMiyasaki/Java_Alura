package br.com.alura.services;

import br.com.alura.client.ClientHttp;
import br.com.alura.domain.Pet;
import br.com.alura.domain.PetDTO;
import br.com.alura.service.PetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PetServiceTest {

    private ClientHttp client = mock(ClientHttp.class);
    private PetService petService = new PetService(client);
    private HttpResponse<String> response = mock(HttpResponse.class);
    private Pet pet = new Pet("CACHORRO", "Brutus", "XITSU", 2, "Preto", 12.0f);

    @Test
    public void testarListarPets() throws JsonProcessingException {

        Pet[] pets = new ObjectMapper().readValue(pet.toString(), Pet[].class);
        List<Pet> listPets = Arrays.stream(pets).toList();

        PetDTO petDto = new PetDTO(listPets.get(0).getTipo(), listPets.get(0).getNome(), listPets.get(0).getRaca(), listPets.get(0).getIdade(), listPets.get(0).getCor(), listPets.get(0).getPeso());
        Pet pet = new Pet(petDto);
        String expectedPet = pet.toString();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        when(response.body()).thenReturn("[{" + pet.toString() + "}]");
        when(client.dispararRequisicaoGET(anyString())).thenReturn(response);

        petService.listarPets("http://localhost:8080/abrigos/Brutus/pets");

        String[] lines = baos.toString().split(System.lineSeparator());
        String actualPet = lines[0];

        Assertions.assertEquals(expectedPet, actualPet);
    }

    @Test
    public void verificarImportarPetsDoAbrigo() {
//        String userInput = String.format("Teste%spets.csv",
//                System.lineSeparator());
//
//        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
//        System.setIn(bais);

        when(client.dispararRequisicaoPost(anyString(), any())).thenReturn(response);

        petService.importarPets();
    }
}
