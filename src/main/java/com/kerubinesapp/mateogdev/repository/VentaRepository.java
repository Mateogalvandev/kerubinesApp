package com.kerubinesapp.mateogdev.repository;

import com.kerubinesapp.mateogdev.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    @Query("SELECT v FROM Venta v JOIN FETCH v.items i JOIN FETCH i.producto")
    List<Venta> findAllWithItemsAndProducts();
}
