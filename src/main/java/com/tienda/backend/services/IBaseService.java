package com.tienda.backend.services;

import com.tienda.backend.entities.Categoria;

import java.util.List;

public interface IBaseService<T, S> {
    List<T> listarTodos();

    T encontrarPorId(S id);

    T crear(T entity);

    void actualizar(T entity);

    void borrar(S id);
}
