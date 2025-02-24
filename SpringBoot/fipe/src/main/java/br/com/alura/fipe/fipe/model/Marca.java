package br.com.alura.fipe.fipe.model;

import br.com.alura.fipe.fipe.dto.MarcaDTO;

public class Marca extends Veiculo {

    public Marca() {
    }

    public Marca(MarcaDTO dto) {
        this.setCodigo(dto.codigo());
        this.setNome(dto.marca());
    }

    @Override
    public String toString() {
        return "Código: " + getCodigo() + " Marca: " + getNome();
    }
}
