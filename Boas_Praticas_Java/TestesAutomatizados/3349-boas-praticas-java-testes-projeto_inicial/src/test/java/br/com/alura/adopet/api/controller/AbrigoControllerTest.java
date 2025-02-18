package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.TipoPet;
import br.com.alura.adopet.api.service.AbrigoService;
import br.com.alura.adopet.api.service.PetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
class AbrigoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AbrigoService abrigoService;

    @MockBean
    private PetService petService;

    @Autowired
    private JacksonTester<CadastroAbrigoDto> jsonDto;

    @Autowired
    private JacksonTester<CadastroPetDto> jsonDto1;
    @Mock
    private Abrigo abrigo;

    @Test
    @DisplayName("Deveria retornar erro 400 para cadastro de abrigo")
    void cadastroAbrigoErro() throws Exception {

        // ARRANGE
        String json = "{}";

        // ACT
        var response = mvc.perform(post("/abrigos")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)

        ).andReturn().getResponse();

        // ASSERT
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Deveria retornar código 200 para cadastro de abrigo")
    void cadastroAbrigo() throws Exception {

        // ARRANGE
        CadastroAbrigoDto dto = new CadastroAbrigoDto("Abrigo", "11914345678", "eduardomiyasaki22@gmail.com");

        // ACT
        var response = mvc.perform(
                post("/abrigos")
                        .content(jsonDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();
        // ASSERTIONS
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Deveria retornar código 200 para cadastro de pet no abrigo")
    void cadastroPetNoAbrigo() throws Exception {

//        // ARRANGE
//        String id = "1";
//        BDDMockito.given(abrigoService.carregarAbrigo(id)).willReturn(abrigo);
//
//        CadastroPetDto dto = new CadastroPetDto(TipoPet.CACHORRO, "Eduardo", "shytzu", 1, "amarelo", 12f);
//        String json = jsonDto1.write(dto).getJson();
//        // ACT
//        var response = mvc.perform(
//                post("/abrigos/1/pets")
//                        .content(abrigo, dto)
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andReturn().getResponse();
//        // ASSERTIONS
//        Assertions.assertEquals(200, response.getStatus());
    }

}