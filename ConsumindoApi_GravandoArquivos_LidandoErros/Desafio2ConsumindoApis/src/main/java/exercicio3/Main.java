package exercicio3;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

    public static void main(String[] args) {

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).
                create();

        String jsonLivro = """
                 {
                     "Titulo": "Harry Potter",
                     "Autor": "J.K. Rowling",
                     "Editora": {
                        Nome:"Bloomsbury Publishing"
                 }
                 }
                """;



        System.out.println(jsonLivro);

        // Passando os dados do Json para o record
        LivroRecord livroRecord = gson.fromJson(jsonLivro , LivroRecord.class);
        
        Livro livro1 = new Livro(livroRecord);
        System.out.println(livro1);
    }
}
