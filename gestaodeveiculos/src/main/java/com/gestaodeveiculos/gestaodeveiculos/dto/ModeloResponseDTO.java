package com.gestaodeveiculos.gestaodeveiculos.dto;

import com.gestaodeveiculos.gestaodeveiculos.Enum.ECategoria;
import com.gestaodeveiculos.gestaodeveiculos.model.Modelo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModeloResponseDTO {
    private Integer id;
    private String descricao;

    private Integer marcaId;

    private Integer motorId;

    private ECategoria categoria;

    public ModeloResponseDTO(Modelo modelo) {
        this.id = modelo.getId();
        this.descricao = modelo.getDescricao();
        this.marcaId = modelo.getMarca().getId();
        this.motorId = (modelo.getMotor() != null) ? modelo.getMotor().getId() : null;
        this.categoria = modelo.getCategoria();
    }

}
