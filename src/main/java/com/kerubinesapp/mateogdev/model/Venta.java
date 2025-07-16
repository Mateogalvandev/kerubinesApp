package com.kerubinesapp.mateogdev.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idVenta;
    private LocalDateTime Date;
    private Double total;
    private String tipoDeVenta;
    @ManyToOne
    private Usuario usuarioVenta;
    @ManyToMany
    private List<Producto> productoLista;

}
