package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.DadosDetalhesPet;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;

public interface ValidacaoPetExisteAbrigo {

    void validar(Abrigo abrigo, DadosDetalhesPet pet);
}
