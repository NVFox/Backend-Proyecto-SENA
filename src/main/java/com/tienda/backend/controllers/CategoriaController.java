package com.tienda.backend.controllers;

import com.tienda.backend.dto.categoria.CreateCategoriaDto;
import com.tienda.backend.dto.categoria.GetCategoriaDto;
import com.tienda.backend.entities.Categoria;
import com.tienda.backend.mappers.CategoriaMapper;
import com.tienda.backend.services.categoria.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categorias")
@CrossOrigin("*")
public class CategoriaController {
    private ICategoriaService categoriaService;

    private CategoriaMapper categoriaMapper;

    @Autowired
    public CategoriaController(ICategoriaService categoriaService, CategoriaMapper categoriaMapper) {
        this.categoriaService = categoriaService;
        this.categoriaMapper = categoriaMapper;
    }

    @GetMapping
    public ResponseEntity<List<GetCategoriaDto>> listarTodos() {
        return ResponseEntity.ok()
                .body(categoriaMapper.toGetCategoryDtoList(categoriaService.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCategoriaDto> encontrarCategoria(@PathVariable("id") String id) {
        return ResponseEntity.ok()
                .body(categoriaMapper.toGetCategoriaDto(categoriaService.encontrarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<GetCategoriaDto> crearCategoria(@RequestBody CreateCategoriaDto createCategoriaDto) {
        Categoria categoria = categoriaMapper.toCategoria(createCategoriaDto);
        Categoria createdCategoria = categoriaService.crear(categoria);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoriaMapper.toGetCategoriaDto(createdCategoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarCategoria(
            @RequestBody CreateCategoriaDto updateCategoriaDto,
            @PathVariable("id") String id
    ) {
        Categoria found = categoriaService.encontrarPorId(id);
        categoriaMapper.updateCategoriaFromDto(updateCategoriaDto, found);
        categoriaService.actualizar(found);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarCategoria(@PathVariable("id") String id) {
        categoriaService.borrar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
