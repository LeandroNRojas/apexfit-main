package com.apexfit.apexfit.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.apexfit.apexfit.DTO.ClienteRequestDTO;
import com.apexfit.apexfit.DTO.ClienteResponseDTO;
import com.apexfit.apexfit.model.Cliente;
import com.apexfit.apexfit.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;


    // ── MAPEO PRIVADO: Entidad → ResponseDTO ─────────
    private ClienteResponseDTO mapToDTO(Cliente cliente) {
        return new ClienteResponseDTO(
            cliente.getRut(),
            cliente.getCorreo(),
            cliente.getContraseña(),
            cliente.getNombre(),
            cliente.getApellido(),
            cliente.getFechaNacimiento()
        );
    }

    // ── OBTENER TODOS ────────────────────────────────
    public List<ClienteResponseDTO> obtenerTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ── OBTENER POR ID ───────────────────────────────
    public Optional<ClienteResponseDTO> obtenerPorId(Long id) {
        return clienteRepository.findById(id).map(this::mapToDTO);
    }

    // ── GUARDAR ──────────────────────────────────────
    public ClienteResponseDTO guardar(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente(
                null,
                dto.getRut(),
                dto.getCorreo(),
                dto.getContraseña(),
                dto.getNombre(),
                dto.getApellido(),
                dto.getFechaNacimiento()
        );
        return mapToDTO(clienteRepository.save(cliente));
    }

    // ── ACTUALIZAR ───────────────────────────────────
    public Optional<ClienteResponseDTO> actualizar(Long id, ClienteRequestDTO dto) {
        return clienteRepository.findById(id).map(existente -> {
            existente.setContraseña(dto.getContraseña());
            existente.setCorreo(dto.getCorreo());
            existente.setNombre(dto.getNombre());
            existente.setApellido(dto.getApellido());
            existente.setFechaNacimiento(dto.getFechaNacimiento());
            return mapToDTO(clienteRepository.save(existente));
        });
    }

    // ── ELIMINAR ─────────────────────────────────────
    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }

    // ── BÚSQUEDAS ────────────────────────────────────
    public List<ClienteResponseDTO> buscarPorNombre(String nombre) {
        return clienteRepository.findByNombreContainingIgnoreCase(nombre)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<ClienteResponseDTO> findById(Long id) {
        return clienteRepository.findById(id)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }
}
