package com.electrodomesticos.service_carrito.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class CarritoDTOResponse {

    private Long idCarrito;
    private List<ProductoDTO> productos;
    private double precio;
    private boolean activo;
}
