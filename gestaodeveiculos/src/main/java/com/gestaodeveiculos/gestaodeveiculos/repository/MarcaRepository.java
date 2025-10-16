package com.gestaodeveiculos.gestaodeveiculos.repository;

import com.gestaodeveiculos.gestaodeveiculos.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {
}
