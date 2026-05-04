package com.electrodomesticos.service_producto.service;

import com.electrodomesticos.service_producto.dto.ProductoDTO;

import java.util.List;

public interface ProductoService {

    List<ProductoDTO> getAllProductos();
    ProductoDTO getProducto(Long id);
    ProductoDTO createProducto(ProductoDTO productoDTO);
    ProductoDTO updateProducto(Long id, ProductoDTO productoDTO);
    void deleteProducto(Long id);
}
