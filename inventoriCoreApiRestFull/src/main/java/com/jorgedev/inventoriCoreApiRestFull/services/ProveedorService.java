package com.jorgedev.inventoriCoreApiRestFull.services;

import com.jorgedev.inventoriCoreApiRestFull.dtos.ProveedorDTO;
import java.util.List;

public interface ProveedorService {
    List<ProveedorDTO> obtenerTodos();
    ProveedorDTO obtenerPorId(Long id);
    ProveedorDTO guardar(ProveedorDTO proveedorDTO);
    ProveedorDTO actualizar(Long id, ProveedorDTO proveedorDTO);
    void eliminar(Long id);
}