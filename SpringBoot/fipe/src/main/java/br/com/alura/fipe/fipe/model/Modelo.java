package br.com.alura.fipe.fipe.model;

import br.com.alura.fipe.fipe.dto.ModeloDTO;

public class Modelo{

    private String codigo;
    private String nome;

    public Modelo(){}

    public Modelo(ModeloDTO dto){
       this.setCodigo(dto.codigo());
       this.setNome(dto.nome());
    }

    @Override
    public String toString() {
        return "Código: " + getCodigo() + " Modelo: " + getNome();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
