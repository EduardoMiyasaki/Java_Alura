package br.com.alura.screenmatch.model;

public enum Categoria {

    ACAO("Action", "Ação"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comédia"),
    DRAMA("Drama","Drama"),
    CRIME("Crime","Crime");

    private String categoriaOmdb;
    private String categoriaPT;

    Categoria(String categoriaOmdb, String usuario) {
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaPT = usuario;
    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text) || categoria.categoriaPT.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada");
    }
}
