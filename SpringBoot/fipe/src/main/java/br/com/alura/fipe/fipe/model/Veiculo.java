package br.com.alura.fipe.fipe.model;

import br.com.alura.fipe.fipe.dto.VeiculoDTO;

public class Veiculo {

    private String codigo;
    private String nome;

    private String tipoVeiculo;
    private double valor;
    private String marca;
    private String modelo;
    private int anoModelo;

    public Veiculo(VeiculoDTO dto) {
        this.tipoVeiculo = dto.tipoVeiculo();
        this.valor = Double.parseDouble(dto.valor());
        this.marca = dto.marca();
        this.modelo = dto.modelo();
        this.anoModelo = dto.anoModelo();
    }

    public Veiculo(){}

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
