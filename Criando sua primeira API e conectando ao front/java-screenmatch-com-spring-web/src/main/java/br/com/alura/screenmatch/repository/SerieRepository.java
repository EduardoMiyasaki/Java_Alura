package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {

    Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);

    List<Serie> findByAtoresContainingIgnoreCase(String nomeAtor);

    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, double avaliacao);

    List<Serie> findTop5ByOrderByAvaliacaoDesc();

    List<Serie> findByGenero(Categoria categoria);

    List<Serie> findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(int totalTemporadas, double avaliacao);

    @Query("SELECT s FROM Serie s WHERE s.totalTemporadas <= :totalTemporadas and s.avaliacao >= :avaliacao")
    List<Serie> seriesPorTemporadaEAvaliacao(int totalTemporadas, double avaliacao);

    @Query("SELECT e FROM Serie s INNER JOIN s.episodios e WHERE e.titulo ILIKE %:trecho%")
    List<Episodio> listarEpisodiosPorTrecho(String trecho);

    @Query("SELECT e FROM Serie s INNER JOIN s.episodios e" +
            " WHERE s.titulo ILIKE %:trechoSerie% AND e.titulo ILIKE %:trechoEpisodio%")
    List<Episodio> listarEpisodiosPorTrechoDaSerieEspecifica(String trechoSerie, String trechoEpisodio);

    @Query("SELECT e FROM Serie s INNER JOIN s.episodios e WHERE s = :serie ORDER BY e.avaliacao DESC limit 5")
    List<Episodio> listandoTop5EpisodiosDeUmaSerie(Optional<Serie> serie);

    //   @Query("SELECT e FROM Serie s INNER JOIN s.episodios e WHERE s.titulo ILIKE %:trechoSerie% ORDER BY e.avaliacao DESC limit 5")
//   List<Episodio> listandoTop5EpisodiosDeUmaSerie(String trechoSerie);
    @Query("SELECT e FROM Serie s INNER JOIN s.episodios e WHERE s = :serie AND YEAR(e.dataLancamento) >= :anoLancamento")
    List<Episodio> episodiosPorSerieAposAno(Optional<Serie> serie, int anoLancamento);

    // errado
    List<Serie> findTop5ByOrderByEpisodiosDataLancamentoDesc();

    @Query("SELECT s FROM Serie s" +
            " INNER JOIN s.episodios e" +
            " GROUP BY s" +
            "    ORDER BY MAX(e.dataLancamento) DESC LIMIT 5")
    List<Serie> findTop5Recentes();

    @Query("SELECT e FROM Serie s" +
            " INNER JOIN s.episodios e" +
            " WHERE s.id = :id AND e.temporada = :numeroTemporada")
    List<Episodio> findByIdAndTemporada(Long id, int numeroTemporada);

    @Query("SELECT e FROM Serie s" +
            " INNER JOIN s.episodios e" +
            " WHERE s.id = :id" +
            " ORDER BY e.avaliacao DESC LIMIT 5")
    List<Episodio> findTop5Episodios(Long id);

}
