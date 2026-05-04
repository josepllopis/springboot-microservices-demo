package com.electrodomesticos.service_ventas.mapper;

import com.electrodomesticos.service_ventas.dto.CarritoDTORequest;
import com.electrodomesticos.service_ventas.dto.CarritoDTOResponse;
import com.electrodomesticos.service_ventas.dto.ProductoDTO;
import com.electrodomesticos.service_ventas.dto.VentaDTO;
import com.electrodomesticos.service_ventas.model.Venta;
import com.electrodomesticos.service_ventas.repository.ApiCarrito;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VentaMapper {

    private final ApiCarrito apiCarrito;

    public VentaDTO toDTO(Venta venta){

        CarritoDTORequest carritoDTORequest = apiCarrito.getCarritoById(venta.getCarritoId());
        CarritoDTOResponse carritoDTOResponse = new CarritoDTOResponse();

        carritoDTOResponse.setProductos(carritoDTORequest.getProductos());

        return VentaDTO.builder()
                .id(venta.getId())
                .fecha(venta.getFecha())
                .precioTotal(venta.getPrecioTotal())
                .carritoDTO(carritoDTOResponse)
                .build();
    }

}
