import java.util.Comparator;

public class OrdenadorNome implements Comparator<Produto> {

    @Override
    public int compare(Produto produto1, Produto produto2) {
        return produto1.getNome().compareTo(produto2.getNome());
    }
}
