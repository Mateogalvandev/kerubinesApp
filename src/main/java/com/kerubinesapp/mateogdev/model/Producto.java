    package com.kerubinesapp.mateogdev.model;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.util.ArrayList;
    import java.util.List;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    public class Producto {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idProducto;
        private String nombreProducto;
        private String talla;
        private String color;
        private String descripcion;
        private Double costo;
        private Long stock;

        @OneToMany(mappedBy = "producto", orphanRemoval = true)
        private List<ItemVenta> itemsVenta = new ArrayList<>();


        public Producto(String nombreProducto, String talla, String color, String descripcion, Double costo, Long stock) {
            this.nombreProducto = nombreProducto;
            this.talla = talla;
            this.color = color;
            this.descripcion = descripcion;
            this.costo = costo;
            this.stock = stock;
        }

        @Override
        public String toString() {
            return "Producto{" +
                    "idProducto=" + idProducto +
                    ", nombreProducto='" + nombreProducto + '\'' +
                    ", talla='" + talla + '\'' +
                    ", color='" + color + '\'' +
                    ", costo=" + costo +
                    ", stock=" + stock;
        }

    }
