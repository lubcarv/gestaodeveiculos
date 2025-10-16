package com.gestaodeveiculos.gestaodeveiculos.repository;

import com.gestaodeveiculos.gestaodeveiculos.model.Cor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CorRepository extends JpaRepository<Cor, Integer> {
    Optional<Cor> findByName(String name);
}