import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        Scanner tecladoTexto = new Scanner(System.in);

        Cartao cartao1 = new Cartao(1001);

        List<Produto> carroCompras = new ArrayList<>();

        int opcaoEscolhida = 1;

        System.out.print("Digite o limite do cartão:");
        cartao1.setLimite(teclado.nextInt());

        while (true) {

            System.out.println("""
                    Escolha uma opção:
                    1 - Adicionar Produto
                    2 - Sair
                    """);

            opcaoEscolhida = teclado.nextInt();

            switch (opcaoEscolhida) {
                case 1: {

                    System.out.println("Digite o nome do produto");
                    Produto produto1 = new Produto();
                    produto1.setNome(tecladoTexto.nextLine());

                    System.out.println("Digite o valor da compra");
                    double valorProduto = teclado.nextDouble();
                    if (valorProduto <= cartao1.getLimite()) {
                        cartao1.setLimite(cartao1.getLimite() - valorProduto);
                        carroCompras.add(produto1);
                        produto1.setPreco(valorProduto);
                        break;
                    } else {
                        System.out.println("Saldo Insuficiente");
                        System.out.println("Ordem alfabética");
                        OrdenadorNome ordenador = new OrdenadorNome();
                        Collections.sort(carroCompras , ordenador);
                        System.out.println("Compras Realizadas: " + carroCompras);
                        System.out.println("Saldo do cartão R$: " + cartao1.getLimite());
                        tecladoTexto.close();
                        teclado.close();
                        System.exit(0);
                    }

                }
                case 2: {
                    Collections.sort(carroCompras);
                    System.out.println("Compras Realizadas: " + carroCompras);
                    System.out.println("Saldo do cartão R$: " + cartao1.getLimite());
                    tecladoTexto.close();
                    teclado.close();
                    System.exit(0);
                }

                default: {
                    System.out.println("Opção inválida");
                }
            }

        }
    }
}
