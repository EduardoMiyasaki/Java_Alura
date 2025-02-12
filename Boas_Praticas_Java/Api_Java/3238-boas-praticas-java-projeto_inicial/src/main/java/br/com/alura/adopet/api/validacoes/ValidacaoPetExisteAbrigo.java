package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.DadosDetalhesPet;
import br.com.alura.adopet.api.model.Abrigo;

public interface ValidacaoPetExisteAbrigo {

    void validar(Abrigo abrigo, DadosDetalhesPet pet);
}
