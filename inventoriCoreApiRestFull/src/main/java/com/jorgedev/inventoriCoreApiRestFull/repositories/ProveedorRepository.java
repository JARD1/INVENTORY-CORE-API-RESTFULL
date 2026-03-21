package com.jorgedev.inventoriCoreApiRestFull.repositories;

import com.jorgedev.inventoriCoreApiRestFull.entities.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
}