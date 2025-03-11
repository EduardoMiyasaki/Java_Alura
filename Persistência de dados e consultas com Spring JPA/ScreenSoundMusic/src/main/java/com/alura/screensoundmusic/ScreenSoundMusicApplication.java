package com.alura.screensoundmusic;

import com.alura.screensoundmusic.principal.Principal;
import com.alura.screensoundmusic.repository.ArtistaRepository;
import com.alura.screensoundmusic.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenSoundMusicApplication implements CommandLineRunner {

    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    public static void main(String[] args) {
        SpringApplication.run(ScreenSoundMusicApplication.class, args);
    }

    @Override
    public void run(String... args){
        Principal principal = new Principal(artistaRepository, musicaRepository);
        principal.exibeMenu();
    }
}
