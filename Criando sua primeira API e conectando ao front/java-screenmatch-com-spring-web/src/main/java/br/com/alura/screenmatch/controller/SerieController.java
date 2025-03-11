package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.service.SerieService;
import br.com.alura.screenmatch.validacao.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService serieService;

    @GetMapping
    public ResponseEntity<List<SerieDTO>> obterSeries() {
        List<SerieDTO> seriesDTO = serieService.obterSeries();
        return ResponseEntity.status(200).body(seriesDTO);
    }

    @GetMapping("/top5")
    public ResponseEntity<List<SerieDTO>> obterTop5Series() {
        List<SerieDTO> seriesDTO = serieService.obterTop5Series();
        return ResponseEntity.status(200).body(seriesDTO);
    }

    @GetMapping("/lancamentos")
    public ResponseEntity<List<SerieDTO>> obterLancamentos() {
        List<SerieDTO> seriesDTO = serieService.obterLancamentos();
        return ResponseEntity.status(200).body(seriesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SerieDTO> obterSeriePeloID(@PathVariable Long id) {
        try {
            SerieDTO serieDTO = serieService.obterSeriePeloID(id);
            return ResponseEntity.status(200).body(serieDTO);
        } catch (ValidacaoException e) {
            return ResponseEntity.status(404).build();
        }
    }
}
