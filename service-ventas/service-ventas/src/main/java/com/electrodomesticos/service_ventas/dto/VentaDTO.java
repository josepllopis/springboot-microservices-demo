package com.electrodomesticos.service_ventas.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class VentaDTO {

    private Long id;
    private LocalDate fecha;
    private CarritoDTOResponse carritoDTO;
    private Double precioTotal;
}
