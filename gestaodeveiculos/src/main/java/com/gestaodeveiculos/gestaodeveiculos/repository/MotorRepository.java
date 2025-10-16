package com.gestaodeveiculos.gestaodeveiculos.repository;

import com.gestaodeveiculos.gestaodeveiculos.model.Motor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MotorRepository
        extends JpaRepository<Motor, Integer> {
    Optional<Motor> findMotorById(int id);
}
