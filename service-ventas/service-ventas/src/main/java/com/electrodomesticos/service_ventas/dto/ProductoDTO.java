package com.electrodomesticos.service_ventas.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductoDTO {

    private String codigo;
    private String nombre;
    private String marca;
    private Integer cantidad;
    private double precio;
}
