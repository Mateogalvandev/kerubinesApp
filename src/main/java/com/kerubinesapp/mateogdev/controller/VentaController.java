package com.kerubinesapp.mateogdev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VentaController {

    @GetMapping("/venta/crear")
    public String crearVentas() {
        return "ventaCrear";
    }
}
