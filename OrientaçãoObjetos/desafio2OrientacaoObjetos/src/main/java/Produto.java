public class Produto {

    private String nome;
    private double preco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void aplicarDesconto(int percentualDesconto) {
        double desconto = this.preco / percentualDesconto;
        this.preco -= desconto;
    }

    public static void main(String[] args) {
        Produto produto = new Produto();

        produto.setNome("Celular");
        produto.setPreco(2000);


        System.out.println("Nome do Produto: " + produto.getNome());
        System.out.println("Preço: " + produto.getPreco());

        produto.aplicarDesconto(10);
        System.out.println("Novo Preço após Desconto: " + produto.getPreco());
    }
}
