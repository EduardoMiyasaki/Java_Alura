package exercicio3;

public class Livro {

    private String titulo;
    private String autor;
    private Editora editora;

    public Livro(LivroRecord livroRecord) {
        this.setTitulo(livroRecord.titulo());
        this.setAutor(livroRecord.autor());
        this.setEditora(livroRecord.editora());
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return String.format("""
                Titulo: %s
                Autor: %s
                Editora: %s
                """ , this.getTitulo() , this.getAutor() , this.getEditora().getNome());
    }
}
