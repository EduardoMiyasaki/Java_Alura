package br.com.alura.ScreenMatch;

import br.com.alura.ScreenMatch.model.SerieDTO;
import br.com.alura.ScreenMatch.service.ConsumoAPI;
import br.com.alura.ScreenMatch.service.ConverterDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatchApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		var consumoAPI = new ConsumoAPI();
		var json = consumoAPI.obterDados("https://www.omdbapi.com/?t=The+Mentalist&apikey=20400ae6");
		System.out.println(json);
		ConverterDados conversor = new ConverterDados();
		var serieDto = conversor.converterDados(json, SerieDTO.class);
		System.out.println(serieDto);
	}
}
