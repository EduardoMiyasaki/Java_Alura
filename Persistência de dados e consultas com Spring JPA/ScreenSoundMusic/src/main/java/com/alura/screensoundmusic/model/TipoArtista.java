package com.alura.screensoundmusic.model;

import com.alura.screensoundmusic.validacao.ValidacaoException;

public enum TipoArtista {

    SOLO("Solo"),
    DUPLA("Dupla"),
    BANDA("Banda");

    private String tipo;

    TipoArtista(String tipo) {
        this.tipo = tipo;
    }

    public static TipoArtista fromString(String text){
        for(TipoArtista tipoArtista: TipoArtista.values()){

            if(tipoArtista.tipo.equalsIgnoreCase(text)){
                return tipoArtista;
            }
        }
        throw new ValidacaoException("Nenhum tipo de artista encontrado");
    }
}
