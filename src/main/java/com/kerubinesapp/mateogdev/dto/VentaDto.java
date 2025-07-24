package com.kerubinesapp.mateogdev.dto;

import com.kerubinesapp.mateogdev.model.Producto;
import com.kerubinesapp.mateogdev.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDto {


    private Long idVenta;
    private LocalDateTime Date;
    private Double total;
    private String tipoDeVenta;
    private Usuario usuarioVenta;
    private Set<Producto> productoLista = new HashSet<>();}
