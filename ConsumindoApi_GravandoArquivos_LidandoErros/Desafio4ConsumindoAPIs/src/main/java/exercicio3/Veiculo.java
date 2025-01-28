package exercicio3;

public class Veiculo {

    private String nome;
    private int rodas;


    public Veiculo(String nome, int rodas) {
        this.nome = nome;
        this.rodas = rodas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getRodas() {
        return rodas;
    }

    public void setRodas(int rodas) {
        this.rodas = rodas;
    }
}
