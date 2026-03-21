package com.jorgedev.inventoriCoreApiRestFull.dtos;

import java.math.BigDecimal;

public record ProductoDTO(
        Long id,
        String codigoSku,
        String nombre,
        String descripcion,
        BigDecimal precio,
        Integer stockActual,
        Long proveedorId // Solo pedimos/devolvemos el ID del proveedor, no toda la entidad
) {
}