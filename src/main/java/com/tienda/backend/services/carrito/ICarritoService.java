package com.tienda.backend.services.carrito;

import com.tienda.backend.entities.Carrito;
import com.tienda.backend.services.IBaseService;

import java.util.List;

public interface ICarritoService extends IBaseService<Carrito, Integer> {
    List<Carrito> listarTodosPorUsuario(int usuario);

    List<Carrito> listarTodosPorEstado(String status);

    List<Carrito> listarTodosPorUsuarioYEstado(int id, String status);

    Carrito encontrarCarritoActivo(int id);

    Carrito crearPorUsuario(int usuario);
}
