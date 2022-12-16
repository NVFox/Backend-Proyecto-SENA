package com.tienda.backend.controllers;

import com.tienda.backend.dto.carrito.GetAllProductCarritoDto;
import com.tienda.backend.dto.carrito.GetCarritoDto;
import com.tienda.backend.dto.orden.CreateOrdenDto;
import com.tienda.backend.dto.orden.GetAllOrdenDto;
import com.tienda.backend.entities.Carrito;
import com.tienda.backend.entities.Orden;
import com.tienda.backend.mappers.CarritoMapper;
import com.tienda.backend.mappers.OrdenMapper;
import com.tienda.backend.services.carrito.ICarritoService;
import com.tienda.backend.services.orden.IOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("carritos")
@CrossOrigin("*")
public class CarritoController {
    private ICarritoService carritoService;
    private IOrdenService ordenService;
    private CarritoMapper carritoMapper;
    private OrdenMapper ordenMapper;

    @Autowired
    public CarritoController(
            ICarritoService carritoService,
            IOrdenService ordenService,
            CarritoMapper carritoMapper,
            OrdenMapper ordenMapper
    ) {
        this.carritoService = carritoService;
        this.ordenService = ordenService;
        this.carritoMapper = carritoMapper;
        this.ordenMapper = ordenMapper;
    }

    @GetMapping
    public ResponseEntity<List<GetCarritoDto>> listarTodosCarritos() {
        return ResponseEntity.ok()
                .body(carritoMapper.toGetCarritoDtoList(carritoService.listarTodos()));
    }

    @GetMapping("/{usuario}")
    public ResponseEntity<GetAllProductCarritoDto> encontrarCarritoActivo(@PathVariable("usuario") int usuario) {
        Carrito carrito = carritoService.encontrarCarritoActivo(usuario);
        List<Orden> productos = ordenService.encontrarProductoPorCarrito(carrito.id_car);

        return ResponseEntity.ok()
                .body(carritoMapper.toGetAllProductCarritoDto(carrito, productos));
    }

    @PostMapping("/{usuario}")
    public ResponseEntity<GetCarritoDto> crearCarrito(@PathVariable("usuario") int usuario) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(carritoMapper.toGetCarritoDto(carritoService.crearPorUsuario(usuario)));
    }

    @DeleteMapping("/{usuario}")
    public ResponseEntity<Void> borrarCarrito(@PathVariable("usuario") int usuario) {
        carritoService.borrar(usuario);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}