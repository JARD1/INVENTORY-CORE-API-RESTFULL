package com.jorgedev.inventoriCoreApiRestFull.controllers;

import com.jorgedev.inventoriCoreApiRestFull.dtos.ProveedorDTO;
import com.jorgedev.inventoriCoreApiRestFull.services.ProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta clase es un controlador REST (devuelve JSON)
@RequestMapping("/api/v1/proveedores") // La ruta base para todos los métodos de esta clase
@RequiredArgsConstructor
public class ProveedorController {

    // Inyectamos nuestro servicio
    private final ProveedorService proveedorService;

    // GET: http://localhost:8081/api/v1/proveedores
    @GetMapping
    public ResponseEntity<List<ProveedorDTO>> listarTodos() {
        return ResponseEntity.ok(proveedorService.obtenerTodos());
    }

    // GET: http://localhost:8081/api/v1/proveedores/1
    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(proveedorService.obtenerPorId(id));
    }

    // POST: http://localhost:8081/api/v1/proveedores
    @PostMapping
    public ResponseEntity<ProveedorDTO> guardar(@RequestBody ProveedorDTO proveedorDTO) {
        ProveedorDTO nuevoProveedor = proveedorService.guardar(proveedorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProveedor);
    }

    // PUT: http://localhost:8081/api/v1/proveedores/1
    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDTO> actualizar(@PathVariable Long id, @RequestBody ProveedorDTO proveedorDTO) {
        return ResponseEntity.ok(proveedorService.actualizar(id, proveedorDTO));
    }

    // DELETE: http://localhost:8081/api/v1/proveedores/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        proveedorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}