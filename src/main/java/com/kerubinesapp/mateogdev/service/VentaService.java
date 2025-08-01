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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
                ventaDto.getTipoDeVenta(),
                ventaDto.getPorcentaje());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Usuario usuario = usuarioRepository.findByUsername(username);
        venta.setUsuarioVenta(usuario);

        // Guardar la venta primero para obtener el ID
        Venta ventaGuardada = ventaRepository.save(venta);

        // Procesar los items de la venta
        double subtotal = 0;
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

            // Sumar al subtotal
            subtotal += itemVenta.calcularSubTotal();
        }

        // Calcular el total aplicando el porcentaje
        double porcentaje = ventaDto.getPorcentaje() != null ? ventaDto.getPorcentaje() : 0.0;
        double total = subtotal * (1 + porcentaje / 100);

        // Actualizar la venta con el total calculado
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

    /*@Override
    public Venta actualizarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }*/

    @Transactional
    public Venta actualizarVenta(Long id, VentaDto ventaDto) {
        Venta ventaExistente = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + id));

        // 1. Actualizar campos directos (incluyendo porcentaje)
        ventaExistente.setNombreCliente(ventaDto.getNombreCliente());
        ventaExistente.setNumeroCliente(ventaDto.getNumeroCliente());
        ventaExistente.setTipoDeVenta(ventaDto.getTipoDeVenta());
        ventaExistente.setModalidadVenta(ventaDto.getModalidadVenta());
        ventaExistente.setPorcentaje(ventaDto.getPorcentaje()); // <- Asegurar que se guarde

        // 2. Sincronizar ítems (tu lógica existente)
        Map<Long, ItemVentaDto> incomingItemsMap = ventaDto.getItems().stream()
                .collect(Collectors.toMap(ItemVentaDto::getProductoId, item -> item));

        Set<ItemVenta> itemsToRemove = ventaExistente.getItems().stream()
                .filter(existingItem -> !incomingItemsMap.containsKey(existingItem.getProducto().getIdProducto()))
                .collect(Collectors.toSet());

        ventaExistente.getItems().removeAll(itemsToRemove);

        for (ItemVentaDto itemDto : ventaDto.getItems()) {
            Producto producto = productoRepository.findById(itemDto.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + itemDto.getProductoId()));

            ItemVenta existingItem = ventaExistente.getItems().stream()
                    .filter(item -> item.getProducto().getIdProducto().equals(itemDto.getProductoId()))
                    .findFirst()
                    .orElse(null);

            if (existingItem != null) {
                existingItem.setCantidad(itemDto.getCantidad());
                existingItem.setPrecioUnitario(itemDto.getPrecioUnitario());
            } else {
                ItemVenta newItem = new ItemVenta(ventaExistente, producto, itemDto.getCantidad());
                newItem.setPrecioUnitario(itemDto.getPrecioUnitario());
                ventaExistente.getItems().add(newItem);
            }
        }

        // 3. Recalcular el total CON porcentaje (¡Cambio clave aquí!)
        double subtotal = ventaExistente.getItems().stream()
                .mapToDouble(ItemVenta::calcularSubTotal)
                .sum();

        double porcentaje = ventaDto.getPorcentaje() != null ? ventaDto.getPorcentaje() : 0.0;
        double total = subtotal * (1 + porcentaje / 100); // Aplicar porcentaje

        ventaExistente.setTotal(total);

        return ventaRepository.save(ventaExistente);
    }

    @Override
    public void eliminarVenta(Long id) {
        ventaRepository.deleteById(id);
    }
}
