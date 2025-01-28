import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner teclado = new Scanner(System.in);
        Scanner tecladoTexto = new Scanner(System.in);

        Gson gson = new Gson().newBuilder().
                setLenient().
                setPrettyPrinting().
                create();

        GeradorArquivo geradorArquivo = new GeradorArquivo();
        FileWriter writer = geradorArquivo.gerarArquivo();

        List<Endereco> listaEnderecos = new ArrayList<>();

        int opcao = 1;
        try {
            while (opcao == 1) {
                System.out.println("""
                        Digite sua Opção:
                        1 - digite seu CEP
                        0 - Sair""");

                opcao = teclado.nextInt();

                if (opcao != 1) {
                    break;
                }

                System.out.print("Informe seu CEP: ");

                ClientHttp clientHttp = new ClientHttp();
                HttpResponse<String> response = clientHttp.consultarCEP(tecladoTexto.nextLine());
                String json = response.body();

                EnderecoDTO enderecoDTO = gson.fromJson(json, EnderecoDTO.class);

                Endereco endereco1 = new Endereco(enderecoDTO);
                listaEnderecos.add(endereco1);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        writer.write(gson.toJson(listaEnderecos));
        writer.close();
    }
}
