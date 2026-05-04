package com.electrodomesticos.service_producto.controller;

import com.electrodomesticos.service_producto.dto.ProductoDTO;
import com.electrodomesticos.service_producto.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/producto")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @Value("${server.port}")
    private int puerto;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> getAllProductos(){
        System.out.println("Soy el puerto: "+puerto);
        return ResponseEntity.ok(productoService.getAllProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getProductoById(@PathVariable Long id){
        return ResponseEntity.ok(productoService.getProducto(id));
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> createProducto(@RequestBody ProductoDTO productoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.createProducto(productoDTO));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> updateProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO){
        return ResponseEntity.ok(productoService.updateProducto(id,productoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id){
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }
}
