package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidacaoPetDisponivelTest {

    @InjectMocks
    private ValidacaoPetDisponivel validacao;

    @Mock
    private PetRepository petRepository;

    @Mock
    private Pet pet;

    @Mock
    private SolicitacaoAdocaoDto dto;

    @Test
    @DisplayName("Pet disponível para solicitação de adoção")
    void deveriaPermitirAdocaoPet() {

        // dado essa ação retorne isso
        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(pet.getAdotado()).willReturn(false);
        // Assert + act
        // fazemos isso pois o retorno do metódo validar é void e estamos testando se lança ou não uma excessão
        Assertions.assertDoesNotThrow(() -> validacao.validar(dto));
    }

    @Test
    @DisplayName("Pet Indisponível para solicitação de adoção")
    void naoDeveriaPermitirAdocaoPet() {

        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(pet.getAdotado()).willReturn(true);

        // Assert + act
        Assertions.assertThrows(ValidacaoException.class,()-> validacao.validar(dto));
    }

}