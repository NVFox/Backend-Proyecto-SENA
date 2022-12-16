package com.tienda.backend.services.venta;

import com.tienda.backend.entities.Venta;
import com.tienda.backend.services.IBaseService;

import java.util.List;

public interface IVentaService extends IBaseService<Venta, Integer> {
    List<Venta> listarTodasVentasPorUsuario(int usuario);
}
