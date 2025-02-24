package br.com.alura.fipe.fipe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ModeloDTO(String codigo, String nome) {
}
