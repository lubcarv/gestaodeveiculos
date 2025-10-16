package com.gestaodeveiculos.gestaodeveiculos.controller;

import com.gestaodeveiculos.gestaodeveiculos.dto.VeiculoCreateDTO;
import com.gestaodeveiculos.gestaodeveiculos.dto.VeiculoResponseDTO;
import com.gestaodeveiculos.gestaodeveiculos.dto.VeiculoUpdateDTO;
import com.gestaodeveiculos.gestaodeveiculos.model.*;
import com.gestaodeveiculos.gestaodeveiculos.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {
    private final VeiculoRepository veiculoRepository;
    private final MotorRepository motorRepository;
    private final ModeloRepository modeloRepository;
    private final ClienteRepository clienteRepository;
    private final CorRepository corRepository;

    public VeiculoController(VeiculoRepository veiculoRepository, MotorRepository motorRepository,
                             ModeloRepository modeloRepository, ClienteRepository clienteRepository,
                             CorRepository corRepository) {
        this.veiculoRepository = veiculoRepository;
        this.motorRepository = motorRepository;
        this.modeloRepository = modeloRepository;
        this.clienteRepository = clienteRepository;
        this.corRepository = corRepository;
    }

    @PostMapping("/inserir")
    public ResponseEntity<VeiculoResponseDTO> inserir(@RequestBody VeiculoCreateDTO createDTO) throws Exception {
        Motor motor = motorRepository.findById(createDTO.getMotorId())
                .orElseThrow(() -> new Exception("Motor não encontrado"));
        Modelo modelo = modeloRepository.findById(createDTO.getModeloId())
                .orElseThrow(() -> new Exception("Modelo não encontrado"));
        Cliente cliente = clienteRepository.findById(createDTO.getProprietarioId())
                .orElseThrow(() -> new Exception("Cliente não encontrado"));
        Cor cor = corRepository.findById(createDTO.getCorId())
                .orElseThrow(() -> new Exception("Cor não encontrada"));

        Veiculo veiculo = Veiculo.builder()
                .placa(createDTO.getPlaca())
                .observacoes(createDTO.getObservacoes())
                .cor(cor)
                .modelo(modelo)
                .proprietario(cliente)
                .motor(motor)
                .build();

        Veiculo veiculoSalvo = veiculoRepository.save(veiculo);
        VeiculoResponseDTO responseDTO = new VeiculoResponseDTO(veiculoSalvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<VeiculoResponseDTO>> buscar() {
        List<Veiculo> veiculos = veiculoRepository.findAll();
        List<VeiculoResponseDTO> responseDTOs = veiculos.stream()
                .map(VeiculoResponseDTO::new)
                .toList();
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<VeiculoResponseDTO> buscar(@PathVariable Integer id) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado"));
        VeiculoResponseDTO responseDTO = new VeiculoResponseDTO(veiculo);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<VeiculoResponseDTO> update(@PathVariable Integer id, @RequestBody VeiculoUpdateDTO updateDTO) throws Exception {
        Veiculo veiculoExistente = veiculoRepository.findById(id)
                .orElseThrow(() -> new Exception("Veículo não encontrado"));

        if (updateDTO.getPlaca() != null) {
            veiculoExistente.setPlaca(updateDTO.getPlaca());
        }
        if (updateDTO.getModeloId() != null) {
            Modelo modelo = modeloRepository.findById(updateDTO.getModeloId())
                    .orElseThrow(() -> new Exception("Modelo não encontrado"));
            veiculoExistente.setModelo(modelo);
        }
        if (updateDTO.getCorId() != null) {
            Cor cor = corRepository.findById(updateDTO.getCorId())
                    .orElseThrow(() -> new Exception("Cor não encontrada"));
            veiculoExistente.setCor(cor);
        }
        if (updateDTO.getObservacoes() != null) {
            veiculoExistente.setObservacoes(updateDTO.getObservacoes());
        }
        if (updateDTO.getProprietarioId() != null) {
            Cliente cliente = clienteRepository.findById(updateDTO.getProprietarioId())
                    .orElseThrow(() -> new Exception("Cliente não encontrado"));
            veiculoExistente.setProprietario(cliente);
        }
        if (updateDTO.getMotorId() != null) {
            Motor motor = motorRepository.findById(updateDTO.getMotorId())
                    .orElseThrow(() -> new Exception("Motor não encontrado"));
            veiculoExistente.setMotor(motor);
        }

        Veiculo veiculoSalvo = veiculoRepository.save(veiculoExistente);
        VeiculoResponseDTO responseDTO = new VeiculoResponseDTO(veiculoSalvo);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<VeiculoResponseDTO> deletar(@PathVariable Integer id) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado"));

        veiculoRepository.delete(veiculo);
        VeiculoResponseDTO responseDTO = new VeiculoResponseDTO(veiculo);
        return ResponseEntity.ok(responseDTO);
    }
}