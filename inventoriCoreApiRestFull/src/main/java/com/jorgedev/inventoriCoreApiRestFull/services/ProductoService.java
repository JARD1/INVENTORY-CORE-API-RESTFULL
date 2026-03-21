package com.jorgedev.inventoriCoreApiRestFull.services;

import com.jorgedev.inventoriCoreApiRestFull.dtos.ProductoDTO;
import java.util.List;

public interface ProductoService {
    List<ProductoDTO> obtenerTodos();
    ProductoDTO obtenerPorId(Long id);
    ProductoDTO guardar(ProductoDTO productoDTO);
    ProductoDTO actualizar(Long id, ProductoDTO productoDTO);
    void eliminar(Long id);
}