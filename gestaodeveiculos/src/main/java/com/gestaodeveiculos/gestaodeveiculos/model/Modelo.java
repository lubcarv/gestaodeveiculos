package com.gestaodeveiculos.gestaodeveiculos.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gestaodeveiculos.gestaodeveiculos.Enum.ECategoria;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "modelo")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Modelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotNull
    @Column(name = "descricao")
    private String descricao;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @OneToOne(mappedBy = "modelo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)

    private Motor motor;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria")
    private ECategoria categoria;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Modelo modelo = (Modelo) obj;
        return Objects.equals(id, modelo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
