package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.DadosCadastroMedico;
import med.voll.api.dto.DadosDetalhamentoMedico;
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
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    @Transactional
    public ResponseEntity saveDoctor(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBulder) {

        var medico = medicoService.saveDoctor(dados);

        var uri = uriBulder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> getAllDoctors(Pageable paginacao) {
        try {
            return ResponseEntity.ok(medicoService.getAllDoctors(paginacao));
        } catch (ValidacaoException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedico> getOneDoctor(@PathVariable Long id) {
        return ResponseEntity.ok(medicoService.getOneDoctor(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedico> updateDoctor(@PathVariable Long id,
                                                                @RequestBody @Valid DadosUpdateMedico dados) {
        return ResponseEntity.ok(medicoService.updateDoctor(id, dados));
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<String> desactiveDoctor(@PathVariable Long id) {
        try {
            medicoService.desactiveDoctor(id);
            return ResponseEntity.status(204).build();
        } catch (ValidacaoException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
        try {
            medicoService.deleteDoctor(id);
            return ResponseEntity.status(204).build();
        } catch (ValidacaoException e) {
            return ResponseEntity.status(404).build();
        }
    }
}
