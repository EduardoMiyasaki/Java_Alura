package com.alura.fraseserie.service;

import com.alura.fraseserie.dto.SerieDTO;
import com.alura.fraseserie.model.Serie;
import com.alura.fraseserie.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    public SerieDTO gerarFrases() {
        Serie serie = serieRepository.buscaFraseAleatoria();
        return new SerieDTO(serie.getTitulo(), serie.getFrase(), serie.getPersonagem(), serie.getPoster());
    }
}
