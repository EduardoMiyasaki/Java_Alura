package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Episodio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EpisodioRepository extends JpaRepository<Episodio, Long> {

    @Query("SELECT e FROM Episodio e WHERE e.titulo LIKE %:trecho%")
    List<Episodio> listarEpisodiosPorTrecho(String trecho);
}
