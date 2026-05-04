package com.electrodomesticos.service_carrito.mapper;

import com.electrodomesticos.service_carrito.dto.CarritoDTOResponse;
import com.electrodomesticos.service_carrito.dto.ProductoDTO;
import com.electrodomesticos.service_carrito.model.Carrito;
import com.electrodomesticos.service_carrito.repository.ApiProducto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CarritoMapper {

    private final ApiProducto apiProducto;

    public CarritoDTOResponse toDTO(Carrito carrito){

        Map<String,ProductoDTO> productos = new HashMap<>();

        for(Long idProducto: carrito.getProductoIds()){
            ProductoDTO productoDTO = apiProducto.getProductoById(idProducto);
            if(productos.containsKey(productoDTO.getCodigo())){
                productos.get(productoDTO.getCodigo()).setCantidad(
                        productos.get(productoDTO.getCodigo()).getCantidad() + 1
                );

            }else{
                productoDTO.setCantidad(1);
                productos.put(productoDTO.getCodigo(),productoDTO);
            }

        }

        List<ProductoDTO> productoDTOS = new ArrayList<>(productos.values());

        return CarritoDTOResponse.builder()
                .idCarrito(carrito.getId())
                .activo(carrito.getActivo())
                .precio(carrito.getPrecio())
                .productos(productoDTOS)
                .build();
    }
}
