public class ProdutoPerecivel extends Produto{

    private String dataValidade;

    public ProdutoPerecivel(String nome, double preco, String dataValidade) {
        super(nome, preco);
        this.dataValidade = dataValidade;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    @Override
    public String toString() {
        return String.format("""
                Produto: %s e o Preço: %.2f e a Data de validade é %s""",this.getNome(), this.getPreco() , this.getDataValidade());
    }
}
