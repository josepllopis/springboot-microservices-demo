package com.electrodomesticos.service_producto.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class ProductoDTO {

    private String codigo;
    private String nombre;
    private String marca;
    private double precio;
}
