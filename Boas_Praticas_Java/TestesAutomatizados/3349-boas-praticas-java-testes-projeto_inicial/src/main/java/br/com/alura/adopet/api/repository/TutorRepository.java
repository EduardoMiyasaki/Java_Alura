package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

    boolean existsByTelefoneOrEmail(String telefone, String email);

//    int countByTutorIdAndStatus(Long idTutor, StatusAdocao status);

}
