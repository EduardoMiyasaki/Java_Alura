package br.com.alura.fipe.fipe.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MarcaDTO(String codigo,
                       @JsonAlias("nome") String marca) {

}
