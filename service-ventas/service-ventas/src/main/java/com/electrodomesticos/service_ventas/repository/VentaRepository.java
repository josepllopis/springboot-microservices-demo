package com.electrodomesticos.service_ventas.repository;

import com.electrodomesticos.service_ventas.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta,Long> {



}
