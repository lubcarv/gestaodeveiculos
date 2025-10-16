package com.gestaodeveiculos.gestaodeveiculos.controller;

import com.gestaodeveiculos.gestaodeveiculos.dto.ModeloCreateDTO;
import com.gestaodeveiculos.gestaodeveiculos.dto.ModeloResponseDTO;
import com.gestaodeveiculos.gestaodeveiculos.dto.ModeloUpdateDTO;
import com.gestaodeveiculos.gestaodeveiculos.model.Marca;
import com.gestaodeveiculos.gestaodeveiculos.model.Modelo;
import com.gestaodeveiculos.gestaodeveiculos.model.Motor;
import com.gestaodeveiculos.gestaodeveiculos.repository.MarcaRepository;
import com.gestaodeveiculos.gestaodeveiculos.repository.ModeloRepository;
import com.gestaodeveiculos.gestaodeveiculos.repository.MotorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/modelo")
public class ModeloController {

    private final ModeloRepository modeloRepository;
    private final MarcaRepository marcaRepository;
    private final MotorRepository motorRepository;

    public ModeloController(ModeloRepository modeloRepository, MarcaRepository marcaRepository, MotorRepository motorRepository) {
        this.modeloRepository = modeloRepository;
        this.marcaRepository = marcaRepository;
        this.motorRepository = motorRepository;
    }

    @PostMapping("/inserir")
    public ResponseEntity<ModeloResponseDTO> inserir(@RequestBody ModeloCreateDTO createDTO) throws Exception {
        Marca marca = marcaRepository.findById(createDTO.getMarcaId())
                .orElseThrow(() -> new Exception("Marca não encontrada"));

        Modelo modelo = Modelo.builder()
                .descricao(createDTO.getDescricao())
                .marca(marca)
                .categoria(createDTO.getCategoria())
                .build();

        Modelo modeloSalvo = modeloRepository.save(modelo);

        Motor motor = Motor.builder()
                .potencia(createDTO.getPotencia())
                .tipoCombustivel(createDTO.getTipoCombustivel())
                .modelo(modeloSalvo)
                .build();

        Motor motorSalvo = motorRepository.save(motor);

        modeloSalvo.setMotor(motorSalvo);
        modeloRepository.save(modeloSalvo);

        ModeloResponseDTO responseDTO = new ModeloResponseDTO(modeloSalvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    @GetMapping("/buscar")
    public ResponseEntity<List<ModeloResponseDTO>> findAll(){
        List<Modelo> modelosList = modeloRepository.findAll();
        List<ModeloResponseDTO> responseDTOs = modelosList.stream()
                .map(ModeloResponseDTO::new)
                .toList();
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ModeloResponseDTO> findById(@PathVariable Integer id){
        Modelo modelo = modeloRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Modelo não encontrado"));
        ModeloResponseDTO responseDTO = new ModeloResponseDTO(modelo);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<ModeloResponseDTO> alterar(@PathVariable Integer id, @RequestBody ModeloUpdateDTO updateDTO) throws Exception {
        Modelo modeloExistente = modeloRepository.findById(id)
                .orElseThrow(() -> new Exception("Modelo não encontrado"));

        if (updateDTO.getDescricao() != null) {
            modeloExistente.setDescricao(updateDTO.getDescricao());
        }
        if (updateDTO.getMarcaId() != null) {
            Marca marca = marcaRepository.findById(updateDTO.getMarcaId())
                    .orElseThrow(() -> new Exception("Marca não encontrada"));
            modeloExistente.setMarca(marca);
        }
        if (updateDTO.getCategoria() != null) {
            modeloExistente.setCategoria(updateDTO.getCategoria());
        }

        if (updateDTO.getPotencia() != null || updateDTO.getTipoCombustivel() != null) {
            Optional<Motor> motorOptional = motorRepository.findById(id);

            if (motorOptional.isPresent()) {
                Motor motorExistente = motorOptional.get();
                if (updateDTO.getPotencia() != null) {
                    motorExistente.setPotencia(updateDTO.getPotencia());
                }
                if (updateDTO.getTipoCombustivel() != null) {
                    motorExistente.setTipoCombustivel(updateDTO.getTipoCombustivel());
                }
                motorRepository.save(motorExistente);
            } else {
                Motor novoMotor = Motor.builder()
                        .potencia(updateDTO.getPotencia())
                        .tipoCombustivel(updateDTO.getTipoCombustivel())
                        .modelo(modeloExistente)
                        .build();
                Motor motorSalvo = motorRepository.save(novoMotor);
                modeloExistente.setMotor(motorSalvo);
            }
        }

        Modelo modeloSalvo = modeloRepository.save(modeloExistente);
        ModeloResponseDTO responseDTO = new ModeloResponseDTO(modeloSalvo);
        return ResponseEntity.ok(responseDTO);
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<ModeloResponseDTO> deletar(@PathVariable Integer id){
        Modelo modeloSalvo = modeloRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Modelo não encontrado"));

        Motor motor = modeloSalvo.getMotor();
        if (motor != null) {
            motorRepository.deleteById(motor.getId());
        }
        modeloRepository.deleteById(id);

        ModeloResponseDTO responseDTO = new ModeloResponseDTO(modeloSalvo);
        return ResponseEntity.ok(responseDTO);
    }
}