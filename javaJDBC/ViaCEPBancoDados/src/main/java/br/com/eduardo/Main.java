package br.com.eduardo;

import br.com.eduardo.client.ClienteHttp;
import br.com.eduardo.domain.Endereco;
import br.com.eduardo.service.EnderecoService;
import com.google.gson.Gson;

import java.net.http.HttpResponse;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private static Scanner teclado = new Scanner(System.in).useDelimiter("\n");
    private static Scanner tecladoTexto = new Scanner(System.in).useDelimiter("\n");

    private static Gson gson = new Gson().newBuilder().
            setPrettyPrinting().
            create();

    private static EnderecoService enderecoService = new EnderecoService();

    public static void main(String[] args) {

        var opcaoMenu = 1;
        while (opcaoMenu != 5) {
            opcaoMenu = gerarMenu();


            switch (opcaoMenu) {
                case 1: {
                    adicionarEndereco();
                    break;
                }
                case 2: {
                    listarEnderecos();
                    break;
                }
                case 3: {
                    listarEndereco();
                }
                case 4: {
                    excluirEndereco();
                }
            }

        }

    }

    public static int gerarMenu() {
        System.out.println(String.format("""
                Menu ViaCEP:
                1 - Adicionar Endereço
                2 - Listas Endereços
                3 - Listar Endereço
                4 - Excluir Endereço
                4 - Sair
                """));
        return teclado.nextInt();
    }

    public static void adicionarEndereco() {

        ClienteHttp clienteHttp = new ClienteHttp();
        System.out.print("Informe seu CEP:");
        HttpResponse<String> response = clienteHttp.consultarCEP(tecladoTexto.nextLine());

        String json = response.body();

        EnderecoDTO enderecoDTO = gson.fromJson(json, EnderecoDTO.class);
        enderecoService.salvarEndereco(enderecoDTO);
        System.out.println("Endereço salvo");

    }

    public static void listarEnderecos() {
        Set<Endereco> listaEnderecos = enderecoService.listarEnderecos();
        listaEnderecos.forEach(System.out::println);
    }

    public static void listarEndereco() {
        System.out.print("Informe o CEP:");
        Endereco endereco = enderecoService.listarEndereco(tecladoTexto.nextLine());
        System.out.println(endereco);
    }

    public static void excluirEndereco() {
        System.out.print("Informe o CEP:");
        enderecoService.excluirEndereco(tecladoTexto.nextLine());

        System.out.println("Endereço Excluido");
    }
}
