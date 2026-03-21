package com.jorgedev.inventoriCoreApiRestFull.repositories;

import com.jorgedev.inventoriCoreApiRestFull.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}