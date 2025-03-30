package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.DadosCadastroPaciente;
import med.voll.api.dto.DadosDetalhamentoPaciente;
import med.voll.api.dto.DadosListagemPaciente;
import med.voll.api.dto.DadosUpdatePacient;
import med.voll.api.model.Paciente;
import med.voll.api.service.PacienteService;
import med.voll.api.validacao.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    @Transactional
    public ResponseEntity savePacient(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBulder) {
        try {
            Paciente paciente = pacienteService.savePacient(dados);

            var uri = uriBulder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

            return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
        } catch (ValidacaoException e) {
            return ResponseEntity.status(404).body(dados);
        }
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> getAllPacients(Pageable paginacao) {
        return ResponseEntity.status(200).body(pacienteService.getAllPacients(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPaciente> getOneDoctor(@PathVariable Long id) {
        try {
            return ResponseEntity.status(200).body(pacienteService.getOneDoctor(id));
        } catch (ValidacaoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoPaciente> updatePacient(@PathVariable Long id,
                                                                   @RequestBody @Valid DadosUpdatePacient dados) {
        try {
            return ResponseEntity.status(200).body(pacienteService.updatePaciente(id, dados));
        } catch (ValidacaoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<String> desativePacient(@PathVariable Long id) {
        try {
            pacienteService.desativePacient(id);
            return ResponseEntity.status(204).build();
        } catch (ValidacaoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
