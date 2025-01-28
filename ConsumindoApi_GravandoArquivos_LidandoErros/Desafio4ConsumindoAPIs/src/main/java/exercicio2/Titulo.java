package exercicio2;

public class Titulo {

    private String nome;
    private String diretor;
    private int duracaoEmMinutos;

    public Titulo(String nome, String diretor, int duracaoEmMinutos) {
        this.nome = nome;
        this.diretor = diretor;
        this.duracaoEmMinutos = duracaoEmMinutos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public void setDuracaoEmMinutos(int duracaoEmMinutos) {
        this.duracaoEmMinutos = duracaoEmMinutos;
    }
}
