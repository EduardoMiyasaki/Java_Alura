public class Pessoa {

    public String nome;
    public int idade;
    public String cidade;

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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String mostrarDados(){
        // return String.format("Seu nome é %s, você tem %d anos e mora em %s", this.nome, this.idade, this.cidade);
        return "Seu nome é " + this.nome + " você tem " + this.idade + " anos" + " e mora em " + this.cidade;
    }

    public String mostrarDados(String nome){
        return nome;
    }
}
