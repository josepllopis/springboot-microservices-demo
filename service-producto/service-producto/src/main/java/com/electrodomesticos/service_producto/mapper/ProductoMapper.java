package com.electrodomesticos.service_producto.mapper;

import com.electrodomesticos.service_producto.dto.ProductoDTO;
import com.electrodomesticos.service_producto.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoDTO toDTO(Producto producto){
        return ProductoDTO.builder()
                .codigo(producto.getCodigo())
                .nombre(producto.getNombre())
                .marca(producto.getMarca())
                .precio(producto.getPrecio())
                .build();
    }

}
