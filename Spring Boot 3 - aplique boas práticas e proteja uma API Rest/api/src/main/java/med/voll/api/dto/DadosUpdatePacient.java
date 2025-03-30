package med.voll.api.dto;

public record DadosUpdatePacient(String nome,
                                 String email,
                                 String telefone,
                                 DadosEndereco dadosEndereco) {
}
