package com.tienda.backend.controllers;

import com.tienda.backend.dto.carrito.GetAllProductCarritoDto;
import com.tienda.backend.entities.Carrito;
import com.tienda.backend.entities.Orden;
import com.tienda.backend.mappers.CarritoMapper;
import com.tienda.backend.services.compra.ICompraService;
import com.tienda.backend.services.orden.IOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("compras")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class CompraController {
    private ICompraService compraService;
    private IOrdenService ordenService;
    private CarritoMapper carritoMapper;

    @Autowired
    public CompraController(ICompraService compraService, IOrdenService ordenService, CarritoMapper carritoMapper) {
        this.compraService = compraService;
        this.ordenService = ordenService;
        this.carritoMapper = carritoMapper;
    }

    @GetMapping
    public ResponseEntity<List<GetAllProductCarritoDto>> listarTodasCompras() {
        List<Carrito> carritos = compraService.listarTodasCompras();

        List<GetAllProductCarritoDto> compras = carritos
                .stream()
                .map(carrito -> {
                    List<Orden> ordenes = ordenService.encontrarProductoPorCarrito(carrito.id_car);
                    return carritoMapper.toGetAllProductCarritoDto(carrito, ordenes);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(compras);
    }

    @GetMapping("/{usuario}")
    public ResponseEntity<List<GetAllProductCarritoDto>> listarTodasComprasPorUsuario(
            @PathVariable("usuario") int usuario
    ) {
        List<Carrito> carritos = compraService.listarTodasComprasPorUsuario(usuario);

        List<GetAllProductCarritoDto> compras = carritos
                .stream()
                .map(carrito -> {
                    List<Orden> ordenes = ordenService.encontrarProductoPorCarrito(carrito.id_car);
                    return carritoMapper.toGetAllProductCarritoDto(carrito, ordenes);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(compras);
    }

    @PostMapping("/{carrito}")
    public ResponseEntity<GetAllProductCarritoDto> hacerCompra(@PathVariable("carrito") int carrito) {
        Carrito compra = compraService.hacerCompra(carrito);
        List<Orden> ordenes = ordenService.encontrarProductoPorCarrito(carrito);

        return ResponseEntity.ok()
                .body(carritoMapper.toGetAllProductCarritoDto(compra, ordenes));
    }
}