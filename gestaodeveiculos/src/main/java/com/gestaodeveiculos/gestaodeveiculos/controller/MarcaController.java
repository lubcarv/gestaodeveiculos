package com.gestaodeveiculos.gestaodeveiculos.controller;

import com.gestaodeveiculos.gestaodeveiculos.dto.MarcaCreateDTO;
import com.gestaodeveiculos.gestaodeveiculos.dto.MarcaResponseDTO;
import com.gestaodeveiculos.gestaodeveiculos.dto.MarcaUpdateDTO;
import com.gestaodeveiculos.gestaodeveiculos.model.Marca;
import com.gestaodeveiculos.gestaodeveiculos.repository.MarcaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/marca")
public class MarcaController {
    private final MarcaRepository marcaRepository;

    public MarcaController(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    @PostMapping("/inserir")
    public ResponseEntity<MarcaResponseDTO> save(@RequestBody MarcaCreateDTO createDTO){
        Marca marca = Marca.builder()
                .nome(createDTO.getNome())
                .build();
        Marca marcaSalva = marcaRepository.save(marca);
        MarcaResponseDTO responseDTO = new MarcaResponseDTO(marcaSalva);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<MarcaResponseDTO>> findAll(){
        List<Marca> marcaList = marcaRepository.findAll();
        List<MarcaResponseDTO> responseDTOs = marcaList.stream()
                .map(MarcaResponseDTO::new)
                .toList();
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<MarcaResponseDTO> findById(@PathVariable Integer id) {
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Marca não encontrada"));
        MarcaResponseDTO responseDTO = new MarcaResponseDTO(marca);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<MarcaResponseDTO> update(@PathVariable Integer id, @RequestBody MarcaUpdateDTO updateDTO) {
        Marca marcaExistente = marcaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Marca não encontrada"));

        if (updateDTO.getNome() != null) {
            marcaExistente.setNome(updateDTO.getNome());
        }

        Marca marcaSalva = marcaRepository.save(marcaExistente);
        MarcaResponseDTO responseDTO = new MarcaResponseDTO(marcaSalva);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<MarcaResponseDTO> deleteById(@PathVariable Integer id) {
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Marca não encontrada"));

        marcaRepository.deleteById(id);
        MarcaResponseDTO responseDTO = new MarcaResponseDTO(marca);
        return ResponseEntity.ok(responseDTO);
    }
}