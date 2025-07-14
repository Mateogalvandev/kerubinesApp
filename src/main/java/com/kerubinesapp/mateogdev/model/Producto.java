package com.kerubinesapp.mateogdev.model;

import jakarta.persistence.*;

import java.util.List;

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
}
