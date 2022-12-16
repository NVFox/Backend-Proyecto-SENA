package com.tienda.backend.services.producto;

import com.tienda.backend.entities.Producto;
import com.tienda.backend.repositories.ProductoRepository;
import com.tienda.backend.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService extends BaseService<Producto, Integer> implements IProductoService {
    private ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository baseRepository) {
        super(baseRepository);
        this.productoRepository = baseRepository;
    }

    @Override
    public void actualizar(Producto entity) {
        super.encontrarPorId(entity.id_pro);
        super.actualizar(entity);
    }

    @Override
    public List<Producto> encontrarProductosPorCategoria(String nombreCategoria) {
        return productoRepository.findByCategoria(nombreCategoria);
    }
}