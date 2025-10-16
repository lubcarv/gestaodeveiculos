package com.gestaodeveiculos.gestaodeveiculos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Table(name = "cor")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cor {
@Id
@Column(name = "id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
@NotNull
@Column(name = "nome")
private String name;
}
