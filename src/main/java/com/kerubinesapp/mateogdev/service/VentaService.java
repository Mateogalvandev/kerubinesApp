package com.kerubinesapp.mateogdev.service;

import com.kerubinesapp.mateogdev.dto.VentaDto;
import com.kerubinesapp.mateogdev.model.Venta;
import com.kerubinesapp.mateogdev.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService implements IVentaService {

    @Autowired
    VentaRepository ventaRepository;

    @Override
    public Venta guardarVenta(VentaDto ventaDto) {
        Venta venta = new Venta(
                ventaDto.getIdVenta(),
                ventaDto.getNombreCliente(),
                ventaDto.getNumeroCliente(),
                ventaDto.getDate(),
                ventaDto.getTotal(),
                ventaDto.getTipoDeVenta(),
                ventaDto.getUsuarioVenta(),
                ventaDto.getProductoLista()
        );
        return ventaRepository.save(venta);
    }

    @Override
    public List<Venta> listaVenta() {
        return ventaRepository.findAll();
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
