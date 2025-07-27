package com.kerubinesapp.mateogdev.model;

import jakarta.persistence.*;
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
@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;
    private String nombreCliente;
    private String numeroCliente;
    private LocalDateTime Date;
    private Double total;
    private String tipoDeVenta;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuarioVenta;
    @ManyToMany
    @JoinTable(
            name = "venta_producto",
            joinColumns = @JoinColumn(name = "venta_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productoLista;

    public Venta(Long idVenta, String nombreCliente, String numeroCliente, LocalDateTime date, Double total, String tipoDeVenta, List<Producto> productoLista) {
        this.idVenta = idVenta;
        this.nombreCliente = nombreCliente;
        this.numeroCliente = numeroCliente;
        this.Date = date;
        this.total = total;
        this.tipoDeVenta = tipoDeVenta;
        this.productoLista = productoLista;

    }

    @Override
    public String toString() {
        return "Venta{" +
                "idVenta=" + idVenta +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", numeroCliente='" + numeroCliente + '\'' +
                ", Date=" + Date +
                ", total=" + total +
                ", tipoDeVenta='" + tipoDeVenta + '\'' +
                ", usuarioId=" + (usuarioVenta != null ? usuarioVenta.getIdUsuario() : null) +
                ", productosCount=" + (productoLista != null ? productoLista.size() : 0) +
                '}';
    }

}
