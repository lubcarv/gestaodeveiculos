package com.gestaodeveiculos.gestaodeveiculos.dto;

import com.gestaodeveiculos.gestaodeveiculos.model.Cliente;
import com.gestaodeveiculos.gestaodeveiculos.model.Cor;
import com.gestaodeveiculos.gestaodeveiculos.model.Modelo;
import com.gestaodeveiculos.gestaodeveiculos.model.Motor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VeiculoCreateDTO
{
    @NotBlank(message = "A placa é obrigatória. ")
    private String placa;
    @NotBlank(message = "As observações são obrigatórias.")
    private String observacoes;
    @NotBlank(message = "A cor é obrigatória.")
    private Integer corId;
    @NotBlank(message = "O modelo é obrigatório.")
    private Integer modeloId;
    @NotBlank(message = "O proprietário é obrigatório.")
    private Integer proprietarioId;
    @NotBlank(message = "O motor é obrigatório.")
    private Integer motorId;
}

