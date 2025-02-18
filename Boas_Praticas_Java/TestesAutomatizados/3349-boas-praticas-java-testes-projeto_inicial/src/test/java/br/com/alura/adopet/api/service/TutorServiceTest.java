package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AtualizacaoTutorDto;
import br.com.alura.adopet.api.dto.CadastroTutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TutorServiceTest {

    @InjectMocks
    private TutorService tutorService;

    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private Tutor tutor;

    @Mock
    private CadastroTutorDto dto;

    @Mock
    private AtualizacaoTutorDto dto1;

    @Test
    @DisplayName("Deveria retorna exception")
    void tutorJaExiste() {

        // ASSERT
        given(tutorRepository.existsByTelefoneOrEmail(dto.telefone(), dto.email()))
                .willReturn(true);

        // ASSERTIONS + ACT
        Assertions.assertThrows(ValidacaoException.class, ()-> tutorService.cadastrar(dto));
    }

    @Test
    @DisplayName("Deveria retorna nada")
    void tutorNaoExiste() {

        // ASSERT
        given(tutorRepository.existsByTelefoneOrEmail(dto.telefone(), dto.email()))
                .willReturn(false);

        // ACT
        tutorService.cadastrar(dto);

        // ASSERTIONS
        BDDMockito.then(tutorRepository).should().save(new Tutor(dto));
    }

    @Test
    void deveriaAtualizarDados(){

        // ARRANGE
        given(tutorRepository.getReferenceById(dto1.id())).willReturn(tutor);
        // ACT
        tutorService.atualizar(dto1);
        // ASSERTION
       BDDMockito.then(tutor).should().atualizarDados(dto1);
    }
}