package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.DadosCadastroMedico;
import med.voll.api.dto.DadosListagemMedico;
import med.voll.api.dto.DadosUpdateMedico;
import med.voll.api.model.Medico;
import med.voll.api.service.MedicoService;
import med.voll.api.validacao.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosCadastroMedico> saveDoctor(@RequestBody @Valid DadosCadastroMedico dados) {
        try {
            medicoService.saveDoctor(dados);
            return ResponseEntity.status(201).body(dados);
        } catch (ValidacaoException e) {
            return ResponseEntity.status(404).body(dados);
        }
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> getAllDoctors(Pageable paginacao) {
        try {
            return ResponseEntity.status(200).body(medicoService.getAllDoctors(paginacao));
        } catch (ValidacaoException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosUpdateMedico> updateDoctor(@PathVariable Long id,
                                                          @RequestBody @Valid DadosUpdateMedico dados){
        try {
            return ResponseEntity.status(200).body(medicoService.updateDoctor(id, dados));
        } catch (ValidacaoException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<String> desactiveDoctor(@PathVariable Long id){
        try {
            medicoService.desactiveDoctor(id);
            return ResponseEntity.status(204).build(    );
        } catch (ValidacaoException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id){
        try {
            medicoService.deleteDoctor(id);
            return ResponseEntity.status(204).build();
        } catch (ValidacaoException e) {
            return ResponseEntity.status(404).build();
        }
    }
}
