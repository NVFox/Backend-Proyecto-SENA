package com.tienda.backend.services.orden;

import com.tienda.backend.dto.orden.CreateOrdenDto;
import com.tienda.backend.dto.orden.UpdateOrdenDto;
import com.tienda.backend.entities.Carrito;
import com.tienda.backend.entities.Orden;
import com.tienda.backend.services.IBaseService;

import java.util.List;

public interface IOrdenService extends IBaseService<Orden, Integer> {
    List<Orden> encontrarProductoPorCarrito(int carrito);

    Orden agregarOrdenAlCarrito(CreateOrdenDto createOrdenDto);

    Orden actualizarOrdenCarrito(UpdateOrdenDto updateOrdenDto);

    void borrarOrdenCarrito(int idOrden);
}
