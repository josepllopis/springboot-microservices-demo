package com.electrodomesticos.service_ventas.service;

import com.electrodomesticos.service_ventas.dto.VentaDTO;

import java.util.List;

public interface VentaService {

    List<VentaDTO> getAllVentas();
    VentaDTO getVentaById(Long id);
    VentaDTO createVenta(Long carritoId);
    void deleteVenta(Long id);
}
