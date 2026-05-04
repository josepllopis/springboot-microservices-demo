package com.electrodomesticos.service_carrito.repository;

import com.electrodomesticos.service_carrito.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-producto")
public interface ApiProducto {


    @GetMapping("/api/v1/producto/{id}")
    ProductoDTO getProductoById(@PathVariable Long id);

}
