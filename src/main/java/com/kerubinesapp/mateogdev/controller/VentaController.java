package com.kerubinesapp.mateogdev.controller;

import com.kerubinesapp.mateogdev.dto.VentaDto;
import com.kerubinesapp.mateogdev.model.Producto;
import com.kerubinesapp.mateogdev.model.Usuario;
import com.kerubinesapp.mateogdev.model.Venta;
import com.kerubinesapp.mateogdev.repository.ProductoRepository;
import com.kerubinesapp.mateogdev.repository.UsuarioRepository;
import com.kerubinesapp.mateogdev.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @ModelAttribute("venta")
    public VentaDto ventaDto() {
        return new VentaDto();
    }

    @GetMapping("/venta/crear")
    public String crearVentas(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Usuario usuario = usuarioRepository.findByUsername(username);

        List<Producto> productos = productoRepository.findByStockGreaterThanEqual(1);

        model.addAttribute("usuarioLogueado", usuario);
        model.addAttribute("productosDisponibles", productos);
        model.addAttribute("ventaEntidad", new VentaDto());
        return "ventaCrear";
    }

    @PostMapping("/venta/crear/post")
    public String crearVentaPost(@ModelAttribute("ventaEntidad") VentaDto ventaDto){
        ventaDto.setDate(LocalDateTime.now());

        if (ventaDto.getTipoDeVenta() == null || ventaDto.getTipoDeVenta().isEmpty()) {
            ventaDto.setTipoDeVenta("NO_ESPECIFICO"); // Valor por defecto
        }

        ventaService.guardarVenta(ventaDto);
        return "redirect:/venta/administrar";
    }


    @GetMapping("/venta/editar/{id}")
    public String editarVenta(@PathVariable Long id, Model model){
        model.addAttribute("ventaEditar", ventaService.obtenerVentaId(id));
        return "ventaEditar";
    }

    @GetMapping("/venta/administrar")
    public String ventaTabla(Model model){
        model.addAttribute("ventaTabla",ventaService.listaVenta());
        return "ventaTabla";
    }

    @PostMapping("/venta/editar/post/{id}")
    public String ventaEditarAccion(@PathVariable Long id,
                                    @ModelAttribute("ventaEditar") VentaDto ventaDto,
                                    Model model){
        Venta ventaExistente = ventaService.obtenerVentaId(id);
        ventaExistente.setTipoDeVenta(ventaDto.getTipoDeVenta());
        ventaExistente.setDate(ventaDto.getDate());
        ventaExistente.setTotal(ventaDto.getTotal());
        ventaExistente.setProductoLista(ventaDto.getProductoLista());
        ventaService.actualizarVenta(ventaExistente);
        return "redirect:/venta/administrar";
    }


    @GetMapping("/venta/eliminar/{id}")
    public String ventaEliminar(@PathVariable Long id){
        ventaService.eliminarVenta(id);
        return "redirect:/venta/administrar";
    }





}
