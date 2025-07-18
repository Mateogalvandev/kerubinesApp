package com.kerubinesapp.mateogdev.controller;

import com.kerubinesapp.mateogdev.dto.UsuarioDto;
import com.kerubinesapp.mateogdev.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class registerController {

    private final UsuarioService usuarioService;

    public registerController (UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @ModelAttribute("usuario")
    public UsuarioDto nuevoUsuarioDto() {
        return new UsuarioDto();
    }

    @GetMapping("/register")
    public String mostrarFormularioRegistro() {
        return "register";
    }

    @GetMapping("/usuarios/crear")
    public String mostrarFormularioRegistroPagina() {
        return "register";
    }

    @PostMapping("/register")
    public String registrarCuentaUsuario(@ModelAttribute("usuario") UsuarioDto usuarioDto) {
        usuarioService.guardarUsuario(usuarioDto);
        return "redirect:/login";
    }

    @GetMapping("/table")
    public String mostrarTablaPrueba() {
        return "table-view";
    }
}
