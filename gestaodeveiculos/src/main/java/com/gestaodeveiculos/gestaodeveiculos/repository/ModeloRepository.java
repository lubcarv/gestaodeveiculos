package com.gestaodeveiculos.gestaodeveiculos.repository;

import com.gestaodeveiculos.gestaodeveiculos.model.Modelo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ModeloRepository extends JpaRepository<Modelo, Integer> {

    @EntityGraph(attributePaths = {"motor", "marca"})
    List<Modelo> findAll();

    @EntityGraph(attributePaths = {"motor", "marca"})
    Optional<Modelo> findById(Integer id);
}