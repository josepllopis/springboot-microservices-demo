package com.electrodomesticos.service_producto.controller;

import com.electrodomesticos.service_producto.exception.ProductoNotFoundException;
import com.electrodomesticos.service_producto.exception.ServiceFallException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<Map<String,Object>> productoNotFoundException(ProductoNotFoundException ex, HttpServletRequest request){
        LinkedHashMap<String,Object> body = new LinkedHashMap<>();

        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error","Not Found");
        body.put("message",ex.getMessage());
        body.put("path",request.getRequestURI());
        body.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(ServiceFallException.class)
    public ResponseEntity<Map<String,Object>> serviceFallException(ServiceFallException ex){
        LinkedHashMap<String,Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.SERVICE_UNAVAILABLE);
        body.put("errors", ex.getMessage());
        body.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
    }

}
