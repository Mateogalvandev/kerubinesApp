package com.kerubinesapp.mateogdev.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private String modalidadVenta;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuarioVenta;


    @OneToMany(mappedBy = "venta", orphanRemoval = true)
    private List<ItemVenta> items = new ArrayList<>();

    public void agregarItem(Producto producto, Integer cantidad){
        ItemVenta itemVenta = new ItemVenta(this, producto, cantidad);
        items.add(itemVenta);
    }



    public Venta(Long idVenta, String nombreCliente, String numeroCliente, LocalDateTime date, Double total,String modalidadVenta, String tipoDeVenta) {
        this.idVenta = idVenta;
        this.nombreCliente = nombreCliente;
        this.numeroCliente = numeroCliente;
        this.Date = date;
        this.total = total;
        this.modalidadVenta = modalidadVenta;
        this.tipoDeVenta = tipoDeVenta;

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
                ", usuarioId=" + (usuarioVenta != null ? usuarioVenta.getIdUsuario() : null);
    }

}
