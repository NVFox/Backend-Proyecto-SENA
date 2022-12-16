package com.tienda.backend.services.compra;

import com.tienda.backend.entities.*;
import com.tienda.backend.enums.CarritoStatus;
import com.tienda.backend.exceptions.NotEnoughMoneyException;
import com.tienda.backend.services.carrito.ICarritoService;
import com.tienda.backend.services.orden.IOrdenService;
import com.tienda.backend.services.producto.IProductoService;
import com.tienda.backend.services.usuario.IUsuarioService;
import com.tienda.backend.services.venta.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService implements ICompraService {
    private ICarritoService carritoService;
    private IProductoService productoService;
    private IOrdenService ordenService;
    private IUsuarioService usuarioService;
    private IVentaService ventaService;

    @Autowired
    public CompraService(
            ICarritoService carritoService,
            IProductoService productoService,
            IOrdenService ordenService,
            IUsuarioService usuarioService,
            IVentaService ventaService
    ) {
        this.carritoService = carritoService;
        this.productoService = productoService;
        this.ordenService = ordenService;
        this.usuarioService = usuarioService;
        this.ventaService = ventaService;
    }

    @Override
    public Carrito hacerCompra(int idCarrito) {
        Carrito carrito = carritoService.encontrarPorId(idCarrito);
        carrito.status = CarritoStatus.INACTIVE.name();

        Usuario usuario = carrito.id_usu;

        if (usuario.sal_usu < carrito.total_car) {
            throw new NotEnoughMoneyException();
        }

        List<Orden> ordenes = ordenService.encontrarProductoPorCarrito(idCarrito);

        for (Orden orden: ordenes) {
            Producto producto = orden.id_pro;
            producto.can_pro -= orden.can_ord;
            productoService.actualizar(producto);

            Usuario vendedor = producto.usu_id;
            vendedor.sal_usu += orden.total_ord;
            usuarioService.actualizar(vendedor);

            Venta venta = new Venta();
            venta.ord_id = orden;
            ventaService.crear(venta);
        }

        int nuevoSaldo = usuario.sal_usu - carrito.total_car;

        carritoService.actualizar(carrito);
        usuarioService.actualizarSaldoUsuario(usuario.id_usu, nuevoSaldo);

        return carrito;
    }

    @Override
    public List<Carrito> listarTodasCompras() {
        return carritoService.listarTodosPorEstado(CarritoStatus.INACTIVE.name());
    }

    @Override
    public List<Carrito> listarTodasComprasPorUsuario(int usuario) {
        return carritoService.listarTodosPorUsuarioYEstado(usuario, CarritoStatus.INACTIVE.name());
    }
}