package com.kerubinesapp.mateogdev.repository;

import com.kerubinesapp.mateogdev.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
}
