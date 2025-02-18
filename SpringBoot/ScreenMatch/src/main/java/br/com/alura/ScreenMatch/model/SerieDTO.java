package br.com.alura.ScreenMatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SerieDTO(@JsonAlias({"Title"}) String titulo,
                       @JsonAlias("Year") String ano,
                       @JsonAlias("totalSeasons") int totalTemporadas,
                       @JsonAlias("imdbRating") String avaliacao,
                       @JsonProperty("imdbVotes") String votos) {

}
