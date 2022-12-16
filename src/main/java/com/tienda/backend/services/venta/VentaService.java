package com.tienda.backend.services.venta;

import com.tienda.backend.entities.Venta;
import com.tienda.backend.repositories.VentaRepository;
import com.tienda.backend.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService extends BaseService<Venta, Integer> implements IVentaService {
    private VentaRepository ventaRepository;

    @Autowired
    public VentaService(VentaRepository baseRepository) {
        super(baseRepository);
        this.ventaRepository = baseRepository;
    }

    @Override
    public void actualizar(Venta entity) {
        super.encontrarPorId(entity.id_ven);
        super.actualizar(entity);
    }

    @Override
    public List<Venta> listarTodasVentasPorUsuario(int usuario) {
        return ventaRepository.findAllByUsuario(usuario);
    }
}