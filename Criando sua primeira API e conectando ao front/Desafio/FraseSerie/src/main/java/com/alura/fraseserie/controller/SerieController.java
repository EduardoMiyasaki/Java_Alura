package com.alura.fraseserie.controller;

import com.alura.fraseserie.dto.SerieDTO;
import com.alura.fraseserie.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService serieService;

    @GetMapping("/frases")
    public SerieDTO gerarFrases(){
        return serieService.gerarFrases();
    }

}
