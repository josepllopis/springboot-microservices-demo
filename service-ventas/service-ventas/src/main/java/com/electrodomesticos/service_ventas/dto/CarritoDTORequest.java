package com.electrodomesticos.service_ventas.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CarritoDTORequest {

    private List<ProductoDTO> productos;
    private double precio;
    private boolean activo;

}
