package com.electrodomesticos.service_carrito.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double precio;

    @ElementCollection
    private List<Long> productoIds;
    private Boolean activo;
}
