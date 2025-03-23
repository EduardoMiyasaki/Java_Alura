package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.dto.EpisodioDTO;
import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.validacao.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    public List<SerieDTO> obterSeries() {
        return converteDados(serieRepository.findAll());
    }

    public List<SerieDTO> obterTop5Series() {
        return converteDados(serieRepository.findTop5ByOrderByAvaliacaoDesc());
    }

    public List<SerieDTO> obterLancamentos() {
        return converteDados(serieRepository.findTop5Recentes());
    }

    public SerieDTO obterSeriePeloID(Long id) {

        Optional<Serie> serie = serieRepository.findById(id);
        if (serie.isPresent()) {
            Serie s = serie.get();
            return new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(), s.getGenero(), s.getAtores(), s.getImagemPoster(), s.getSinopse());
        } else {
            throw new ValidacaoException("Série não encontrada");
        }
    }

    public List<EpisodioDTO> obterTemporadas(Long id) {
        Optional<Serie> serie = serieRepository.findById(id);

        if (serie.isPresent()) {
            Serie s = serie.get();

            return s.getEpisodios().stream()
                    .map(e -> new EpisodioDTO(e.getTemporada(), e.getNumero(), e.getTitulo()))
                    .toList();
        } else {
            throw new ValidacaoException("Série não encontrada");
        }
    }

    public List<EpisodioDTO> obterEpisodiosPorTemporadas(Long id, int numeroTemporada) {
        List<Episodio> listaEpisodio = serieRepository.findByIdAndTemporada(id, numeroTemporada);

        return listaEpisodio.stream()
                .map(e -> new EpisodioDTO(e.getTemporada(), e.getNumero(), e.getTitulo()))
                .toList();
    }

    public List<SerieDTO> obterSeriesPorCategoria(String genero) {
        try {
            Categoria categoria = Categoria.fromString(genero);
            return converteDados(serieRepository.findByGenero(categoria));
        } catch (IllegalArgumentException e) {
            throw new ValidacaoException(e.getMessage());
        }
    }

    public List<EpisodioDTO> listarTop5Episodios(Long id) {
        List<Episodio> listaEpisodio = serieRepository.findTop5Episodios(id);

        return listaEpisodio.stream()
                .map(e -> new EpisodioDTO(e.getTemporada(), e.getNumero(), e.getTitulo()))
                .toList();
    }

    private List<SerieDTO> converteDados(List<Serie> series) {
        return series.stream()
                .map(s -> new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(), s.getGenero(), s.getAtores(), s.getImagemPoster(), s.getSinopse()))
                .toList();
    }
}
