package com.alura.screensoundmusic.repository;

import com.alura.screensoundmusic.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicaRepository extends JpaRepository<Musica, Long> {
}
