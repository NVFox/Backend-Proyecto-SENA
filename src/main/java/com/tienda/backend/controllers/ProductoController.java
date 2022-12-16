package com.tienda.backend.controllers;

import com.tienda.backend.dto.producto.CreateProductoDto;
import com.tienda.backend.dto.producto.GetAllProductoDto;
import com.tienda.backend.dto.producto.GetProductoDto;
import com.tienda.backend.entities.Producto;
import com.tienda.backend.mappers.ProductoMapper;
import com.tienda.backend.services.producto.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("productos")
public class ProductoController {
    private IProductoService productoService;
    private ProductoMapper productoMapper;

    @Autowired
    public ProductoController(IProductoService productoService, ProductoMapper productoMapper) {
        this.productoService = productoService;
        this.productoMapper = productoMapper;
    }

    @GetMapping
    public ResponseEntity<List<GetAllProductoDto>> listarTodos() {
        return ResponseEntity.ok().body(productoMapper.toGetAllProductoDtoList(productoService.listarTodos()));
    }

    @GetMapping("/categoria/{nombre}")
    public ResponseEntity<List<GetAllProductoDto>> encontrarProductosPorCategoria(
            @PathVariable("nombre") String nombre
    ) {
        return ResponseEntity.ok()
                .body(productoMapper.toGetAllProductoDtoList(productoService.encontrarProductosPorCategoria(nombre)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetAllProductoDto> encontrarProducto(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(productoMapper.toGetAllProductoDto(productoService.encontrarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<GetProductoDto> crearProducto(@RequestBody CreateProductoDto createProductoDto) {
        Producto producto = productoMapper.toProducto(createProductoDto);
        Producto createdProducto = productoService.crear(producto);

        return ResponseEntity.status(HttpStatus.CREATED).body(productoMapper.toShowProductDto(createdProducto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarProducto(
            @RequestBody CreateProductoDto updateProductoDto,
            @PathVariable("id") int id
    ) {
        Producto foundProducto = productoService.encontrarPorId(id);
        productoMapper.updateProductoFromDto(updateProductoDto, foundProducto);
        productoService.actualizar(foundProducto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarProducto(@PathVariable("id") int id) {
        productoService.borrar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}