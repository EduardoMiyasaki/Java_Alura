package com.alura.fraseserie.repository;

import com.alura.fraseserie.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SerieRepository extends JpaRepository<Serie, Long> {

    @Query("SELECT s FROM Serie s ORDER BY function('RANDOM') LIMIT 1 ")
    Serie buscaFraseAleatoria();
}
