package com.tienda.backend.services.producto;

import com.tienda.backend.entities.Producto;
import com.tienda.backend.services.IBaseService;

import java.util.List;

public interface IProductoService extends IBaseService<Producto, Integer> {
    List<Producto> encontrarProductosPorCategoria(String nombreCategoria);
}
