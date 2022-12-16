package com.tienda.backend.services.compra;

import com.tienda.backend.entities.Carrito;

import java.util.List;

public interface ICompraService {
    Carrito hacerCompra(int idCarrito);

    List<Carrito> listarTodasCompras();

    List<Carrito> listarTodasComprasPorUsuario(int usuario);
}
