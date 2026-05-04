package com.electrodomesticos.service_producto.service;

import com.electrodomesticos.service_producto.dto.ProductoDTO;
import com.electrodomesticos.service_producto.exception.ProductoNotFoundException;
import com.electrodomesticos.service_producto.exception.ServiceFallException;
import com.electrodomesticos.service_producto.mapper.ProductoMapper;
import com.electrodomesticos.service_producto.model.Producto;
import com.electrodomesticos.service_producto.repository.ProductoRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService{

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    @Override
    public List<ProductoDTO> getAllProductos() {
        return productoRepository.findAll().stream().map(productoMapper::toDTO).toList();
    }

    @CircuitBreaker(name="service-producto", fallbackMethod = "fallbackGetProducto")
    @Retry(name = "service-producto")
    @Override
    public ProductoDTO getProducto(Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow(()->
                new ProductoNotFoundException("No existe ese producto"));

        return productoMapper.toDTO(producto);
    }

    public ProductoDTO fallbackGetProducto(Long id){
        throw new ServiceFallException("Servicio no disponible actualmente");
    }

    @Override
    public ProductoDTO createProducto(ProductoDTO productoDTO) {
       Producto producto = Producto.builder()
               .codigo(productoDTO.getCodigo())
               .nombre(productoDTO.getNombre())
               .marca(productoDTO.getMarca())
               .precio(productoDTO.getPrecio())
               .build();

       return productoMapper.toDTO(productoRepository.save(producto));
    }

    @Override
    public ProductoDTO updateProducto(Long id, ProductoDTO productoDTO) {
        Producto producto = productoRepository.findById(id).orElseThrow(()->
                new ProductoNotFoundException("No existe ese producto"));

        producto.setCodigo(productoDTO.getCodigo());
        producto.setMarca(productoDTO.getMarca());
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(producto.getPrecio());

        return productoMapper.toDTO(productoRepository.save(producto));
    }

    @Override
    public void deleteProducto(Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow(()->
                new ProductoNotFoundException("No existe ese producto"));

        productoRepository.delete(producto);
    }
}
