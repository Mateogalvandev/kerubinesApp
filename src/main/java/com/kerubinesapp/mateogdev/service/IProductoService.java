package com.kerubinesapp.mateogdev.service;

import com.kerubinesapp.mateogdev.dto.ProductoDto;
import com.kerubinesapp.mateogdev.model.Producto;

import java.util.List;

public interface IProductoService {

    public Producto guardarProducto(ProductoDto productoDto);

    public List<Producto> listaProducto();

    public Producto obtenerProductoId(Long id);

    public Producto actualizarProducto(Producto producto);

    public void eliminarProducto(Long id);
}
