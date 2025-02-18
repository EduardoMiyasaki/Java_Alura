package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ValidacaoTutorComLimiteDeAdocoesTest {

    @InjectMocks
    private ValidacaoTutorComLimiteDeAdocoes validacao;

    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private SolicitacaoAdocaoDto dto;

    @Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private Tutor tutor;

    @Mock
    private Pet pet;

    @Test
    @DisplayName("Deveria retornar Exceção por conta de ter 5 adoções")
    void deveriaRetornarException() {

        // ARRANGE
        BDDMockito.given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);
        List<Adocao> listaAdocoes = new ArrayList<>();

        for (var i = 0; i <= 5; i++) {
            Adocao adocao = new Adocao(tutor, pet, ".");
            adocao.marcarComoAprovada();
            listaAdocoes.add(adocao);
        }

        BDDMockito.given(adocaoRepository.findAll()).willReturn(listaAdocoes);

        // ASSERTION + ACT
        Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(dto));
    }

    @Test
    @DisplayName("Não Deveria retornar Exceção")
    void NaodeveriaRetornarException() {

        // ARRANGE
        List<Adocao> listaAdocoes = new ArrayList<>();

        BDDMockito.given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);
        BDDMockito.given(adocaoRepository.findAll()).willReturn(listaAdocoes);

        // ASSERTION + ACT
        Assertions.assertDoesNotThrow(() -> validacao.validar(dto));
    }

}