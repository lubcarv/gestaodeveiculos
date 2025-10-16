package com.gestaodeveiculos.gestaodeveiculos.repository;

import com.gestaodeveiculos.gestaodeveiculos.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {
}
