package com.electrodomesticos.service_carrito.service;

import com.electrodomesticos.service_carrito.dto.CarritoDTOResponse;
import com.electrodomesticos.service_carrito.dto.ProductoDTO;
import com.electrodomesticos.service_carrito.model.Carrito;

import java.util.List;

public interface CarritoService {

    List<CarritoDTOResponse> getAllCarritos();
    CarritoDTOResponse getCarritoById(Long id);
    CarritoDTOResponse createCarrito();
    CarritoDTOResponse addProductoToCarrito(Long id, Long id_producto);
    CarritoDTOResponse deleteProductoToCarrito(Long id, Long id_producto);
    CarritoDTOResponse cambiarEstadoCarrito(Long id, Boolean estado);
    void deleteCarrito(Long id);
}
