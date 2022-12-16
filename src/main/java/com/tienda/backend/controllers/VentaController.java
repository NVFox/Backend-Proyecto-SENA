package com.tienda.backend.controllers;

import com.tienda.backend.dto.venta.GetAllVentaDto;
import com.tienda.backend.mappers.VentaMapper;
import com.tienda.backend.services.venta.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ventas")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class VentaController {
    private IVentaService ventaService;
    private VentaMapper ventaMapper;

    @Autowired
    public VentaController(IVentaService ventaService, VentaMapper ventaMapper) {
        this.ventaService = ventaService;
        this.ventaMapper = ventaMapper;
    }

    @GetMapping
    public ResponseEntity<List<GetAllVentaDto>> listarTodasVentas() {
        return ResponseEntity.ok()
                .body(ventaMapper.toGetAllVentaDtoList(ventaService.listarTodos()));
    }

    @GetMapping("/{usuario}")
    public ResponseEntity<List<GetAllVentaDto>> listarTodasVentasPorUsuario(@PathVariable("usuario") int usuario) {
        return ResponseEntity.ok()
                .body(ventaMapper.toGetAllVentaDtoList(ventaService.listarTodasVentasPorUsuario(usuario)));
    }
}
