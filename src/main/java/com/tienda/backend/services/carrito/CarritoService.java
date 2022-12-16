package com.tienda.backend.services.carrito;

import com.tienda.backend.entities.Carrito;
import com.tienda.backend.repositories.CarritoRepository;
import com.tienda.backend.services.BaseService;
import com.tienda.backend.services.usuario.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoService extends BaseService<Carrito, Integer> implements ICarritoService {
    private CarritoRepository carritoRepository;
    private IUsuarioService usuarioService;

    @Autowired
    public CarritoService(CarritoRepository baseRepository, IUsuarioService usuarioService) {
        super(baseRepository);
        this.carritoRepository = baseRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public Carrito crear(Carrito entity) {
        super.encontrarPorId(entity.id_car);
        return super.crear(entity);
    }

    @Override
    public List<Carrito> listarTodosPorUsuario(int usuario) {
        return carritoRepository.findAllByUsuario(usuario);
    }

    @Override
    public List<Carrito> listarTodosPorEstado(String status) {
        return carritoRepository.findAllByStatus(status);
    }

    @Override
    public List<Carrito> listarTodosPorUsuarioYEstado(int id, String status) {
        return carritoRepository.findAllByUsuarioAndStatus(id, status);
    }

    @Override
    public Carrito encontrarCarritoActivo(int id) {
        return carritoRepository.findByActiveCarrito(id)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Carrito crearPorUsuario(int usuario) {
        Carrito carrito = new Carrito();
        carrito.id_usu = usuarioService.encontrarPorId(usuario);
        return super.crear(carrito);
    }
}