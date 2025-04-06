package med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;

public record dadosAutenticacao(@NotBlank String login,
                                @NotBlank String senha){
}
