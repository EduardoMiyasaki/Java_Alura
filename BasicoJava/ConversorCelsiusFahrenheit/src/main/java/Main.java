public class Main {

    public static void main(String[] args) {

        int temperaturaEmCelsius = 31;

        double temperaturaEmFahrenheit = temperaturaEmCelsius * 1.8 + 32;

        System.out.println(temperaturaEmFahrenheit);

        int temperatura = (int) temperaturaEmFahrenheit;

        double nota1 = 7.5;
        double nota2 = 9.5;

        double media = (nota1 + nota2) / 2;

        System.out.println(String.format("A média de nota é de %.2f", media));

        double numeroDouble = 7.4;

        int numeroInteiro = (int) (numeroDouble);

        System.out.println(numeroInteiro);

        char a = 'a';
        String b = "marelo";

        System.out.println(a + b);

        double precoProduto = 10.5;
        int quantidade = 5;

        double preco = precoProduto * quantidade;
        System.out.println("O preço por esses produtos é de " + preco);

        double valorEmDolares = 4.94;
        double reais = 100;

        double conversao = reais / valorEmDolares;
        System.out.println(conversao);

        double precoOriginal = 149.99;

        double percentualDesconto = 10;

        double valorDesconto = precoOriginal / percentualDesconto;

        System.out.println(valorDesconto);

        double novoPreco = precoOriginal - valorDesconto;

        System.out.println("Preço original: R$" + precoOriginal);
        System.out.println("Desconto: R$" + valorDesconto);
        System.out.println("Novo preço com desconto: R$" + novoPreco);


    }
}
