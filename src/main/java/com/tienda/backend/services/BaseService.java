package com.tienda.backend.services;

import com.tienda.backend.exceptions.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class BaseService<T, S> implements IBaseService<T, S> {
    protected JpaRepository<T, S> baseRepository;

    public BaseService(JpaRepository<T, S> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public List<T> listarTodos() {
        return baseRepository.findAll();
    }

    @Override
    public T encontrarPorId(S id) {
        return baseRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public T crear(T entity) {
        return baseRepository.save(entity);
    }

    @Override
    public void actualizar(T entity) {
        baseRepository.save(entity);
    }

    @Override
    public void borrar(S id) {
        encontrarPorId(id);
        baseRepository.deleteById(id);
    }
}
