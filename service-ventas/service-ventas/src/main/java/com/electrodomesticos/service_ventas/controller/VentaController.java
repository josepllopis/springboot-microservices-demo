package com.electrodomesticos.service_ventas.controller;

import com.electrodomesticos.service_ventas.dto.VentaDTO;
import com.electrodomesticos.service_ventas.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/venta")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> getAllVentas(){
        return ResponseEntity.ok(ventaService.getAllVentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> getVentaById(@PathVariable Long id){
        return ResponseEntity.ok(ventaService.getVentaById(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<VentaDTO> createVenta(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaService.createVenta(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VentaDTO> deleteVenta(@PathVariable Long id){

        ventaService.deleteVenta(id);

        return ResponseEntity.noContent().build();
    }

}
