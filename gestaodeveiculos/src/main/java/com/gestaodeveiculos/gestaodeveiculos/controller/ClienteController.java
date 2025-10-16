package com.gestaodeveiculos.gestaodeveiculos.controller;

import com.gestaodeveiculos.gestaodeveiculos.dto.ClienteCreateDTO;
import com.gestaodeveiculos.gestaodeveiculos.dto.ClienteResponseDTO;
import com.gestaodeveiculos.gestaodeveiculos.dto.ClienteUpdateDTO;
import com.gestaodeveiculos.gestaodeveiculos.model.Cliente;
import com.gestaodeveiculos.gestaodeveiculos.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping("/inserir")
    public ResponseEntity<ClienteResponseDTO> save(@RequestBody ClienteCreateDTO createDTO) {
        Cliente cliente = Cliente.builder()
                .nome(createDTO.getNome())
                .celular(createDTO.getCelular())
                .email(createDTO.getEmail())
                .dataCadastro(LocalDate.now())
                .build();

        Cliente clienteSalvo = clienteRepository.save(cliente);
        ClienteResponseDTO responseDTO = new ClienteResponseDTO(clienteSalvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ClienteResponseDTO>> listarClientes(){
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteResponseDTO> responseDTOs = clientes.stream()
                .map(ClienteResponseDTO::new)
                .toList();
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ClienteResponseDTO> buscar(@PathVariable Integer id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        ClienteResponseDTO responseDTO = new ClienteResponseDTO(cliente);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<ClienteResponseDTO> alterar(@PathVariable Integer id, @RequestBody ClienteUpdateDTO updateDTO) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        if (updateDTO.getNome() != null) {
            cliente.setNome(updateDTO.getNome());
        }
        if (updateDTO.getEmail() != null) {
            cliente.setEmail(updateDTO.getEmail());
        }
        if (updateDTO.getCelular() != null) {
            cliente.setCelular(updateDTO.getCelular());
        }

        Cliente clienteSalvo = clienteRepository.save(cliente);
        ClienteResponseDTO responseDTO = new ClienteResponseDTO(clienteSalvo);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<ClienteResponseDTO> deletar(@PathVariable Integer id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        clienteRepository.deleteById(id);
        ClienteResponseDTO responseDTO = new ClienteResponseDTO(cliente);
        return ResponseEntity.ok(responseDTO);
    }
}