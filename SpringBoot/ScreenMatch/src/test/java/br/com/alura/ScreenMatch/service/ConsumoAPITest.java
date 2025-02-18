package br.com.alura.ScreenMatch.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConsumoAPITest {

    @InjectMocks
    private ConsumoAPI consumoAPI;

    @Test
    @DisplayName("Erro 400 para endereço errado")
    void deveriaRetornarErro() {
        //ARRANGE
        var endereco = "";
        // ACT
        var response = consumoAPI.obterDados(endereco);
        // ASSERTIONS
        Assertions.assertEquals(400, response);
    }
}