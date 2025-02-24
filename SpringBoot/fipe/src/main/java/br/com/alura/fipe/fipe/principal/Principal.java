package br.com.alura.fipe.fipe.principal;

import br.com.alura.fipe.fipe.dto.*;
import br.com.alura.fipe.fipe.model.Marca;
import br.com.alura.fipe.fipe.model.Modelo;
import br.com.alura.fipe.fipe.model.Veiculo;
import br.com.alura.fipe.fipe.service.ConsumoAPI;
import br.com.alura.fipe.fipe.service.ConverteDados;

import java.util.*;

public class Principal {

    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";
    private final Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    public void exibeMenu() {

        System.out.println("Informe o tipo de veículo que você procura(ex: carros,motos ou caminhoes): ");
        var tipoVeiculo = leitura.nextLine();

        String response = consumo.obterDados(ENDERECO + tipoVeiculo + "/marcas");

        MarcaDTO[] marcas = conversor.converter(response, MarcaDTO[].class);
        var listaMarcas = Arrays.stream(marcas).map(Marca::new).toList();

        System.out.println(listaMarcas);

        System.out.print("Informe o código da marca que você deseja ver os veículos: ");
        var codigoMarca = leitura.nextLine();

        response = consumo.obterDados(ENDERECO + tipoVeiculo + "/marcas/" + codigoMarca + "/modelos");

        ModeloResponse modelos = conversor.converter(response, ModeloResponse.class);
        List<Modelo> listaModelos = modelos.modelos().stream()
                .map(Modelo::new)
                .toList();

        System.out.println(listaModelos);

        System.out.println("Informe o código do modelo que você deseja ver");
        String codigoModelo = leitura.nextLine();

        response = consumo.obterDados(ENDERECO + tipoVeiculo + "/marcas/" + codigoMarca + "/modelos/" + codigoModelo + "/anos");
        System.out.println(response);
        Veiculo[] veiculos = conversor.converter(response, Veiculo[].class);
        System.out.println(Arrays.toString(veiculos));

        for (Veiculo veiculo : veiculos) {
            response = consumo.obterDados(ENDERECO + tipoVeiculo + "/marcas/" + codigoMarca + "/modelos/" + codigoModelo + "/anos/" + veiculo.getCodigo());
            System.out.println("-------------------------");
            VeiculoDTO dto = conversor.converter(response, VeiculoDTO.class);
            System.out.println(dto);
        }
    }
}
