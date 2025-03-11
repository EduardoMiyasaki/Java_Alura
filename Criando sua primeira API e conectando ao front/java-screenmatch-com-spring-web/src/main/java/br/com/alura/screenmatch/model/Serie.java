package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.service.traducao.ConsultaMyMemory;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Entity
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private Integer totalTemporadas;

    private Double avaliacao;

    @Enumerated(EnumType.STRING)
    private Categoria genero;

    private String atores;

    private String imagemPoster;

    private String sinopse;

    @OneToMany(mappedBy = "serie",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episodio> episodios = new ArrayList<>();

    public Serie() {
    }

    public Serie(DadosSerie dto) {
        this.titulo = dto.titulo();
        this.totalTemporadas = dto.totalTemporadas();
        this.avaliacao = OptionalDouble.of(Double.parseDouble(dto.avaliacao())).orElse(0);
        this.genero = Categoria.fromString(dto.genero().split(",")[0].trim());
        this.atores = dto.atores();
        this.imagemPoster = dto.imagemPoster();
        this.sinopse = ConsultaMyMemory.obterTraducao(dto.sinopse());
//        this.sinopse = ConsultaChatGPT.obterTraducao(dto.sinopse()).trim();
    }

    public Long getId() {return id;}

    public String getTitulo() {
        return titulo;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public Categoria getGenero() {
        return genero;
    }

    public String getAtores() {
        return atores;
    }

    public String getImagemPoster() {
        return imagemPoster;
    }

    public String getSinopse() {
        return sinopse;
    }

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        episodios.forEach(e -> e.setSerie(this));
        this.episodios = episodios;
    }

    @Override
    public String toString() {
        return "titulo='" + titulo + '\'' +
                ", genero=" + genero +
                ", totalTemporadas=" + totalTemporadas +
                ", avaliacao=" + avaliacao +
                ", atores='" + atores + '\'' +
                ", imagemPoster='" + imagemPoster + '\'' +
                ", sinopse='" + sinopse+ '\'';
    }
}
