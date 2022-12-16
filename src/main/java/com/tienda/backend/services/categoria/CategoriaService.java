package com.tienda.backend.services.categoria;

import com.tienda.backend.entities.Categoria;
import com.tienda.backend.repositories.CategoriaRepository;
import com.tienda.backend.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService extends BaseService<Categoria, String> implements ICategoriaService {
    @Autowired
    public CategoriaService(CategoriaRepository baseRepository) {
        super(baseRepository);
    }

    @Override
    public void actualizar(Categoria entity) {
        super.encontrarPorId(entity.nom_cate);
        super.actualizar(entity);
    }
}