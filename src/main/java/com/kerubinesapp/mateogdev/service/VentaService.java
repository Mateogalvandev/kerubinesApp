package com.kerubinesapp.mateogdev.service;

import com.kerubinesapp.mateogdev.dto.ItemVentaDto;
import com.kerubinesapp.mateogdev.dto.VentaDto;
import com.kerubinesapp.mateogdev.model.ItemVenta;
import com.kerubinesapp.mateogdev.model.Producto;
import com.kerubinesapp.mateogdev.model.Usuario;
import com.kerubinesapp.mateogdev.model.Venta;
import com.kerubinesapp.mateogdev.repository.ItemVentaRepository;
import com.kerubinesapp.mateogdev.repository.ProductoRepository;
import com.kerubinesapp.mateogdev.repository.UsuarioRepository;
import com.kerubinesapp.mateogdev.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private ItemVentaRepository itemVentaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Venta guardarVenta(VentaDto ventaDto) {
        Venta venta = new Venta(
                ventaDto.getIdVenta(),
                ventaDto.getNombreCliente(),
                ventaDto.getNumeroCliente(),
                ventaDto.getDate(),
                ventaDto.getTotal(),
                ventaDto.getModalidadVenta(),
                ventaDto.getTipoDeVenta());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Usuario usuario = usuarioRepository.findByUsername(username);
        venta.setUsuarioVenta(usuario);

        // Guardar la venta primero para obtener el ID
        Venta ventaGuardada = ventaRepository.save(venta);

        // Procesar los items de la venta
        double total = 0;
        for (ItemVentaDto itemDto : ventaDto.getItems()){
            Producto producto = productoRepository.findById(itemDto.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            // Validar stock
            if (producto.getStock() < itemDto.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para " + producto.getNombreProducto());
            }

            // Crear item de venta
            ItemVenta itemVenta = new ItemVenta();
            itemVenta.setVenta(ventaGuardada);
            itemVenta.setProducto(producto);
            itemVenta.setCantidad(itemDto.getCantidad());
            itemVenta.setPrecioUnitario(producto.getCosto());

            // Actualizar stock
            producto.setStock(producto.getStock() - itemDto.getCantidad());
            productoRepository.save(producto);

            // Guardar Item
            itemVentaRepository.save(itemVenta);

            // Sumar el total
            total += itemVenta.calcularSubTotal();
        }



        ventaGuardada.setTotal(total);
        return ventaRepository.save(ventaGuardada);
    }

    @Override
    public List<Venta> listaVenta() {
        return ventaRepository.findAllWithItemsAndProducts();
    }

    @Override
    public Venta obtenerVentaId(Long id) {
        Venta venta = ventaRepository.findById(id).get();
        return venta;
    }

    @Override
    public Venta actualizarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public void eliminarVenta(Long id) {
        ventaRepository.deleteById(id);
    }
}
