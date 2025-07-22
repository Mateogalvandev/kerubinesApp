package com.kerubinesapp.mateogdev.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProducto;
    private String nombreProducto;
    private String talla;
    private String color;
    private String descripcion;
    private Double costo;
    @ManyToMany
    private List<Venta> ventaLista;

    public Producto(String nombreProducto, String descripcion, String talla, String color, Double costo) {
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.talla = talla;
        this.color = color;
        this.costo = costo;
    }
}
