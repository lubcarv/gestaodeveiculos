package com.gestaodeveiculos.gestaodeveiculos.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteUpdateDTO {
    private String nome;
    private String celular;
    private String email;
}
