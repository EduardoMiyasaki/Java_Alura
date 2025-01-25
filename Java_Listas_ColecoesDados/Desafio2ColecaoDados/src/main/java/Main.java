import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Produto> listaProdutos = new ArrayList<>();

        Produto produto1 = new Produto("Leite condensado" , 6.50);
        Produto produto2 = new Produto("Café", 23.00);
        Produto produto3 = new Produto("Arroz" , 16.50);

        listaProdutos.add(produto1);
        listaProdutos.add(produto2);
        listaProdutos.add(produto3);

        // Imprima o tamanho da lista
        System.out.println("O tamanho da lista é " + listaProdutos.size());

        //  recupere um produto pelo índice
        System.out.println("3° Produto: " + listaProdutos.get(2).getNome());

        //  imprima a lista de produtos
        System.out.println(listaProdutos);

        ProdutoPerecivel perecivel1 = new ProdutoPerecivel("Coco ralado" , 7.50 , "31/05/2025");
        ProdutoPerecivel perecivel2 = new ProdutoPerecivel("Queijo ralado" , 9.50 , "31/05/2026");

        System.out.println(perecivel1.toString());
    }
}
