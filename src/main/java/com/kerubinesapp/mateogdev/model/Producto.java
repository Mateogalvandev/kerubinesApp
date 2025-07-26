    package com.kerubinesapp.mateogdev.model;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.util.List;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    public class Producto {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long idProducto;
        private String nombreProducto;
        private String talla;
        private String color;
        private String descripcion;
        private Double costo;
        private Double stock;
        @ManyToMany
        private List<Venta> ventaLista;

        public Producto(String nombreProducto, String talla, String color, String descripcion, Double costo, Double stock, List<Venta> ventaLista) {
            this.nombreProducto = nombreProducto;
            this.talla = talla;
            this.color = color;
            this.descripcion = descripcion;
            this.costo = costo;
            this.stock = stock;
        }

        public Producto(String nombreProducto, String talla, String color, String descripcion, Double costo, Double stock) {
            this.nombreProducto = nombreProducto;
            this.talla = talla;
            this.color = color;
            this.descripcion = descripcion;
            this.costo = costo;
            this.stock = stock;
        }

    }
