package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.ReprovacaoAdocaoDto;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
    private Adocao adocao;

    @Mock
    private SolicitacaoAdocaoDto dto;

    @Mock
    private AprovacaoAdocaoDto aprovacaoAdocaoDto;

    @Mock
    private ReprovacaoAdocaoDto reprovacaoAdocaoDto;

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
    @DisplayName("Verificando se o Email está sendo disparado quando a solicitação de adoção for chamada")
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
                .enviarEmail(pet.getAbrigo().getEmail(), "Solicitação de adoção", "Olá " + pet.getAbrigo().getNome() + "!\n\nUma solicitação de adoção foi registrada hoje para o pet: " + pet.getNome() + ". \nFavor avaliar para aprovação ou reprovação.");
    }


    @Test
    @DisplayName("Verificando se o Email está sendo disparado quando a solicitação de adoção for aprovada")
    void deveriaEnviarEmailAprovado() {

        // ARRANGE
        this.aprovacaoAdocaoDto = new AprovacaoAdocaoDto(10L);
        BDDMockito.given(adocaoRepository.getReferenceById(aprovacaoAdocaoDto.idAdocao())).willReturn(adocao);
        BDDMockito.given(adocao.getPet()).willReturn(pet);
        BDDMockito.given(adocao.getPet().getAbrigo()).willReturn(abrigo);
        BDDMockito.given(adocao.getTutor()).willReturn(tutor);
        BDDMockito.given(adocao.getData()).willReturn(LocalDateTime.now());

        // ACT
        adocaoService.aprovar(aprovacaoAdocaoDto);
        // ASSERT
        BDDMockito.then(emailService)
                .should()
                .enviarEmail(
                        adocao.getPet().getAbrigo().getEmail(),
                        "Adoção aprovada",
                        "Parabéns " + adocao.getTutor().getNome() + "!\n\nSua adoção do pet " + adocao.getPet().getNome() + ", solicitada em " + adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + ", foi aprovada.\nFavor entrar em contato com o abrigo " + adocao.getPet().getAbrigo().getNome() + " para agendar a busca do seu pet.");
    }

    @Test
    @DisplayName("Verificando se o Email está sendo disparado quando a solicitação de adoção for reprovada")
    void deveriaEnviarEmailReprovado() {

        // ARRANGE
        this.reprovacaoAdocaoDto = new ReprovacaoAdocaoDto(10L, "Justificativa qualquer");
        BDDMockito.given(adocaoRepository.getReferenceById(reprovacaoAdocaoDto.idAdocao())).willReturn(adocao);
        BDDMockito.given(adocao.getTutor()).willReturn(tutor);
        BDDMockito.given(adocao.getPet()).willReturn(pet);
        BDDMockito.given(adocao.getPet().getAbrigo()).willReturn(abrigo);
        BDDMockito.given(adocao.getData()).willReturn(LocalDateTime.now());

        // ACT
        adocaoService.reprovar(reprovacaoAdocaoDto);
        // ASSERT
        BDDMockito.then(emailService)
                .should()
                .enviarEmail(
                        adocao.getPet().getAbrigo().getEmail(),
                        "Reprovação de adoção",
                        "Olá " + adocao.getTutor().getNome() + "!\n\nInfelizmente sua adoção do pet " + adocao.getPet().getNome() + ", solicitada em " + adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + ", foi reprovada pelo abrigo " + adocao.getPet().getAbrigo().getNome() + " com a seguinte justificativa: " + adocao.getJustificativaStatus());
    }

    @Test
    @DisplayName("Verificando se está chamando o metódo marcarComoAprovada()")
    void deveriaChamarMarcarComoAprovada() {
        // ARRANGE
        this.aprovacaoAdocaoDto = new AprovacaoAdocaoDto(10L);
        BDDMockito.given(adocaoRepository.getReferenceById(aprovacaoAdocaoDto.idAdocao())).willReturn(adocao);
        BDDMockito.given(adocao.getPet()).willReturn(pet);
        BDDMockito.given(adocao.getPet().getAbrigo()).willReturn(abrigo);
        BDDMockito.given(adocao.getTutor()).willReturn(tutor);
        BDDMockito.given(adocao.getData()).willReturn(LocalDateTime.now());

        // ACT
        adocaoService.aprovar(aprovacaoAdocaoDto);

        // Assert
        BDDMockito.then(adocao).should().marcarComoAprovada();
    }

    @Test
    @DisplayName("Deveria chamar o metódo marcarComoReprovado")
    void deveriaChamarOMarcarComoReprovado() {

        // ARRANGE
        this.reprovacaoAdocaoDto = new ReprovacaoAdocaoDto(10L, "Justificativa qualquer");
        BDDMockito.given(adocaoRepository.getReferenceById(reprovacaoAdocaoDto.idAdocao())).willReturn(adocao);
        BDDMockito.given(adocao.getPet()).willReturn(pet);
        BDDMockito.given(adocao.getPet().getAbrigo()).willReturn(abrigo);
        BDDMockito.given(adocao.getTutor()).willReturn(tutor);
        BDDMockito.given(adocao.getData()).willReturn(LocalDateTime.now());

        // ACT
        adocaoService.reprovar(reprovacaoAdocaoDto);
        // ASSERT
        BDDMockito.then(adocao).should().marcarComoReprovada(reprovacaoAdocaoDto.justificativa());
    }
}