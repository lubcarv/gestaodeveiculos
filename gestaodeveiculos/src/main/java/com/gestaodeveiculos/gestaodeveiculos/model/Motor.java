package com.gestaodeveiculos.gestaodeveiculos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gestaodeveiculos.gestaodeveiculos.Enum.ETipoCombustivel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Table(name = "motor")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Motor {
@Id
@Column(name = "modelo_id")
private Integer id;

@NotNull
@Column(name = "potencia")
private int potencia;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "modelo_id", nullable = false)
    private Modelo modelo;

@Enumerated(EnumType.STRING)
@Column(name =  "tipo_combustivel")
private ETipoCombustivel tipoCombustivel;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Motor motor = (Motor) obj;
        return Objects.equals(id, motor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
