package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AbrigoServiceTest {

    @InjectMocks
    private AbrigoService abrigoService;

    @Mock
    private AbrigoRepository abrigoRepository;

    @Mock
    private Abrigo abrigo;

    @Mock
    private CadastroAbrigoDto dto;

    @Test
    @DisplayName("Cadastro já existe")
    void deveriaRetornarException() {

        // ASSERT
        BDDMockito.given(abrigoRepository.existsByNomeOrTelefoneOrEmail(dto.nome(), dto.telefone(), dto.email()))
                .willReturn(true);

        // ASSERTIONS
        Assertions.assertThrows(ValidacaoException.class, () -> abrigoService.cadastrar(dto));
    }

    @Test
    @DisplayName("Cadastro não existe")
    void NaodeveriaRetornarException() {

        // ASSERT
        BDDMockito.given(abrigoRepository.existsByNomeOrTelefoneOrEmail(dto.nome(), dto.telefone(), dto.email()))
                .willReturn(false);

        // ASSERTIONS
        Assertions.assertDoesNotThrow(() -> abrigoService.cadastrar(dto));
    }

    @Test
    @DisplayName("verificando se o metódo está chamando e salvando no banco")
    void chamandoBanco() {

        // ASSERT
        BDDMockito.given(abrigoRepository.existsByNomeOrTelefoneOrEmail(dto.nome(), dto.telefone(), dto.email()))
                .willReturn(false);

        // ACT
        abrigoService.cadastrar(dto);

        // ASSERTIONS
        BDDMockito.then(abrigoRepository).should().save(new Abrigo(dto));
    }

    @Test
    @DisplayName("Chamando abrigo pelo id")
    void deveriaRetornarUmAbrigo() {

        String id = "fds";

        // ARRANGE
        BDDMockito.given(abrigoRepository.findByNome(id)).willReturn(Optional.of(abrigo));

        // ACT
        var response = abrigoService.carregarAbrigo(id);

        // ASSERTIONS
        Assertions.assertEquals(abrigo, response);
    }

    @Test
    @DisplayName("Chamando abrigo pelo id")
    void NaodeveriaRetornarUmAbrigo() {

        // ARRANGE
        String id = "m";

        // ASSERTIONS +  ACT
        Assertions.assertThrows(ValidacaoException.class, () -> abrigoService.carregarAbrigo(id));
    }
}