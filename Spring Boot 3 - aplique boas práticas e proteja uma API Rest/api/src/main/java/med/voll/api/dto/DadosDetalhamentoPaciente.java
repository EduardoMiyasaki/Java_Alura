package med.voll.api.dto;

import med.voll.api.model.Endereco;
import med.voll.api.model.Paciente;

public record DadosDetalhamentoPaciente(String nome,
                                        String email,
                                        String telefone,
                                        String cpf,
                                        Endereco endereco) {

    public DadosDetalhamentoPaciente(Paciente p) {
        this(p.getNome(), p.getEmail(), p.getTelefone(), p.getCpf(), p.getEndereco());
    }
}
