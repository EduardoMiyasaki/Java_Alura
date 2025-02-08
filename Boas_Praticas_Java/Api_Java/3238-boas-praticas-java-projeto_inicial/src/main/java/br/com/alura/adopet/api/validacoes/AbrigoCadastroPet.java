package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.DadosDetalhesPet;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;

public class AbrigoCadastroPet implements ValidacaoPetExisteAbrigo {

    @Override
    public void validar(Abrigo abrigo, DadosDetalhesPet pet) {
        boolean exists = abrigo.getPets().stream().anyMatch(p -> p.equals(pet));
        if (exists) {
            throw new ValidacaoException("Pet já existe no abrigo");
        }
    }
}
