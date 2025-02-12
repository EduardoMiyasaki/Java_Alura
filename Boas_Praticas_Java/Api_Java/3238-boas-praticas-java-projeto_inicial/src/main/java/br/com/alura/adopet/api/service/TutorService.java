package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroTutorDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    public void cadastrar(CadastroTutorDTO dto) {
        verificarSeTutorExistePeloTelefoneOuEmail(dto);
        tutorRepository.save(new Tutor(dto));
    }

    public void atualizar(Long idTutor, CadastroTutorDTO dto) {
        verificarSeTutorExistePeloId(idTutor);
        Tutor tutor = verificarSeTutorExistePeloId(idTutor);

        tutor.setEmail(dto.email());
        tutor.setTelefone(dto.telefone());
        tutorRepository.save(tutor);
    }

    public void verificarSeTutorExistePeloTelefoneOuEmail(CadastroTutorDTO dto) {
        boolean existsTutor = tutorRepository.existsByEmailOrTelefone(dto.email(), dto.telefone());
        if (existsTutor) {
            throw new ValidacaoException("Tutor já existe");
        }
    }

    public Tutor verificarSeTutorExistePeloId(Long id) {
        return tutorRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("Tutor com esse ID não existe!"));


    }
}
