package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.CadastrarAbrigoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AbrigoCadastroExiste implements ValidacaoCadastroAbrigo {

    @Autowired
    private AbrigoRepository abrigoRepository;

    @Override
    public void validar(CadastrarAbrigoDTO dto) {
        boolean existsByTelefoneNomeEmail = abrigoRepository.existsByNomeOrTelefoneOrEmail(dto);
        if (existsByTelefoneNomeEmail) {
            throw new ValidacaoException("Dados já cadastrados para outro abrigo!");
        }
    }
}
