package med.voll.api.service;

import med.voll.api.dto.DadosCadastroPaciente;
import med.voll.api.dto.DadosDetalhamentoPaciente;
import med.voll.api.dto.DadosListagemPaciente;
import med.voll.api.dto.DadosUpdatePacient;
import med.voll.api.model.Paciente;
import med.voll.api.repository.PacienteRepository;
import med.voll.api.validacao.ValidacaoException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente savePacient(DadosCadastroPaciente dados) {
        try {
            Paciente paciente = new Paciente(dados);
            pacienteRepository.save(paciente);
            return paciente;
        } catch (IllegalArgumentException e) {
            throw new ValidacaoException("Erro ao cadastrar paciente");
        }
    }

    public Page<DadosListagemPaciente> getAllPacients(Pageable paginacao) {
        return pacienteRepository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
    }

    public DadosDetalhamentoPaciente getOneDoctor(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Paciente não encontrado"));

        return new DadosDetalhamentoPaciente(paciente);
    }

    public DadosDetalhamentoPaciente updatePaciente(Long id, DadosUpdatePacient dados) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Paciente não encontrado"));

        if (dados.nome() != null) paciente.setNome(dados.nome());
        if (dados.email() != null) paciente.setEmail(dados.email());
        if (dados.telefone() != null) paciente.setTelefone(dados.telefone());

        return new DadosDetalhamentoPaciente(paciente);
    }

    public void desativePacient(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Paciente não encontrado"));

        paciente.desativar();
    }


}
