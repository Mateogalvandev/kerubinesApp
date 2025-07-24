package com.kerubinesapp.mateogdev.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUsuario;
    private String nombre;
    private String username;
    private String password;
    @OneToMany(mappedBy = "usuarioVenta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Venta> ventas = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Rol rolUsuario;
    private LocalDate fechaIngreso;

    public Usuario(String nombre,String password, String username, LocalDate fechaIngreso) {
        this.password = password;
        this.username = username;
        this.nombre = nombre;
        this.fechaIngreso = fechaIngreso;
    }

    public Usuario(String nombre, String password, String username) {
        this.nombre = nombre;
        this.password = password;
        this.username = username;
    }

    public enum Rol {
        ROLE_USER,
        ROLE_ADMIN
    }


    public Usuario(Long idUsuario, String nombre, String username, String password, List<Venta> ventas, Rol rolUsuario) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.ventas = ventas;
        this.rolUsuario = rolUsuario;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public Rol getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(Rol rolUsuario) {
        this.rolUsuario = rolUsuario;
    }
}