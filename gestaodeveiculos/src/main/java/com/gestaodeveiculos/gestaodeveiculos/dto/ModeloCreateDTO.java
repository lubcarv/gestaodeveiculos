package com.gestaodeveiculos.gestaodeveiculos.dto;

import com.gestaodeveiculos.gestaodeveiculos.Enum.ECategoria;
import com.gestaodeveiculos.gestaodeveiculos.Enum.ETipoCombustivel;
import com.gestaodeveiculos.gestaodeveiculos.model.Marca;
import com.gestaodeveiculos.gestaodeveiculos.model.Motor;
import jakarta.persistence.*;
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
public class ModeloCreateDTO {

    @NotBlank(message = "A descrição é obrigatória.")
    @Size(max = 20, message = "Descrição deve ter até 20 caracteres.")
    private String descricao;
    @NotNull(message = "A marca é obrigatória.")
    private Integer marcaId;
    @NotNull(message = "A categoria é obrigatória.")
    private ECategoria categoria;
    @NotNull(message = "A potência do motor é obrigatória.")
    private Integer potencia;
    @NotNull(message = "O tipo de combustível é obrigatório.")
    private ETipoCombustivel tipoCombustivel;
}