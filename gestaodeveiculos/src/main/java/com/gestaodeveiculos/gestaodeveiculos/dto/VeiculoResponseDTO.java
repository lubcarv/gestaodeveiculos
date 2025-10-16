package com.gestaodeveiculos.gestaodeveiculos.dto;

import com.gestaodeveiculos.gestaodeveiculos.model.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VeiculoResponseDTO {
    private String placa;
    private String observacoes;
    private Integer corId;
    private Integer modeloId;
    private Integer proprietarioId;
    private Integer motorId;


    public VeiculoResponseDTO(Veiculo veiculo) {
        this.placa = veiculo.getPlaca();
        this.observacoes = veiculo.getObservacoes();
        this.corId = veiculo.getCor().getId();
        this.modeloId = veiculo.getModelo().getId();
        this.proprietarioId = veiculo.getProprietario().getId();
        this.motorId = veiculo.getMotor().getId();
    }
}
