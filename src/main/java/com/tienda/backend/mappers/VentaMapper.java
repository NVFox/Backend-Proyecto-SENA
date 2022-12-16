package com.tienda.backend.mappers;

import com.tienda.backend.dto.venta.GetAllVentaDto;
import com.tienda.backend.entities.Venta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = OrdenMapper.class, componentModel = "spring")
public interface VentaMapper {
    List<GetAllVentaDto> toGetAllVentaDtoList(List<Venta> ventas);

    @Mapping(source = "fec_ven", target = "fec_ven", dateFormat = "dd/MM/yyyy")
    GetAllVentaDto toGetAllVentaDto(Venta venta);
}
