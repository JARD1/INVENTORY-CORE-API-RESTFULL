package com.jorgedev.inventoriCoreApiRestFull.dtos;

public record ProveedorDTO(
        Long id,
        String nombre,
        String contacto,
        String telefono,
        String email
) {
}