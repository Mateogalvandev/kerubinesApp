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
@AllArgsConstructor // Asegúrate de que Lombok genere un constructor con todos los campos
public class VentaDto {
    private Long idVenta;
    private String nombreCliente;
    private String numeroCliente;
    private LocalDateTime Date;
    private Double total;
    private String tipoDeVenta;
    private Usuario usuarioVenta;
    private String modalidadVenta;
    private Double porcentaje; // ¡Campo agregado!
    private List<ItemVentaDto> items;

    // Puedes mantener tus constructores específicos si los necesitas,
    // pero con @Data y @AllArgsConstructor, Lombok ya genera los getters/setters
    // y un constructor con todos los campos.

    // Si aún necesitas constructores específicos, asegúrate de incluir 'porcentaje'
    // en ellos si es relevante para su uso. Por ejemplo:

    public VentaDto(Long idVenta, String nombreCliente, String numeroCliente, LocalDateTime date, Double total, String modalidadVenta, String tipoDeVenta, Double porcentaje) {
        this.idVenta = idVenta;
        this.nombreCliente = nombreCliente;
        this.numeroCliente = numeroCliente;
        this.Date = date;
        this.total = total;
        this.modalidadVenta = modalidadVenta;
        this.tipoDeVenta = tipoDeVenta;
        this.porcentaje = porcentaje; // Incluir en el constructor
    }

    public VentaDto(String nombreCliente, String numeroCliente, LocalDateTime date, Double total, String tipoDeVenta, Usuario usuarioVenta, List<ItemVentaDto> items, Double porcentaje) {
        this.nombreCliente = nombreCliente;
        this.numeroCliente = numeroCliente;
        Date = date;
        this.total = total;
        this.tipoDeVenta = tipoDeVenta;
        this.usuarioVenta = usuarioVenta;
        this.items = items;
        this.porcentaje = porcentaje; // Incluir en el constructor
    }

    public VentaDto(Long idVenta, String nombreCliente, String numeroCliente, LocalDateTime date, Double total, String tipoDeVenta, Usuario usuarioVenta, List<ItemVentaDto> items, Double porcentaje) {
        this.idVenta = idVenta;
        this.nombreCliente = nombreCliente;
        this.numeroCliente = numeroCliente;
        Date = date;
        this.total = total;
        this.tipoDeVenta = tipoDeVenta;
        this.usuarioVenta = usuarioVenta;
        this.items = items;
        this.porcentaje = porcentaje; // Incluir en el constructor
    }
}
