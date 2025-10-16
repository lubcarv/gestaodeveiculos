package com.gestaodeveiculos.gestaodeveiculos.dto;

import com.gestaodeveiculos.gestaodeveiculos.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteResponseDTO {
    private Integer id;
    private String nome;
    private String celular;
    private String email;
    private LocalDate dataCadastro;

    public ClienteResponseDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.celular = cliente.getCelular();
        this.email = cliente.getEmail();
        this.dataCadastro = cliente.getDataCadastro();
    }
}