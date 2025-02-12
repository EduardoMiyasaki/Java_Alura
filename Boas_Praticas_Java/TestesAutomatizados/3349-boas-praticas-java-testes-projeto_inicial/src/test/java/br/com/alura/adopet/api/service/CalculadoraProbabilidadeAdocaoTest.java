package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculadoraProbabilidadeAdocaoTest {

    @Test
    @DisplayName("Probabilidade alta para gatos jovens com peso baixo")
    void probabilidadeAltaCenario01() {
        // idade 4 anos e 4kg

        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto
                ("Abrigo feliz", "941159058", "abrigo@gmail.com"));

        Pet pet = new Pet(new CadastroPetDto
                (TipoPet.GATO, "MIAU", "Siames", 4, "Cinza", 4.0f), abrigo);

        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        // Faz a comparação da chamada com o valor esperado
        Assertions.assertEquals(ProbabilidadeAdocao.ALTA, probabilidade);
    }

    @Test
    @DisplayName("Probabilidade média para gatos idosos com peso baixo")
    void probabilidadeMediaCenario01() {
        // idade 4 anos e 4kg

        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto
                ("Abrigo feliz", "941159058", "abrigo@gmail.com"));

        Pet pet = new Pet(new CadastroPetDto
                (TipoPet.GATO, "Gato", "Siames", 15, "Cinza", 4.0f), abrigo);

        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        // Faz a comparação da chamada com o valor esperado
        Assertions. assertEquals(ProbabilidadeAdocao.MEDIA, probabilidade);
    }
}