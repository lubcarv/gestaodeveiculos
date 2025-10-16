package com.gestaodeveiculos.gestaodeveiculos.dto;

import com.gestaodeveiculos.gestaodeveiculos.Enum.ECategoria;
import com.gestaodeveiculos.gestaodeveiculos.Enum.ETipoCombustivel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModeloUpdateDTO {
    @Size(max = 20, message = "Descrição deve ter até 20 caracteres.")
    private String descricao;
    private Integer marcaId;
    private ECategoria categoria;
    private Integer potencia;
    private ETipoCombustivel tipoCombustivel;
}
