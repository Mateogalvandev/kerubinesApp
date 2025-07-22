package com.kerubinesapp.mateogdev.controller;

import com.kerubinesapp.mateogdev.dto.ProductoDto;
import com.kerubinesapp.mateogdev.model.Producto;
import com.kerubinesapp.mateogdev.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class productoController {

    @Autowired
    private ProductoService productoService;

    @ModelAttribute("producto")
    public ProductoDto productoDto() {
        return new ProductoDto();
    }

    @GetMapping("/productos/crear")
    public String crearProducto(Model model) {
        ProductoDto productoDto = new ProductoDto();
        model.addAttribute("productoEntidad", productoDto);
        return "productoCrear";
    }

    @PostMapping("/productos/crear/post")
    public String crearProductoPost(@ModelAttribute("producto") ProductoDto productoDto) {
        productoService.guardarProducto(productoDto);

        System.out.println("Nombre: " + productoDto.getNombreProducto());
        System.out.println("Descripcion: " + productoDto.getDescripcion());
        System.out.println("Talla: " + productoDto.getTalla());
        System.out.println("Color: " + productoDto.getColor());
        System.out.println("Costo: " + productoDto.getCosto());

        return "redirect:/productos/administrar";
    }

    @GetMapping("/productos/editar/{id}")
    public String editarProducto(@PathVariable Long id, Model model){
        model.addAttribute("productoEditar", productoService.obtenerProductoId(id));
        return "productoEditar";
    }

    @GetMapping("/productos/administrar")
    public String productoTabla(Model model) {
        model.addAttribute("ProductosTabla", productoService.listaProducto());
        return "productoTabla";
    }


    @PostMapping("/productos/editar/post/{id}")
    public String productoEditarAcción(@PathVariable Long id,
                                      @ModelAttribute("productoEditar") ProductoDto productoDto,
                                      Model model){
        Producto productoExistente = productoService.obtenerProductoId(id);
        productoExistente.setNombreProducto(productoDto.getNombreProducto());
        productoExistente.setDescripcion(productoDto.getDescripcion());
        productoExistente.setTalla(productoDto.getTalla());
        productoExistente.setColor(productoDto.getColor());
        productoExistente.setCosto(productoDto.getCosto());
        productoService.actualizarProducto(productoExistente);
        return "redirect:/productos/administrar";


    }

    @GetMapping("/productos/eliminar/{id}")
    public String productoEliminar(@PathVariable Long id){
        productoService.eliminarProducto(id);
        return "redirect:/productos/administrar";
    }
}
