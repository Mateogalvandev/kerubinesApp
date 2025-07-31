package com.kerubinesapp.mateogdev.repository;

import com.kerubinesapp.mateogdev.model.ItemVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemVentaRepository extends JpaRepository<ItemVenta, Long> {
    //public List<ItemVenta> findByVentaId(Long ventaId);
}
