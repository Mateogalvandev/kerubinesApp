package com.kerubinesapp.mateogdev.controller;

import com.kerubinesapp.mateogdev.dto.ItemVentaDto;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    public String crearVentaPost(@ModelAttribute("ventaEntidad") VentaDto ventaDto, BindingResult result){
        ventaDto.setDate(LocalDateTime.now());

        // Validacion de tipo de venta
        if (ventaDto.getTipoDeVenta() == null || ventaDto.getTipoDeVenta().isEmpty()) {
            ventaDto.setTipoDeVenta("NO_ESPECIFICO"); // Valor por defecto
        }

        // Validacion para no hacer ventas vacias
        if (ventaDto.getItems() == null || ventaDto.getItems().isEmpty()) {
            throw new RuntimeException("Debe agregar al menos un producto a la venta");
        }

        ventaService.guardarVenta(ventaDto);
        return "redirect:/venta/administrar";
    }



    @GetMapping("/venta/editar/{id}")
    public String editarVenta(@PathVariable Long id, Model model) {
        Venta venta = ventaService.obtenerVentaId(id);
        venta.getItems().size(); // Fuerza la carga de los items si son LAZY

        VentaDto ventaDto = new VentaDto();
        ventaDto.setIdVenta(venta.getIdVenta());
        ventaDto.setNombreCliente(venta.getNombreCliente());
        ventaDto.setNumeroCliente(venta.getNumeroCliente());
        ventaDto.setDate(venta.getDate());
        ventaDto.setTotal(venta.getTotal());
        ventaDto.setTipoDeVenta(venta.getTipoDeVenta());
        ventaDto.setUsuarioVenta(venta.getUsuarioVenta());
        ventaDto.setModalidadVenta(venta.getModalidadVenta());
        ventaDto.setPorcentaje(venta.getPorcentaje() != null ? venta.getPorcentaje() : 0.0);

        List<ItemVentaDto> itemDtos = venta.getItems().stream()
                .map(item -> new ItemVentaDto(item.getProducto().getIdProducto(), item.getCantidad(), item.getPrecioUnitario()))
                .collect(Collectors.toList());
        ventaDto.setItems(itemDtos);

        List<Producto> productos = productoRepository.findAll(); // Usar findAll() para obtener todos los productos disponibles
        model.addAttribute("productosDisponibles", productos);
        model.addAttribute("ventaEditar", ventaDto);
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
                                    BindingResult result,
                                    Model model){
        if (ventaDto.getItems() == null || ventaDto.getItems().isEmpty()) {
            model.addAttribute("error", "Debe agregar al menos un producto a la venta");
            List<Producto> productos = productoRepository.findAll();
            model.addAttribute("productosDisponibles", productos);
            return "ventaEditar";
        }
        try {
            ventaService.actualizarVenta(id, ventaDto);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            List<Producto> productos = productoRepository.findAll();
            model.addAttribute("productosDisponibles", productos);
            return "ventaEditar";
        }
        return "redirect:/venta/administrar";
    }


    @GetMapping("/venta/eliminar/{id}")
    public String ventaEliminar(@PathVariable Long id){
        ventaService.eliminarVenta(id);
        return "redirect:/venta/administrar";
    }





}
