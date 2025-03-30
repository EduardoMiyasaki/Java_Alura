package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.DadosCadastroPaciente;
import med.voll.api.dto.DadosListagemPaciente;
import med.voll.api.dto.DadosUpdatePacient;
import med.voll.api.service.PacienteService;
import med.voll.api.validacao.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosCadastroPaciente> savePacient(@RequestBody @Valid DadosCadastroPaciente dados) {
        try {
            pacienteService.savePacient(dados);
            return ResponseEntity.status(201).body(dados);
        } catch (ValidacaoException e) {
            return ResponseEntity.status(404).body(dados);
        }
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> getAllPacients(Pageable paginacao) {
        try {
            return ResponseEntity.status(200).body(pacienteService.getAllPacients(paginacao));
        } catch (ValidacaoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosUpdatePacient> updatePacient(@PathVariable Long id,
                                                            @RequestBody @Valid DadosUpdatePacient dados) {
        try {
            return ResponseEntity.status(200).body(pacienteService.updatePaciente(id, dados));
        } catch (ValidacaoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<String> desativePacient(@PathVariable Long id){
        try {
            pacienteService.desativePacient(id);
            return ResponseEntity.status(204).build();
        } catch (ValidacaoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
