package br.com.alura.services;

import br.com.alura.client.ClientHttp;
import br.com.alura.domain.Abrigo;
import br.com.alura.service.AbrigoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbrigoServiceTest {

    private ClientHttp client = mock(ClientHttp.class);
    private AbrigoService abrigoService = new AbrigoService(client);
    private HttpResponse<String> response = mock(HttpResponse.class);
    private Abrigo abrigo = new Abrigo("Teste", "(55)941159058", "Eduardo@gmail.com");

    @Test
    public void verificarQuandoHaAbrigo() throws JsonProcessingException {
        abrigo.setId(0L);

//        String expectedAbrigosCadastrados = "Abrigos cadastrados: ";
        String expectIdENome = "[\"id\":\"0\", \"nome\":\"Teste\", \"telefone\":\"(55)941159058\",\"email\":\"Eduardo@gmail.com\"\n" +
                "]";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        // simula o retorno da chamada da api com abrigo.toString
        when(client.dispararRequisicaoGET(anyString())).thenReturn(response);
        when(response.body()).thenReturn("[{" + abrigo.toString() + "}]");

        abrigoService.listarAbrigos("http://localhost:8080/abrigos");


        String[] lines = baos.toString().split(System.lineSeparator());
//        String actualAbrigosCadastrados = lines[0];
        String actualIdENome = lines[0];

//        Assertions.assertEquals(expectedAbrigosCadastrados, actualAbrigosCadastrados);
        Assertions.assertEquals(expectIdENome, actualIdENome);
    }

    @Test
    public void deveVerificarQuandoNaoHaAbrigo() throws JsonProcessingException {
        String expectedReponse = "Sem abrigos";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        // simula o resultado da chamada da api []
        when(client.dispararRequisicaoGET(anyString())).thenReturn(response);
        when(response.body()).thenReturn("[]");

        abrigoService.listarAbrigos("http://localhost:8080/abrigos");

        String[] lines = baos.toString().split(System.lineSeparator());
        String actualResponse = lines[0];

        Assertions.assertEquals(expectedReponse , actualResponse);
    }

}
