public class Pessoa {

    private String nome;
    private int idade;

    // Isso é um metódo construtor
    // sempre que Instaciar um objeto desse tipo
    // tem que passar as informações do paramêtro

    //    public Pessoa(String nome, int idade) {
    //        this.nome = nome;
    //        this.idade = idade;
    //    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Seu nome é " + this.nome + " e você tem " + this.idade + " anos";
    }
}
