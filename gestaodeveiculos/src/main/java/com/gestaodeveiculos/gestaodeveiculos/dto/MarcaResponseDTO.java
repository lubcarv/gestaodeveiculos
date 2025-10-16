package com.gestaodeveiculos.gestaodeveiculos.dto;

import com.gestaodeveiculos.gestaodeveiculos.model.Marca;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MarcaResponseDTO {
    private Integer id;
    private String nome;

    public MarcaResponseDTO(Marca marca) {
        this.id = marca.getId();
        this.nome = marca.getNome();
    }
}