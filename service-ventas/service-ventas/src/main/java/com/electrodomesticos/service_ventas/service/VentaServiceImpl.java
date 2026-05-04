package com.electrodomesticos.service_ventas.service;

import com.electrodomesticos.service_ventas.dto.CarritoDTORequest;
import com.electrodomesticos.service_ventas.dto.VentaDTO;
import com.electrodomesticos.service_ventas.exception.CarritoNotActivoException;
import com.electrodomesticos.service_ventas.exception.CarritoVacioException;
import com.electrodomesticos.service_ventas.exception.VentaNotFoundException;
import com.electrodomesticos.service_ventas.mapper.VentaMapper;
import com.electrodomesticos.service_ventas.model.Venta;
import com.electrodomesticos.service_ventas.repository.ApiCarrito;
import com.electrodomesticos.service_ventas.repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService{

    private final VentaRepository ventaRepository;
    private final VentaMapper ventaMapper;
    private final ApiCarrito apiCarrito;

    @Override
    public List<VentaDTO> getAllVentas() {
        return ventaRepository.findAll().stream().map(ventaMapper::toDTO).toList();
    }

    @Override
    public VentaDTO getVentaById(Long id) {
        return ventaMapper.toDTO(ventaRepository.findById(id).orElseThrow(()->
                new VentaNotFoundException("No existe esa venta")));
    }

    @Override
    public VentaDTO createVenta(Long carritoId) {
        CarritoDTORequest carrito = apiCarrito.getCarritoById(carritoId);

        if(!carrito.isActivo()){
            throw new CarritoNotActivoException("El carrito no está activo");
        }

        if(carrito.getProductos().isEmpty()){
            throw new CarritoVacioException("El carrito esta vacío");
        }

        Venta venta = new Venta();

        venta.setCarritoId(carritoId);
        venta.setFecha(LocalDate.now());
        venta.setPrecioTotal(carrito.getPrecio());

        apiCarrito.cambiarEstadoCarrito(carritoId,false);
        return ventaMapper.toDTO(ventaRepository.save(venta));
    }

    @Override
    public void deleteVenta(Long id) {

        Venta ventaEliminar = ventaRepository.findById(id).orElseThrow(()->
                new VentaNotFoundException("No existe esa venta"));

        apiCarrito.cambiarEstadoCarrito(ventaEliminar.getCarritoId(), true);


        ventaRepository.delete(ventaEliminar);
    }
}
