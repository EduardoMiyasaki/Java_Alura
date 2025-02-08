package br.com.alura.adopet.api.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "adocoes")
public class Adocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;

    //    @JoinColumn(name = "tutor_id")
    // ja é da conveção do JPA passar um relacionamento com o nome
    // da tabela_id
    @ManyToOne(fetch = FetchType.LAZY)
    private Tutor tutor;

    //    @JoinColumn(name = "pet_id")
//    @JsonManagedReference("adocao_pets")
    @OneToOne(fetch = FetchType.LAZY)
    private Pet pet;

    private String motivo;

    @Enumerated(EnumType.STRING)
    private StatusAdocao status;

    @Column(name = "justificativa_status")
    private String justificativaStatus;

    //    @PersistenceConstructor
    public Adocao(Pet pet, Tutor tutor, String motivo) {
        this.data = LocalDateTime.now();
        this.pet = pet;
        this.tutor = tutor;
        this.motivo = motivo;
        this.status = StatusAdocao.AGUARDANDO_AVALIACAO;
    }

    public Adocao() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adocao adocao = (Adocao) o;
        return Objects.equals(id, adocao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public Pet getPet() {
        return pet;
    }

    public String getMotivo() {
        return motivo;
    }

    public StatusAdocao getStatus() {
        return status;
    }

    public String getJustificativaStatus() {
        return justificativaStatus;
    }

    public void marcarComoAprovado() {
        this.status = StatusAdocao.APROVADO;
    }


    public void marcarComoReprovado(String justificativa) {
        this.status = StatusAdocao.REPROVADO;
        this.justificativaStatus = justificativa;
    }
}
