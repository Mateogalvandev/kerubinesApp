package com.kerubinesapp.mateogdev.controller;

import com.kerubinesapp.mateogdev.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class usuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios/administrar")
    public String listaUsuarios(Model model){
        model.addAttribute("usuariosLista", usuarioService.usuarioLista());
        return "usuariosTabla";
    }
}
