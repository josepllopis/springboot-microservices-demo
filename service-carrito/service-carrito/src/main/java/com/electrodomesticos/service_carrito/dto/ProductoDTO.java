package com.electrodomesticos.service_carrito.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
@EqualsAndHashCode
public class ProductoDTO {

    private String codigo;
    private String nombre;
    private String marca;
    private Integer cantidad;
    private double precio;
}
