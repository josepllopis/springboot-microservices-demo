package com.electrodomesticos.service_ventas.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CarritoDTOResponse {

    private List<ProductoDTO> productos;
}
