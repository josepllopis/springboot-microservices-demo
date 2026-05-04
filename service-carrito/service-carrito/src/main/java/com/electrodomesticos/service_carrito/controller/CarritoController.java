package com.electrodomesticos.service_carrito.controller;

import com.electrodomesticos.service_carrito.dto.CarritoDTOResponse;
import com.electrodomesticos.service_carrito.service.CarritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService carritoService;

    @GetMapping
    public ResponseEntity<List<CarritoDTOResponse>> getAllCarritos(){
        return ResponseEntity.ok(carritoService.getAllCarritos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarritoDTOResponse> getCarritoById(@PathVariable Long id){
        return ResponseEntity.ok(carritoService.getCarritoById(id));
    }

    @PostMapping
    public ResponseEntity<CarritoDTOResponse> createNewCarrito(){
        return ResponseEntity.status(HttpStatus.CREATED).body(carritoService.createCarrito());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarritoDTOResponse> deleteCarrito(@PathVariable Long id){
        carritoService.deleteCarrito(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idCarrito}/agregar/{idProducto}")
    public ResponseEntity<CarritoDTOResponse> agregarProductoToCarrito(@PathVariable Long idCarrito,
                                                                       @PathVariable Long idProducto){

        return ResponseEntity.ok(carritoService.addProductoToCarrito(idCarrito,idProducto));
    }

    @PutMapping("/{idCarrito}/quitar/{idProducto}")
    public ResponseEntity<CarritoDTOResponse> quitarProductoToCarrito(@PathVariable Long idCarrito,
                                                                       @PathVariable Long idProducto){

        return ResponseEntity.ok(carritoService.deleteProductoToCarrito(idCarrito,idProducto));
    }

    @PutMapping("/cambiar_estado/{id}")
    public ResponseEntity<CarritoDTOResponse> desactivarCarrito(@PathVariable Long id, @RequestParam Boolean estado){
        return ResponseEntity.ok(carritoService.cambiarEstadoCarrito(id,estado));
    }

}
