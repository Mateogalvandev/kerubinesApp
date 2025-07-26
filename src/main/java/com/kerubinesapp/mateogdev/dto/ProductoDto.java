package com.kerubinesapp.mateogdev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDto {
    private Long idProducto;
    private String nombreProducto;
    private String talla;
    private String color;
    private String descripcion;
    private Double costo;
    private Double stock;

}
