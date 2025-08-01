package com.kerubinesapp.mateogdev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemVentaDto {
    private Long productoId;
    private int cantidad;
    private Double precioUnitario;

    public ItemVentaDto(Long productoId, int cantidad, Double precioUnitario) {
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }
    // Puedes añadir otros campos si los necesitas en el frontend,
    // pero no el objeto Producto completo para simplificar.
}
