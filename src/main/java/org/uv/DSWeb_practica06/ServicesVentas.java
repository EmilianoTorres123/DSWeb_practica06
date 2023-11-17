/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.DSWeb_practica06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.uv.DSWeb_practica06.data.RepositoryDetalle;
import org.uv.DSWeb_practica06.data.RepositoryVentas;

/**
 *
 * @author aaron-emiliano
 */
@Service
public class ServicesVentas{

    @Autowired
    private RepositoryVentas ventaRepository;

    @Autowired
    private RepositoryDetalle ventaDetalleRepository;

    public Venta guardarVenta(Venta ventaDTO) {
        Venta venta = new Venta();
        venta.setFecha(ventaDTO.getFecha());
        venta.setTotal(ventaDTO.getTotal());

        List<VentaDetalle> detalles = ventaDTO.getVentasDetalle().stream()
                .map(this::mapToVentaDetalle)
                .collect(Collectors.toList());

        detalles.forEach(detalle -> detalle.setVenta(venta));
        venta.setVentasDetalle(detalles);

        BigDecimal total = calcularTotalVenta(detalles);
        venta.setTotal(total);

        // Guardar la venta y sus detalles
        Venta ventaGuardada = ventaRepository.save(venta);
        ventaDetalleRepository.saveAll(detalles);

        return ventaGuardada;
    }

    private VentaDetalle mapToVentaDetalle(VentaDetalle detalleDTO) {
        VentaDetalle detalle = new VentaDetalle();
        detalle.setIdProducto(detalleDTO.getIdProducto());
        detalle.setPrecio(detalleDTO.getPrecio());
        detalle.setDescripcion(detalleDTO.getDescripcion());
        detalle.setCantidad(detalleDTO.getCantidad());
        return detalle;
    }

    private BigDecimal calcularTotalVenta(List<VentaDetalle> detalles) {
        return detalles.stream()
                .map(detalle -> detalle.getPrecio().multiply(BigDecimal.valueOf(detalle.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

