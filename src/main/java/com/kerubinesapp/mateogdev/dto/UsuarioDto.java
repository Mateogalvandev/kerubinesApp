package com.kerubinesapp.mateogdev.dto;

import com.kerubinesapp.mateogdev.model.Rol;
import com.kerubinesapp.mateogdev.model.Venta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

    private String nombre;
    private String username;
    private String password;
    private Rol rolUsuario;



}
