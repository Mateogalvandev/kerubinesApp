package com.kerubinesapp.mateogdev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class registerController {

    // Maneja la solicitud GET para mostrar el formulario de registro
    @GetMapping("/register")
    public String mostrarFormularioRegistro(){
        return "register"; // Esto mapea a src/main/resources/templates/register.html
    }
}
