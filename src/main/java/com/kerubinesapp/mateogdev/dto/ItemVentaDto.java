package com.kerubinesapp.mateogdev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemVentaDto {
    private Long productoId;
    private int cantidad;
}
