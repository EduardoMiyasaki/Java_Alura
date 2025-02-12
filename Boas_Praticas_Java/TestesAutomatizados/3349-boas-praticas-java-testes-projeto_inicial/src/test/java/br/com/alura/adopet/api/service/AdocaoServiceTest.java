package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoPetComAdocaoEmAndamento;
import br.com.alura.adopet.api.validacoes.ValidacaoPetDisponivel;
import br.com.alura.adopet.api.validacoes.ValidacaoSolicitacaoAdocao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AdocaoServiceTest {

    @InjectMocks
    private AdocaoService adocaoService;

    @Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private EmailService emailService;

    @Spy
    private List<ValidacaoSolicitacaoAdocao> validacoes = new ArrayList<>();

    @Mock
    private ValidacaoPetDisponivel validacaoPetDisponivel;

    @Mock
    private ValidacaoPetComAdocaoEmAndamento validacaoPetComAdocaoEmAndamento;

    @Mock
    private Pet pet;

    @Mock
    private Tutor tutor;

    @Mock
    private Abrigo abrigo;

    @Mock
    private SolicitacaoAdocaoDto dto;

    @Captor
    private ArgumentCaptor<Adocao> adocaoCaptor;

    @Test
    @DisplayName("Salvar a adoção no banco")
    void deveriaSalvarAdocaoAoSolicitar() {

        this.dto = new SolicitacaoAdocaoDto(10L, 20L, "Motivo qualquer");
        // ARRANGE
        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);
        BDDMockito.given(pet.getAbrigo()).willReturn(abrigo);

        // ACT
        adocaoService.solicitar(dto);

        // ASSERT
        then(adocaoRepository).should().save(adocaoCaptor.capture());
        Adocao adocaoSalva = adocaoCaptor.getValue();
        Assertions.assertEquals(pet, adocaoSalva.getPet());
        Assertions.assertEquals(tutor, adocaoSalva.getTutor());
        Assertions.assertEquals(dto.motivo(), adocaoSalva.getMotivo());
    }

    @Test
    @DisplayName("Verificando se os metódos de validar estão sendo chamados")
    void deveriaChamarValidadoresDeAdocaoSolicitar() {

        this.dto = new SolicitacaoAdocaoDto(10L, 20L, "Motivo qualquer");
        // ARRANGE
        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);
        BDDMockito.given(pet.getAbrigo()).willReturn(abrigo);

        validacoes.add(validacaoPetComAdocaoEmAndamento);
        validacoes.add(validacaoPetDisponivel);

        // ACT
        adocaoService.solicitar(dto);

        // ASSERT
        BDDMockito.then(validacaoPetComAdocaoEmAndamento).should().validar(dto);
        BDDMockito.then(validacaoPetDisponivel).should().validar(dto);
    }

    @Test
    @DisplayName("Verificando se o Email está sendo disparado")
    void deveriaEnviarEmail() {

        this.dto = new SolicitacaoAdocaoDto(10L, 20L, "Motivo qualquer");
        // ARRANGE
        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);
        BDDMockito.given(pet.getAbrigo()).willReturn(abrigo);

        // ACT
        adocaoService.solicitar(dto);

        // ASSERT
        BDDMockito.then(emailService)
                .should()
                .enviarEmail(pet.getAbrigo().getEmail(), "Solicitação de adoção","Olá " + pet.getAbrigo().getNome() +"!\n\nUma solicitação de adoção foi registrada hoje para o pet: "  + pet.getNome() +". \nFavor avaliar para aprovação ou reprovação.");
    }
}