package com.kerubinesapp.mateogdev.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ItemVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_id", nullable = false)
    private Venta venta;

    @ManyToOne(fetch = FetchType.EAGER) // Cambiado a EAGER para asegurar que el producto se cargue con el ítem
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    private int cantidad;
    private Double precioUnitario; // <--- ¡Asegúrate de que este campo esté aquí!

    public ItemVenta(Venta venta, Producto producto, int cantidad) {
        this.venta = venta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = producto.getCosto(); // Asigna el costo del producto al crear el ítem
    }

    public Double calcularSubTotal() {
        return precioUnitario * cantidad;
    }
}
