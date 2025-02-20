package br.com.alura.ScreenMatch.principal;

import br.com.alura.ScreenMatch.dto.SerieDTO;
import br.com.alura.ScreenMatch.service.ConsumoAPI;
import br.com.alura.ScreenMatch.service.ConverterDados;

import java.util.Scanner;

public class Main {

    private Scanner teclado = new Scanner(System.in);
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String APIKEY = "&apikey=20400ae6";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverterDados conversor = new ConverterDados();

    public void exibirMenu() {

        System.out.print("Digite o nome da série para busca");
        String nomeSerie = teclado.nextLine();

        System.out.println("-------------- Série ------------------------");
        var json = consumoAPI.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + APIKEY);
        var serieDto = conversor.converterDados(json, SerieDTO.class);
        System.out.println(serieDto);
    }


}
