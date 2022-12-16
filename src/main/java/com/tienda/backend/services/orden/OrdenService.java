package com.tienda.backend.services.orden;

import com.tienda.backend.dto.orden.CreateOrdenDto;
import com.tienda.backend.dto.orden.UpdateOrdenDto;
import com.tienda.backend.entities.Carrito;
import com.tienda.backend.entities.Orden;
import com.tienda.backend.entities.Producto;
import com.tienda.backend.exceptions.NotEnoughQuantityException;
import com.tienda.backend.repositories.OrdenRepository;
import com.tienda.backend.services.BaseService;
import com.tienda.backend.services.carrito.ICarritoService;
import com.tienda.backend.services.producto.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenService extends BaseService<Orden, Integer> implements IOrdenService {
    private OrdenRepository ordenRepository;
    private IProductoService productoService;

    private ICarritoService carritoService;

    @Autowired
    public OrdenService(
            OrdenRepository baseRepository,
            IProductoService productoService,
            ICarritoService carritoService
    ) {
        super(baseRepository);
        this.ordenRepository = baseRepository;
        this.productoService = productoService;
        this.carritoService = carritoService;
    }

    @Override
    public void actualizar(Orden entity) {
        super.encontrarPorId(entity.id_ord);
        super.actualizar(entity);
    }

    @Override
    public List<Orden> encontrarProductoPorCarrito(int carrito) {
        return ordenRepository.findAllProductosFromCarrito(carrito);
    }

    @Override
    public Orden agregarOrdenAlCarrito(CreateOrdenDto createOrdenDto) {
        Orden orden = new Orden();
        Producto producto = productoService.encontrarPorId(createOrdenDto.id_pro);

        if (producto.can_pro < createOrdenDto.can_total) {
            throw new NotEnoughQuantityException();
        }

        Carrito carrito = carritoService.encontrarCarritoActivo(createOrdenDto.id_usu);

        orden.id_pro = producto;
        orden.id_car = carrito;
        orden.can_ord = createOrdenDto.can_total;
        orden.total_ord = orden.id_pro.pre_pro * orden.can_ord;

        Orden created = super.crear(orden);

        carrito.total_car += created.total_ord;
        carritoService.actualizar(carrito);

        return created;
    }

    @Override
    public Orden actualizarOrdenCarrito(UpdateOrdenDto updateOrdenDto) {
        Orden orden = this.encontrarPorId(updateOrdenDto.id_ord);
        Producto producto = orden.id_pro;
        Carrito carrito = orden.id_car;

        if (producto.can_pro < updateOrdenDto.can_total) {
            throw new NotEnoughQuantityException();
        }

        int nuevoTotalOrden = producto.pre_pro * updateOrdenDto.can_total;

        if (nuevoTotalOrden < orden.total_ord) {
            carrito.total_car -= orden.total_ord - nuevoTotalOrden;
        }

        if (nuevoTotalOrden > orden.total_ord) {
            carrito.total_car += nuevoTotalOrden - orden.total_ord;
        }

        orden.can_ord = updateOrdenDto.can_total;
        orden.total_ord = nuevoTotalOrden;

        super.actualizar(orden);
        carritoService.actualizar(carrito);

        return orden;
    }

    @Override
    public void borrarOrdenCarrito(int idOrden) {
        Orden orden = this.encontrarPorId(idOrden);
        Carrito carrito = orden.id_car;

        carrito.total_car -= orden.total_ord;
        carritoService.actualizar(carrito);

        super.borrar(idOrden);
    }
}