package med.voll.api.dto;

import med.voll.api.model.Endereco;
import med.voll.api.model.Medico;

public record DadosDetalhamentoMedico(Long id,
                                      String nome,
                                      String email,
                                      String crm,
                                      String telefone,
                                      Especialidade especialidade,
                                      Endereco endereco) {

    public DadosDetalhamentoMedico(Medico m) {
        this(m.getId(), m.getNome(), m.getEmail(), m.getCrm(), m.getTelefone(), m.getEspecialidade(), m.getEndereco());
    }
}
