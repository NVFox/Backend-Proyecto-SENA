package com.tienda.backend.controllers;

import com.tienda.backend.dto.orden.CreateOrdenDto;
import com.tienda.backend.dto.orden.GetAllOrdenDto;
import com.tienda.backend.dto.orden.UpdateOrdenDto;
import com.tienda.backend.mappers.OrdenMapper;
import com.tienda.backend.services.orden.IOrdenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ordenes")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class OrdenController {
    private IOrdenService ordenService;
    private OrdenMapper ordenMapper;

    public OrdenController(IOrdenService ordenService, OrdenMapper ordenMapper) {
        this.ordenService = ordenService;
        this.ordenMapper = ordenMapper;
    }

    @PostMapping
    public ResponseEntity<GetAllOrdenDto> agregarOrdenCarrito(@RequestBody CreateOrdenDto createOrdenDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ordenMapper.toGetAllOrdenDto(ordenService.agregarOrdenAlCarrito(createOrdenDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetAllOrdenDto> actualizarOrdenCarrito(
            @PathVariable("id") int id,
            @RequestBody UpdateOrdenDto updateOrdenDto
    ) {
        updateOrdenDto.id_ord = id;

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ordenMapper.toGetAllOrdenDto(ordenService.actualizarOrdenCarrito(updateOrdenDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarOrdenCarrito(@PathVariable("id") int id) {
        ordenService.borrarOrdenCarrito(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(null);
    }

}
