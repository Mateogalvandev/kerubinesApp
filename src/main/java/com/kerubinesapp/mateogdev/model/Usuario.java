package com.kerubinesapp.mateogdev.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUsuario;
    private String nombre;
    private String username;
    private String password;
    @OneToMany
    private List<Venta> ventaLista;

}