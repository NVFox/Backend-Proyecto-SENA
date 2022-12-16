package com.tienda.backend.mappers;

import com.tienda.backend.dto.producto.CreateProductoDto;
import com.tienda.backend.dto.producto.GetAllProductoDto;
import com.tienda.backend.dto.producto.GetProductoDto;
import com.tienda.backend.entities.Producto;
import org.mapstruct.*;

import java.util.List;

@Mapper(uses = { CategoriaMapper.class, UsuarioMapper.class  }, componentModel = "spring")
public interface ProductoMapper {
    List<GetAllProductoDto> toGetAllProductoDtoList(List<Producto> productos);

    @Mapping(target = "id_pro", ignore = true)
    @Mapping(target = "nom_cate", qualifiedByName = "stringToCategoria")
    @Mapping(target = "usu_id", qualifiedByName = "intToUsuario")
    Producto toProducto(CreateProductoDto createProductoDto);

    GetProductoDto toShowProductDto(Producto producto);

    @Mapping(source = "nom_cate", target = "pro_cate", qualifiedByName = "categoriaToString")
    GetAllProductoDto toGetAllProductoDto(Producto producto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id_pro", ignore = true)
    @Mapping(target = "nom_cate", qualifiedByName = "stringToCategoria")
    @Mapping(target = "usu_id", qualifiedByName = "intToUsuario")
    void updateProductoFromDto(CreateProductoDto dto, @MappingTarget Producto producto);
}