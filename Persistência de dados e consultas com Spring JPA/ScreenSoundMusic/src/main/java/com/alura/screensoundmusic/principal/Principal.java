package com.alura.screensoundmusic.principal;

import com.alura.screensoundmusic.dto.CadastroArtistaDTO;
import com.alura.screensoundmusic.model.Artista;
import com.alura.screensoundmusic.model.Musica;
import com.alura.screensoundmusic.model.TipoArtista;
import com.alura.screensoundmusic.repository.ArtistaRepository;
import com.alura.screensoundmusic.repository.MusicaRepository;
import com.alura.screensoundmusic.validacao.ValidacaoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private MusicaRepository musicaRepository;
    private ArtistaRepository artistaRepository;

    public Principal(ArtistaRepository artistaRepository, MusicaRepository musicaRepository) {
        this.musicaRepository = musicaRepository;
        this.artistaRepository = artistaRepository;
    }

    Scanner teclado = new Scanner(System.in);
    Scanner tecladoTexto = new Scanner(System.in);

    public void exibeMenu() {
        int opcao = 0;

        while (opcao != 5) {
            exibirMenu();
            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusica();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    listarMusicasPorArtista();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void exibirMenu() {
        System.out.println("""
                1 - Cadastrar Artistas
                2 - Cadastrar Músicas
                3 - Listar Músicas
                4 - Buscar Músicas Por Artista
                5 - Sair
                """);
    }

    private void exibirMenuCadastroMusica() {
        System.out.println("""
                1 - Cadastrar Música
                2 - Sair
                """);
    }

    private void cadastrarArtista() {

        var cadastrarNovo = "S";

        while (cadastrarNovo.equalsIgnoreCase("s")) {
            System.out.println("Insira o nome do artista: ");
            var nomeArtista = teclado.nextLine();

            System.out.println("Insira o tipo do artista: (solo, dupla, banda)");
            var tipoArtista = tecladoTexto.nextLine();

            TipoArtista tipo = TipoArtista.valueOf(tipoArtista.toUpperCase());
            //     TipoArtista tipo = TipoArtista.fromString(tipoArtista);
            CadastroArtistaDTO dto = new CadastroArtistaDTO(nomeArtista, tipo);
            artistaRepository.save(new Artista(dto));

            System.out.println("Cadastrar novo artista: (S/N)");
            cadastrarNovo = teclado.nextLine();
        }
    }

    private void cadastrarMusica() {

        System.out.println("Insira o nome do artista");
        Optional<Artista> artistaBuscado = artistaRepository.findByNomeContainingIgnoreCase(teclado.nextLine());

        if (artistaBuscado.isPresent()) {
            List<Musica> listMusica = new ArrayList<>();
            System.out.println("Insira o título da música");
            String nomeMusica = teclado.nextLine();

            Musica musica = new Musica(nomeMusica);
            musica.setArtista(artistaBuscado.get());
            listMusica.add(musica);
            artistaBuscado.get().setMusicas(listMusica);
            artistaRepository.save(artistaBuscado.get());
        } else {
            throw new ValidacaoException("Artista não encontrado");
        }
    }

    private void listarMusicas() {

        List<Artista> artistas = artistaRepository.findAll();
        artistas.forEach(a -> a.getMusicas().forEach(System.out::println));
    }


    private void listarMusicasPorArtista() {

        System.out.println("insira o nome do artista: ");
        List<Musica> musicaDoArtista = artistaRepository.listarMusicaProArtista(teclado.nextLine());
        musicaDoArtista.forEach(System.out::println);
    }


}
