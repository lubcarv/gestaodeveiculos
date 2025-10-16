package com.gestaodeveiculos.gestaodeveiculos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VeiculoUpdateDTO {
    private String placa;
    private String observacoes;
    private String cor;
    private Integer modeloId;
    private Integer proprietarioId;
}