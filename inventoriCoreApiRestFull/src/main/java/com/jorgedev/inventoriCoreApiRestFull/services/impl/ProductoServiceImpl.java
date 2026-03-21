package com.jorgedev.inventoriCoreApiRestFull.services.impl;

import com.jorgedev.inventoriCoreApiRestFull.dtos.ProductoDTO;
import com.jorgedev.inventoriCoreApiRestFull.entities.Producto;
import com.jorgedev.inventoriCoreApiRestFull.entities.Proveedor;
import com.jorgedev.inventoriCoreApiRestFull.repositories.ProductoRepository;
import com.jorgedev.inventoriCoreApiRestFull.repositories.ProveedorRepository;
import com.jorgedev.inventoriCoreApiRestFull.services.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    // Inyectamos también el de Proveedor para validar que exista al crear un producto
    private final ProveedorRepository proveedorRepository;

    @Override
    public List<ProductoDTO> obtenerTodos() {
        return productoRepository.findAll()
                .stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO obtenerPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));
        return mapearADTO(producto);
    }

    @Override
    public ProductoDTO guardar(ProductoDTO productoDTO) {
        // 1. Buscamos el proveedor en la BD usando el ID que viene en el DTO
        Proveedor proveedor = proveedorRepository.findById(productoDTO.proveedorId())
                .orElseThrow(() -> new RuntimeException("No se puede crear el producto. Proveedor no encontrado con ID: " + productoDTO.proveedorId()));

        // 2. Mapeamos y le asignamos el proveedor real
        Producto producto = mapearAEntidad(productoDTO);
        producto.setProveedor(proveedor);

        // 3. Guardamos en la base de datos
        Producto nuevoProducto = productoRepository.save(producto);
        return mapearADTO(nuevoProducto);
    }

    @Override
    public ProductoDTO actualizar(Long id, ProductoDTO productoDTO) {
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));

        Proveedor proveedor = proveedorRepository.findById(productoDTO.proveedorId())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + productoDTO.proveedorId()));

        // Actualizamos los campos
        productoExistente.setCodigoSku(productoDTO.codigoSku());
        productoExistente.setNombre(productoDTO.nombre());
        productoExistente.setDescripcion(productoDTO.descripcion());
        productoExistente.setPrecio(productoDTO.precio());
        productoExistente.setStockActual(productoDTO.stockActual());
        productoExistente.setProveedor(proveedor); // Actualizamos la relación

        Producto productoActualizado = productoRepository.save(productoExistente);
        return mapearADTO(productoActualizado);
    }

    @Override
    public void eliminar(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));
        productoRepository.delete(producto);
    }

    // --- Métodos Privados para Mapeo ---

    private ProductoDTO mapearADTO(Producto producto) {
        return new ProductoDTO(
                producto.getId(),
                producto.getCodigoSku(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStockActual(),
                producto.getProveedor().getId() // Sacamos solo el ID del proveedor
        );
    }

    private Producto mapearAEntidad(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setCodigoSku(dto.codigoSku());
        producto.setNombre(dto.nombre());
        producto.setDescripcion(dto.descripcion());
        producto.setPrecio(dto.precio());
        producto.setStockActual(dto.stockActual());
        // El proveedor lo seteamos directamente en los métodos guardar y actualizar
        return producto;
    }
}