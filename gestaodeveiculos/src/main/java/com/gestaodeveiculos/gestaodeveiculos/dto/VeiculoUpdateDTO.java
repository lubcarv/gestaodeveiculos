package com.gestaodeveiculos.gestaodeveiculos.dto;

import jakarta.validation.constraints.NotBlank;
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
    private Integer corId;
    private Integer modeloId;
    private Integer proprietarioId;
    private Integer motorId;
}
