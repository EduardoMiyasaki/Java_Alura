package br.com.alura.ScreenMatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodioDTO(@JsonAlias("Title") String titulo,
                          @JsonAlias("Episode") int numeroEpisodio,
                          @JsonAlias("imdbRating") String avaliacao,
                          @JsonProperty("Released") String dataLancamento) {
}
