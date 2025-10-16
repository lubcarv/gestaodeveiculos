package com.gestaodeveiculos.gestaodeveiculos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "veiculo")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
private Integer id;
@NotNull
@Column(name = "placa")
private String placa;
@NotNull
@Column(name = "observacoes")
private String observacoes;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "cor_id")
private Cor cor;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "modelo_id")
private Modelo modelo;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "proprietario_id")
private Cliente proprietario;
@OneToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "motor_id")
private Motor motor;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Veiculo veiculo = (Veiculo) obj;
        return Objects.equals(id, veiculo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
