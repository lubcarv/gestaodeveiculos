package com.gestaodeveiculos.gestaodeveiculos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarcaCreateDTO {
    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 20, message = "O nome deve ter até 20 caracteres")
    private String nome;
}
