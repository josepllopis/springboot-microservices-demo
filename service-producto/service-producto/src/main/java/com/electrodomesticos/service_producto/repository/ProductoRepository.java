package com.electrodomesticos.service_producto.repository;


import com.electrodomesticos.service_producto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {
}
