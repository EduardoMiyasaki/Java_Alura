public class Produto implements Comparable<Produto> {

    private String nome;
    private double preco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return "Produto: " + this.getNome() + " Preço: R$" + this.getPreco();
    }

    @Override
    public int compareTo(Produto outroProduto) {

        return Double.valueOf(this.getPreco()).compareTo(Double.valueOf(outroProduto.getPreco()));
//        if (this.getPreco() > outroProduto.getPreco()) {
//            return 1;
//        }
//        else if (this.getPreco() < outroProduto.getPreco()) {
//            return -1;
//        }
//        else {
//            return 0;
//        }
    }
}
