package med.voll.api.service;

import med.voll.api.dto.DadosCadastroMedico;
import med.voll.api.dto.DadosDetalhamentoMedico;
import med.voll.api.dto.DadosListagemMedico;
import med.voll.api.dto.DadosUpdateMedico;
import med.voll.api.model.Medico;
import med.voll.api.model.Paciente;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.validacao.ValidacaoException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico saveDoctor(DadosCadastroMedico dados) {

        try {
            var medico = new Medico(dados);
            medicoRepository.save(medico);
            return medico;
        } catch (IllegalArgumentException e) {
            throw new ValidacaoException("Dados Inválidos");
        }
    }

    public Page<DadosListagemMedico> getAllDoctors(Pageable paginacao) {
        return medicoRepository.findAllByAtivoTrue(paginacao)
                .map(DadosListagemMedico::new);

//        List<Medico> medicos = medicoRepository.findAll();
//        return MedicoService.converterMedico(medicos);
    }

    public DadosDetalhamentoMedico getOneDoctor(Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        return new DadosDetalhamentoMedico(medico);
    }

    public DadosDetalhamentoMedico updateDoctor(Long id, DadosUpdateMedico dados) {

        Medico medico = medicoRepository.getReferenceById(id);

        if (dados.nome() != null) medico.setNome(dados.nome());
        if (dados.telefone() != null) medico.setEmail(dados.telefone());
        if (dados.telefone() != null) medico.setTelefone(dados.telefone());

        return new DadosDetalhamentoMedico(medico);
    }

    public void desactiveDoctor(Long id) {

        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Médico não encontrado"));

        medico.setAtivo(false);
    }

    public void deleteDoctor(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Médico não encontrado!"));

        medicoRepository.delete(medico);
    }

    private static List<DadosListagemMedico> converterMedico(List<Medico> medicos) {
        return medicos.stream()
                .map(m -> new DadosListagemMedico(m.getId(), m.getNome(),
                        m.getEmail(), m.getCrm(), m.getEspecialidade()))
                .toList();
    }


}
