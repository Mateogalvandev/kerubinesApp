package com.kerubinesapp.mateogdev.dto;

import com.kerubinesapp.mateogdev.model.ItemVenta;
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
public class VentaDto {


    private Long idVenta;
    private String nombreCliente;
    private String numeroCliente;
    private LocalDateTime Date;
    private Double total;
    private String tipoDeVenta;
    private Usuario usuarioVenta;
    private String modalidadVenta;
    private List<ItemVentaDto> items;

    public VentaDto(Long idVenta, String nombreCliente, String numeroCliente, LocalDateTime date, Double total, String modalidadVenta,String tipoDeVenta) {
        this.idVenta = idVenta;
        this.nombreCliente = nombreCliente;
        this.numeroCliente = numeroCliente;
        this.Date = date;
        this.total = total;
        this.modalidadVenta = modalidadVenta;
        this.tipoDeVenta = tipoDeVenta;

    }

    public VentaDto(String nombreCliente, String numeroCliente, LocalDateTime date, Double total, String tipoDeVenta, Usuario usuarioVenta, List<ItemVentaDto> items) {
        this.nombreCliente = nombreCliente;
        this.numeroCliente = numeroCliente;
        Date = date;
        this.total = total;
        this.tipoDeVenta = tipoDeVenta;
        this.usuarioVenta = usuarioVenta;
        this.items = items;
    }

    public VentaDto(Long idVenta, String nombreCliente, String numeroCliente, LocalDateTime date, Double total, String tipoDeVenta, Usuario usuarioVenta, List<ItemVentaDto> items) {
        this.idVenta = idVenta;
        this.nombreCliente = nombreCliente;
        this.numeroCliente = numeroCliente;
        Date = date;
        this.total = total;
        this.tipoDeVenta = tipoDeVenta;
        this.usuarioVenta = usuarioVenta;
        this.items = items;
    }
}
