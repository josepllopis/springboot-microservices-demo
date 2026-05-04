package com.electrodomesticos.service_ventas.controller;

import com.electrodomesticos.service_ventas.exception.CarritoNotActivoException;
import com.electrodomesticos.service_ventas.exception.CarritoVacioException;
import com.electrodomesticos.service_ventas.exception.VentaNotFoundException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(CarritoNotActivoException.class)
    public ResponseEntity<Map<String,Object>> carritoNotActivoException(CarritoNotActivoException ex, HttpServletRequest request){
        LinkedHashMap<String,Object> body = new LinkedHashMap<>();

        body.put("status", HttpStatus.CONFLICT.value());
        body.put("error","Conflicto");
        body.put("message",ex.getMessage());
        body.put("path",request.getRequestURI());
        body.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(VentaNotFoundException.class)
    public ResponseEntity<Map<String,Object>> ventaNotFoundException(VentaNotFoundException ex, HttpServletRequest request){
        LinkedHashMap<String,Object> body = new LinkedHashMap<>();

        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error","Not Found");
        body.put("message",ex.getMessage());
        body.put("path",request.getRequestURI());
        body.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<Map<String,Object>> productoNotFoundException(FeignException.NotFound ex, HttpServletRequest request){
        LinkedHashMap<String,Object> body = new LinkedHashMap<>();

        String mensaje = "El producto no existe";

        try {
            // Parsea el JSON del error que viene de producto-service
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(ex.contentUTF8());
            mensaje = json.get("message").asText();
        } catch (Exception ignored) {}

        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error","Not Found");
        body.put("message",mensaje);
        body.put("path",request.getRequestURI());
        body.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(CarritoVacioException.class)
    public ResponseEntity<Map<String,Object>> carritoVacioException(CarritoVacioException ex, HttpServletRequest request){
        LinkedHashMap<String,Object> body = new LinkedHashMap<>();

        body.put("status",HttpStatus.CONFLICT.value());
        body.put("error","Conflict");
        body.put("message",ex.getMessage());
        body.put("path",request.getRequestURI());
        body.put("timestamp",LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }
}
