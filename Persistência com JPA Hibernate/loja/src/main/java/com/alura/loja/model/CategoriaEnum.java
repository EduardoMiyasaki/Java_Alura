package com.alura.loja.model;

public enum CategoriaEnum {

    CELULAR("Celular"),
    INFORMATICA("Informatica"),
    LIVRO("Livro");

    private String usuarioInforma;

    CategoriaEnum(String usuarioInforma) {
        this.usuarioInforma = usuarioInforma;
    }

    public static CategoriaEnum fromString(String text) {
        for (CategoriaEnum categoria : CategoriaEnum.values()) {
            if (categoria.usuarioInforma.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada");
    }
}
