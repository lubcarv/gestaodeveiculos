package com.gestaodeveiculos.gestaodeveiculos.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCreateDTO {
    @NotBlank(message = "O nome é obrigatório.")
    private String nome;
    @NotBlank(message = "O celular é obrigatório.")
    private String celular;
    @NotBlank(message = "O email é obrigatório.")
    private String email;
}
