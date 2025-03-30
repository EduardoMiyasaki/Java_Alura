package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

public record DadosUpdateMedico(String nome,
        @Pattern(regexp = "\\d{7,11}")
        String telefone,
        @Valid DadosEndereco dadosEndereco) {
}
