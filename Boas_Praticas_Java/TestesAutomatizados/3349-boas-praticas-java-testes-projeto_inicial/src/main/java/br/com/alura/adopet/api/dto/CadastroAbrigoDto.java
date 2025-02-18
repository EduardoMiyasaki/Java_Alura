package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CadastroAbrigoDto(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotBlank
        @Pattern(regexp = "\\(?\\d{2}\\)?\\d?\\d{5}-?\\d{4}")
        String telefone,
        @NotBlank
        @Email(message = "Email inválido")
        String email) {
}
