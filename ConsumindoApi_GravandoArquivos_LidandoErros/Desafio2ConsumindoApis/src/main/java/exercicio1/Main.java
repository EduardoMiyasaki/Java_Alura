package exercicio1;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {

        Gson gson = new GsonBuilder().
                setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setLenient()
                .create();

        String jsonPessoa =  """
          {
            "Nome" : "Eduardo Miyasaki",
            "Idade" : 18,
            "Cidade" : "Itaquaquecetuba",
            Altura: 1.8
          }
        """;
        System.out.println("JSON de exercicio1.Pessoa");
        System.out.println(jsonPessoa);

        // Passando os dados do JSON para o Record
        PessoaRecord pessoaRecord =  gson.fromJson(jsonPessoa , PessoaRecord.class);

        // Passando os dados do record para a classe exercicio1.Pessoa atráves do construtor
        Pessoa pessoa1 = new Pessoa(pessoaRecord);
        System.out.println(pessoa1);
    }
}
