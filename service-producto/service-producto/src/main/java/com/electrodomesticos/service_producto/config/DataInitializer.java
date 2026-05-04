package com.electrodomesticos.service_producto.config;

import com.electrodomesticos.service_producto.model.Producto;
import com.electrodomesticos.service_producto.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {


    private final ProductoRepository productoRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (productoRepository.count() == 0) {
            productoRepository.saveAll(getProductos());
            System.out.println("✓ 50 productos creados correctamente");
        }
    }

    private List<Producto> getProductos() {
        return List.of(
                Producto.builder().codigo("7839201").nombre("Lavadora").marca("Siemens").precio(450.0).build(),
                Producto.builder().codigo("7839202").nombre("Secadora").marca("Bosch").precio(380.0).build(),
                Producto.builder().codigo("7839203").nombre("Lavavajillas").marca("Siemens").precio(200.0).build(),
                Producto.builder().codigo("7839204").nombre("Frigorífico").marca("Samsung").precio(650.0).build(),
                Producto.builder().codigo("7839205").nombre("Microondas").marca("LG").precio(120.0).build(),
                Producto.builder().codigo("7839206").nombre("Horno").marca("Bosch").precio(350.0).build(),
                Producto.builder().codigo("7839207").nombre("Vitrocerámica").marca("Siemens").precio(280.0).build(),
                Producto.builder().codigo("7839208").nombre("Campana extractora").marca("Balay").precio(180.0).build(),
                Producto.builder().codigo("7839209").nombre("Aire acondicionado").marca("Daikin").precio(750.0).build(),
                Producto.builder().codigo("7839210").nombre("Calefactor").marca("Rowenta").precio(90.0).build(),
                Producto.builder().codigo("7839211").nombre("Aspiradora").marca("Dyson").precio(320.0).build(),
                Producto.builder().codigo("7839212").nombre("Robot aspirador").marca("Roomba").precio(450.0).build(),
                Producto.builder().codigo("7839213").nombre("Plancha").marca("Philips").precio(60.0).build(),
                Producto.builder().codigo("7839214").nombre("Centro de planchado").marca("Rowenta").precio(180.0).build(),
                Producto.builder().codigo("7839215").nombre("Cafetera espresso").marca("DeLonghi").precio(250.0).build(),
                Producto.builder().codigo("7839216").nombre("Cafetera de cápsulas").marca("Nespresso").precio(150.0).build(),
                Producto.builder().codigo("7839217").nombre("Cafetera americana").marca("Philips").precio(80.0).build(),
                Producto.builder().codigo("7839218").nombre("Tostadora").marca("Moulinex").precio(40.0).build(),
                Producto.builder().codigo("7839219").nombre("Batidora de vaso").marca("Braun").precio(70.0).build(),
                Producto.builder().codigo("7839220").nombre("Batidora de mano").marca("Bosch").precio(45.0).build(),
                Producto.builder().codigo("7839221").nombre("Robot de cocina").marca("Thermomix").precio(1200.0).build(),
                Producto.builder().codigo("7839222").nombre("Freidora de aire").marca("Philips").precio(130.0).build(),
                Producto.builder().codigo("7839223").nombre("Freidora").marca("Tefal").precio(80.0).build(),
                Producto.builder().codigo("7839224").nombre("Sandwichera").marca("Moulinex").precio(35.0).build(),
                Producto.builder().codigo("7839225").nombre("Grill eléctrico").marca("Tefal").precio(90.0).build(),
                Producto.builder().codigo("7839226").nombre("Arrocera").marca("Panasonic").precio(60.0).build(),
                Producto.builder().codigo("7839227").nombre("Olla programable").marca("Instant Pot").precio(110.0).build(),
                Producto.builder().codigo("7839228").nombre("Yogurtera").marca("Severin").precio(35.0).build(),
                Producto.builder().codigo("7839229").nombre("Heladera").marca("Cuisinart").precio(85.0).build(),
                Producto.builder().codigo("7839230").nombre("Exprimidor").marca("Philips").precio(40.0).build(),
                Producto.builder().codigo("7839231").nombre("Licuadora").marca("Braun").precio(55.0).build(),
                Producto.builder().codigo("7839232").nombre("Picadora").marca("Moulinex").precio(30.0).build(),
                Producto.builder().codigo("7839233").nombre("Amasadora").marca("KitchenAid").precio(400.0).build(),
                Producto.builder().codigo("7839234").nombre("Panificadora").marca("Moulinex").precio(95.0).build(),
                Producto.builder().codigo("7839235").nombre("Vaporizador de ropa").marca("Philips").precio(70.0).build(),
                Producto.builder().codigo("7839236").nombre("Secador de pelo").marca("Dyson").precio(350.0).build(),
                Producto.builder().codigo("7839237").nombre("Plancha de pelo").marca("GHD").precio(180.0).build(),
                Producto.builder().codigo("7839238").nombre("Afeitadora eléctrica").marca("Braun").precio(120.0).build(),
                Producto.builder().codigo("7839239").nombre("Depiladora").marca("Philips").precio(90.0).build(),
                Producto.builder().codigo("7839240").nombre("Cepillo de dientes eléctrico").marca("Oral-B").precio(60.0).build(),
                Producto.builder().codigo("7839241").nombre("Televisor 55 pulgadas").marca("Samsung").precio(800.0).build(),
                Producto.builder().codigo("7839242").nombre("Televisor 43 pulgadas").marca("LG").precio(500.0).build(),
                Producto.builder().codigo("7839243").nombre("Barra de sonido").marca("Sony").precio(250.0).build(),
                Producto.builder().codigo("7839244").nombre("Altavoz inteligente").marca("Amazon").precio(100.0).build(),
                Producto.builder().codigo("7839245").nombre("Proyector").marca("Epson").precio(600.0).build(),
                Producto.builder().codigo("7839246").nombre("Impresora").marca("HP").precio(150.0).build(),
                Producto.builder().codigo("7839247").nombre("Escáner").marca("Canon").precio(120.0).build(),
                Producto.builder().codigo("7839248").nombre("Router WiFi").marca("TP-Link").precio(80.0).build(),
                Producto.builder().codigo("7839249").nombre("Ventilador de torre").marca("Dyson").precio(280.0).build(),
                Producto.builder().codigo("7839250").nombre("Purificador de aire").marca("Philips").precio(320.0).build()
        );
    }
}
