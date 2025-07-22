package com.kerubinesapp.mateogdev.repository;

import com.kerubinesapp.mateogdev.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
