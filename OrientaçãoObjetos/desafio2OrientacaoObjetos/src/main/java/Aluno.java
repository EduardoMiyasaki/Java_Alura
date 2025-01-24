public class Aluno {

    private String nome;
    private double notas;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getNota() {
        return notas;
    }

    public void setNota(double nota) {
        this.notas += nota;
    }

    public double calcularMedia() {
        return this.notas / 2;
    }


}
