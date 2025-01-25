package exercicio3;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class Main {

    public static void main(String[] args) {

        double precoTotal = 0;
        ArrayList<Produto> listaProdutos = new ArrayList<>();

        Produto produto1 = new Produto("Café", 20.00);
        Produto produto2 = new Produto("Arroz", 16.50);
        Produto produto3 = new Produto("Pão", 0.30);

        listaProdutos.add(produto1);
        listaProdutos.add(produto2);
        listaProdutos.add(produto3);

//        listaProdutos.forEach(produto -> {
//            System.out.println("Produto: " + produto.getNome() + " Preço: " + produto.getPreco());
//            double precoTotal += produto.getPreco();
//        });

        for (int i = 0; i < listaProdutos.size(); i++) {
            precoTotal += listaProdutos.get(i).getPreco();
        }

        double mediaPreco = precoTotal / listaProdutos.size();

        System.out.println("A média de preço é R$: " + precoTotal / listaProdutos.size());
    }
}
