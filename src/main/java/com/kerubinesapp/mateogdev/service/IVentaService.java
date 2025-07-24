package com.kerubinesapp.mateogdev.service;

import com.kerubinesapp.mateogdev.dto.ProductoDto;
import com.kerubinesapp.mateogdev.dto.VentaDto;
import com.kerubinesapp.mateogdev.model.Producto;
import com.kerubinesapp.mateogdev.model.Venta;

import java.util.List;

public interface IVentaService {

    public Venta guardarVenta(VentaDto ventaDto);

    public List<Venta> listaVenta();

    public Venta obtenerVentaId(Long id);

    public Venta actualizarVenta(Venta venta);

    public void eliminarVenta(Long id);
}
