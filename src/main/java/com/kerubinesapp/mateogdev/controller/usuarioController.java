package com.kerubinesapp.mateogdev.controller;

import com.kerubinesapp.mateogdev.model.Usuario;
import com.kerubinesapp.mateogdev.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class usuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios/administrar")
    public String listaUsuarios(Model model){
        model.addAttribute("usuariosLista", usuarioService.usuarioLista());
        return "usuariosTabla";
    }

    @GetMapping("/usuarios/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model){
        model.addAttribute("usuarioEditar", usuarioService.usuarioObtenerId(id));
        return "usuarioEditar";
    }

    @PostMapping("/usuarios/editar/post/{id}")
    public String editarUsuarioAccion(@PathVariable Long id,
                                      @ModelAttribute("usuarioEditar")Usuario usuario,
                                      Model model){
        Usuario usuarioExistente = usuarioService.usuarioObtenerId(id);
        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setUsername(usuario.getUsername());
        usuarioExistente.setPassword(usuario.getPassword());
        usuarioService.actualizarUsuario(usuarioExistente);
        return "redirect:/usuarios/administrar";


    }

    @GetMapping("/usuarios/eliminar/{id}")
        public String usuarioEliminar(@PathVariable Long id){
        usuarioService.eliminarUsuario(id);
        return "redirect:/usuarios/administrar";
    }
}
