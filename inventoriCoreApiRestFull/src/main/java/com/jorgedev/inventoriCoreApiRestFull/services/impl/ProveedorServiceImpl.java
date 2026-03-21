package com.jorgedev.inventoriCoreApiRestFull.services.impl;

import com.jorgedev.inventoriCoreApiRestFull.dtos.ProveedorDTO;
import com.jorgedev.inventoriCoreApiRestFull.entities.Proveedor;
import com.jorgedev.inventoriCoreApiRestFull.repositories.ProveedorRepository;
import com.jorgedev.inventoriCoreApiRestFull.services.ProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProveedorServiceImpl implements ProveedorService {

    // Spring Boot inyecta automáticamente el repositorio gracias a Lombok
    private final ProveedorRepository proveedorRepository;

    @Override
    public List<ProveedorDTO> obtenerTodos() {
        return proveedorRepository.findAll()
                .stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProveedorDTO obtenerPorId(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con el ID: " + id));
        return mapearADTO(proveedor);
    }

    @Override
    public ProveedorDTO guardar(ProveedorDTO proveedorDTO) {
        Proveedor proveedor = mapearAEntidad(proveedorDTO);
        Proveedor nuevoProveedor = proveedorRepository.save(proveedor);
        return mapearADTO(nuevoProveedor);
    }

    @Override
    public ProveedorDTO actualizar(Long id, ProveedorDTO proveedorDTO) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con el ID: " + id));

        // Actualizamos los datos (nota que en los records se usa dto.nombre() en vez de getNombre())
        proveedor.setNombre(proveedorDTO.nombre());
        proveedor.setContacto(proveedorDTO.contacto());
        proveedor.setTelefono(proveedorDTO.telefono());
        proveedor.setEmail(proveedorDTO.email());

        Proveedor proveedorActualizado = proveedorRepository.save(proveedor);
        return mapearADTO(proveedorActualizado);
    }

    @Override
    public void eliminar(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con el ID: " + id));
        proveedorRepository.delete(proveedor);
    }

    // --- Métodos Privados para Mapeo (Traducción entre Entidad y DTO) ---

    private ProveedorDTO mapearADTO(Proveedor proveedor) {
        return new ProveedorDTO(
                proveedor.getId(),
                proveedor.getNombre(),
                proveedor.getContacto(),
                proveedor.getTelefono(),
                proveedor.getEmail()
        );
    }

    private Proveedor mapearAEntidad(ProveedorDTO dto) {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(dto.nombre());
        proveedor.setContacto(dto.contacto());
        proveedor.setTelefono(dto.telefono());
        proveedor.setEmail(dto.email());
        return proveedor;
    }
}