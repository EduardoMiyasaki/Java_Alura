package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.CadastroTutorDto;
import br.com.alura.adopet.api.service.TutorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TutorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TutorService tutorService;

    @Autowired
    private JacksonTester<CadastroTutorDto> jacksonDto;


    @Test
    @DisplayName("Tutor inválido deveria retorna código 400")
    void tutorComErro() throws Exception {

        // ARRANGE
        var json = "{}";
        // ACT
        var response = mvc.perform(
                post("/tutores")
                        .content(json).
                        contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();
        // ASSERTIONS
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Cadastro tutor com sucesso")
    void cadastroTutor() throws Exception {
        // ASSERT
        CadastroTutorDto dto = new CadastroTutorDto("Eduardo","11941159059","eduardo@gmail.com");
        // ACT
        var response = mvc.perform(
                post("/tutores")
                        .content(jacksonDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();
        // ASSERTIONS
        Assertions.assertEquals(200, response.getStatus());

    }
}