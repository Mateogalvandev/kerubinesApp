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
    private String nombreCliente;
    private String numeroCliente;
    private LocalDateTime Date;
    private Double total;
    private String tipoDeVenta;
    private Usuario usuarioVenta;
    private List<Producto> productoLista;

    public VentaDto(Long idVenta, String nombreCliente, String numeroCliente, LocalDateTime date, Double total, String tipoDeVenta, List<Producto> productoLista) {
        this.idVenta = idVenta;
        this.nombreCliente = nombreCliente;
        this.numeroCliente = numeroCliente;
        this.Date = date;
        this.total = total;
        this.tipoDeVenta = tipoDeVenta;
        this.productoLista = productoLista;

    }
}
