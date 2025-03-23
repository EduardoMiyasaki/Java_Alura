package med.voll.api.service;

import med.voll.api.dto.DadosCadastroMedico;
import med.voll.api.dto.DadosListagemMedico;
import med.voll.api.dto.DadosUpdateMedico;
import med.voll.api.model.Medico;
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

    public void saveDoctor(DadosCadastroMedico dados) {
        try {
            medicoRepository.save(new Medico(dados));
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

    public DadosUpdateMedico updateDoctor(Long id, DadosUpdateMedico dados) {

        Optional<Medico> medico0 = medicoRepository.findById(id);

        if (medico0.isEmpty()) {
            throw new ValidacaoException("Médico não encontrado");
        }
        Medico medico = medico0.get();
        BeanUtils.copyProperties(dados, medico);
        return dados;

    }

    public void desactiveDoctor(Long id) {

        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Médico não encontrado"));

        medico.setAtivo(false);
    }

    public void deleteDoctor(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(()-> new ValidacaoException("Médico não encontrado!"));

        medicoRepository.delete(medico);
    }

    private static List<DadosListagemMedico> converterMedico(List<Medico> medicos) {
        return medicos.stream()
                .map(m -> new DadosListagemMedico(m.getId(), m.getNome(),
                        m.getEmail(), m.getCrm(), m.getEspecialidade()))
                .toList();
    }


}
