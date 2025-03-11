package com.alura.screensoundmusic.repository;

import com.alura.screensoundmusic.model.Artista;
import com.alura.screensoundmusic.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

   Optional<Artista> findByNomeContainingIgnoreCase(String nome);

   @Query("SELECT a.musicas FROM Artista a WHERE a.nome ILIKE %:nomeArtista%")
   List<Musica> listarMusicaProArtista(@Param("nomeArtista") String nome);

   @Query("SELECT m FROM Artista a JOIN a.musicas m WHERE a.nome ILIKE %:nomeArtista%")
   List<Musica> listarMusicaProArtista1(@Param("nomeArtista") String nome);
}
