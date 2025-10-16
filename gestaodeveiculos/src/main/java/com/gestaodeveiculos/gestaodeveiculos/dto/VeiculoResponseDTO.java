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
    private Integer id;
    private String placa;
    private String observacoes;
    private String cor;
    private Integer modeloId;
    private Integer proprietarioId;
    private Integer motorId;

    public VeiculoResponseDTO(Veiculo veiculo) {
        this.id = veiculo.getId();
        this.placa = veiculo.getPlaca();
        this.observacoes = veiculo.getObservacoes();
        this.cor = veiculo.getCor() != null ? veiculo.getCor().getName() : null;
        this.modeloId = veiculo.getModelo() != null ? veiculo.getModelo().getId() : null;
        this.proprietarioId = veiculo.getProprietario() != null ? veiculo.getProprietario().getId() : null;
        this.motorId = veiculo.getMotor() != null ? veiculo.getMotor().getId() : null;
    }
}