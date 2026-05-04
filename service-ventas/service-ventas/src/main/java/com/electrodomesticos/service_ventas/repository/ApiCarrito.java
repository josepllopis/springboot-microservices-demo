package com.electrodomesticos.service_ventas.repository;

import com.electrodomesticos.service_ventas.dto.CarritoDTORequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="service-carrito")
public interface ApiCarrito {

    @GetMapping("/api/v1/carrito/{id}")
    CarritoDTORequest getCarritoById(@PathVariable Long id);

    @PutMapping("/api/v1/carrito/cambiar_estado/{id}")
    void cambiarEstadoCarrito(@PathVariable Long id, @RequestParam Boolean estado);
}
