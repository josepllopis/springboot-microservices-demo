package com.electrodomesticos.service_carrito.service;

import com.electrodomesticos.service_carrito.dto.CarritoDTOResponse;
import com.electrodomesticos.service_carrito.dto.ProductoDTO;
import com.electrodomesticos.service_carrito.exception.CarritoNotExistsException;
import com.electrodomesticos.service_carrito.exception.ProductoNotExistsToCarrito;
import com.electrodomesticos.service_carrito.mapper.CarritoMapper;
import com.electrodomesticos.service_carrito.model.Carrito;
import com.electrodomesticos.service_carrito.repository.ApiProducto;
import com.electrodomesticos.service_carrito.repository.CarritoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarritoServiceImpl implements CarritoService{

    private final CarritoRepository carritoRepository;
    private final CarritoMapper carritoMapper;
    private final ApiProducto apiProducto;

    @Override
    public List<CarritoDTOResponse> getAllCarritos() {
        return carritoRepository.findAll().stream().map(carritoMapper::toDTO).toList();
    }

    @Override
    public CarritoDTOResponse getCarritoById(Long id) {
        return carritoMapper.toDTO(carritoRepository.findById(id).orElseThrow(()->
                new CarritoNotExistsException("No existe ese carrito")));
    }

    @Override
    public CarritoDTOResponse createCarrito() {
        Carrito carrito = Carrito.builder()
                .activo(true)
                .precio(0)
                .productoIds(new ArrayList<>())
                .build();

        return carritoMapper.toDTO(carritoRepository.save(carrito));
    }

    @Override
    public CarritoDTOResponse addProductoToCarrito(Long id, Long id_producto) {
        Carrito carrito = carritoRepository.findById(id).orElseThrow(()->
                new CarritoNotExistsException("No existe este carrito"));

        ProductoDTO producto = apiProducto.getProductoById(id_producto);

        if(!carrito.getActivo()){
            throw new CarritoNotExistsException("No se pueden añadir productos a un carrito inactivo");
        }

        carrito.getProductoIds().add(id_producto);

        carrito.setPrecio(carrito.getPrecio()+producto.getPrecio());

        return carritoMapper.toDTO(carritoRepository.save(carrito));
    }

    @Override
    public CarritoDTOResponse deleteProductoToCarrito(Long id, Long id_producto) {
        Carrito carrito = carritoRepository.findById(id).orElseThrow(()->
                new CarritoNotExistsException("No existe este carrito"));

        if(!carrito.getActivo()){
            throw new CarritoNotExistsException("No se pueden borrar productos de un carrito inactivo");
        }

        ProductoDTO producto = apiProducto.getProductoById(id_producto);

        if(!carrito.getProductoIds().contains(Long.valueOf(id_producto))){
            throw new ProductoNotExistsToCarrito("No está este producto en el carrito");
        }



        Iterator<Long> it = carrito.getProductoIds().iterator();


        while (it.hasNext()){
            if(it.next().equals(id_producto)){
                it.remove();
                break;
            }
        }

        carrito.setPrecio(carrito.getPrecio()-producto.getPrecio());

        return carritoMapper.toDTO(carritoRepository.save(carrito));

    }

    @Override
    public CarritoDTOResponse cambiarEstadoCarrito(Long id, Boolean estado) {

        Carrito carrito = carritoRepository.findById(id).orElseThrow(()->
                new CarritoNotExistsException("No existe ese carrito"));

        carrito.setActivo(estado);
        return carritoMapper.toDTO(carritoRepository.save(carrito));
    }

    @Override
    public void deleteCarrito(Long id) {
        Carrito carrito = carritoRepository.findById(id).orElseThrow(()->
                new CarritoNotExistsException("No existe ese carrito"));

        if(!carrito.getActivo()){
            throw new CarritoNotExistsException("No se puede borrar un carrito inactivo");
        }

        carritoRepository.delete(carrito);
    }
}
