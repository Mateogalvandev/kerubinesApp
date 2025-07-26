package com.kerubinesapp.mateogdev.service;

import com.kerubinesapp.mateogdev.dto.ProductoDto;
import com.kerubinesapp.mateogdev.model.Producto;
import com.kerubinesapp.mateogdev.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto guardarProducto(ProductoDto productoDto) {
        Producto producto = new Producto(
                productoDto.getNombreProducto(), productoDto.getDescripcion(),
                productoDto.getTalla(), productoDto.getColor(), productoDto.getCosto(),
                productoDto.getStock()
        );
        return productoRepository.save(producto);

    }

    @Override
    public List<Producto> listaProducto() {
        return productoRepository.findAll();
    }

    @Override
    public Producto obtenerProductoId(Long id) {
        Producto producto = productoRepository.findById(id).get();
        return producto;
    }

    @Override
        public Producto actualizarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
