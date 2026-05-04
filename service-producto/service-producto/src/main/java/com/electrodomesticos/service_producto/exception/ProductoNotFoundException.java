package com.electrodomesticos.service_producto.exception;

public class ProductoNotFoundException extends RuntimeException {
    public ProductoNotFoundException(String message) {
        super(message);
    }
}
