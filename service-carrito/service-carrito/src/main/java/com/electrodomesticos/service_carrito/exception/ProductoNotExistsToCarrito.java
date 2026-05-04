package com.electrodomesticos.service_carrito.exception;

public class ProductoNotExistsToCarrito extends RuntimeException {
    public ProductoNotExistsToCarrito(String message) {
        super(message);
    }
}
