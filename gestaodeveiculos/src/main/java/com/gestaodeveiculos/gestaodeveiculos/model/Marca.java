package com.gestaodeveiculos.gestaodeveiculos.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Table(name = "marca")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Marca {

@Column(name = "id")
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

@Setter
@Column(name = "nome")
private String nome;
}
