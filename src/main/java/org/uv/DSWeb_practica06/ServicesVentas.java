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
import java.util.Optional;
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
    
    public List<Venta> obtenerTodasLasVentas() {
        return ventaRepository.findAll();
    }

    public Optional<Venta> obtenerVentaPorId(Long id) {
        return ventaRepository.findById(id);
    }

    public Venta actualizarVenta(Long id, Venta ventaDTO) {
        Optional<Venta> optionalVenta = ventaRepository.findById(id);
        if (optionalVenta.isPresent()) {
            Venta ventaExistente = optionalVenta.get();
            ventaExistente.setFecha(ventaDTO.getFecha());
            ventaExistente.setTotal(ventaDTO.getTotal());

            // Actualizar detalles (puedes implementar lógica específica según tus necesidades)

            BigDecimal total = calcularTotalVenta(ventaExistente.getVentasDetalle());
            ventaExistente.setTotal(total);

            // Guardar la venta actualizada
            return ventaRepository.save(ventaExistente);
        } else {
            // Manejo de error: la venta no existe
            // Puedes lanzar una excepción, devolver un resultado especial, etc.
            return null;
        }
    }

    public void eliminarVenta(Long id) {
        Optional<Venta> optionalVenta = ventaRepository.findById(id);
        if (optionalVenta.isPresent()) {
            Venta venta = optionalVenta.get();
            // Eliminar detalles asociados (opcional, dependiendo de tus requisitos)
            ventaDetalleRepository.deleteAll(venta.getVentasDetalle());
            // Eliminar la venta
            ventaRepository.deleteById(id);
        } else {
            // Manejo de error: la venta no existe
            // Puedes lanzar una excepción, devolver un resultado especial, etc.
        }
    }
}

