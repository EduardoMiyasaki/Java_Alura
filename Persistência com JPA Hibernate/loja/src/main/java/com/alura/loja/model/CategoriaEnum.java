package com.alura.loja.model;

public enum Categoria {

    CELULAR("Celular"),
    INFORMATICA("Informatica"),
    LIVRO("Livro");

    private String usuarioInforma;

    Categoria(String usuarioInforma) {
        this.usuarioInforma = usuarioInforma;
    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.usuarioInforma.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada");
    }
}
