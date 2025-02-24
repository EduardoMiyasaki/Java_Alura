package br.com.alura.fipe.fipe.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VeiculoDTO(@JsonAlias("TipoVeiculo") String tipoVeiculo,
                         @JsonAlias("Valor")String valor,
                         @JsonAlias("Marca")String marca,
                         @JsonAlias("Modelo")String modelo,
                         @JsonAlias("AnoModelo") int anoModelo) {
}
