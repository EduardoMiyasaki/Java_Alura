package br.com.alura.fipe.fipe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record ModeloResponse(List<ModeloDTO> modelos) {
}
