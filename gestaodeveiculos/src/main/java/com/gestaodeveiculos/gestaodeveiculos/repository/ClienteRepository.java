package com.gestaodeveiculos.gestaodeveiculos.repository;

import com.gestaodeveiculos.gestaodeveiculos.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository
        extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findById(Integer integer);
}
