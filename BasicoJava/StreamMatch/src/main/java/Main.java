import java.lang.reflect.Type;
import java.net.Proxy;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bem vindo ao Screen Match");

        System.out.println("Filme: Top Gun: Maverick");

        int anoDeLancamento = 2022;

        System.out.println("Ano de lançamento: " + anoDeLancamento);
        boolean incluidoNoPlano = true;
        float notaDoFilme = 8.1f;
        String nomeDoFilme = "Filme: Top Gun: Maverick";
        int preco = 10;

        double mediaFilme = (9.8 + 6.3 + 8.0) / 3;

        System.out.println(mediaFilme);

        String senha = "123456";

        // Equals é utilizado para comparar strings

        if (senha.equals("123456")) {
            System.out.println("Acesso autorizado");
        } else {
            System.out.println("Acesso Negado");
        }

        senha = "Eduardo";

        // utilizando o equalsIgnoreCase,
        // ele faz a comparação de Strings desconsiderando as letras maiúsculas e minúsculas

        if (senha.equalsIgnoreCase("eduardo")) {
            System.out.println("Acesso autorizado");
        } else {
            System.out.println("Acesso Negado");
        }

        System.out.println(String.format("O nome do filme é %s, minha nota para o filme foi %.2f e paguei %d" ,nomeDoFilme, notaDoFilme ,preco
        ));

        int classifacao;

        classifacao = (int) (mediaFilme / 2);

        int x = 10;
        double y = x; // casting implícito

        double z = 10.5;
        int p = (int) x; // casting explícito
        System.out.println(p);
    }
}

