package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.Abrigo;
import jakarta.validation.constraints.NotNull;

public record CadastrarPetNoAbrigoDTO(@NotNull Abrigo abrigo, boolean adotado) {
}
