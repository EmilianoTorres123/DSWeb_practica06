/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package org.uv.DSWeb_practica06.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.uv.DSWeb_practica06.DTOVistaVenta;
import org.uv.DSWeb_practica06.ServicesVentas;
import org.uv.DSWeb_practica06.Venta;
import org.uv.DSWeb_practica06.VentaDetalle;
import org.uv.DSWeb_practica06.data.RepositoryDetalle;
import org.uv.DSWeb_practica06.data.RepositoryVentas;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 *
 * @author aaron-emiliano
 */
@RestController
@RequestMapping("/api/ventas")
public class Controllerventas {
    
    @Autowired
    private ServicesVentas ventaService;
    @Autowired
    private RepositoryVentas ventaRepository;

    @Autowired
    private RepositoryDetalle ventaDetalleRepository;
    @GetMapping("/")
    public List<DTOVistaVenta> list() {
        List<Venta> data = ventaRepository.findAll();
        List<DTOVistaVenta> lista = new ArrayList<>();
        for (Venta v : data) {
            DTOVistaVenta ventaVista = new DTOVistaVenta();
            List<VentaDetalle> list = ventaDetalleRepository.findByVentaId(v.getIdventa());
            ventaVista.llenarVista(v, list);
            lista.add(ventaVista);
        }
        return lista;
    }
    
    @GetMapping("/{id}")
    public DTOVistaVenta get(@PathVariable Long id) {
        Optional<Venta> v = ventaRepository.findById(id);
        DTOVistaVenta vVista = new DTOVistaVenta();
            List<VentaDetalle> list = ventaDetalleRepository.findByVentaId(id);
            vVista.llenarVista(v.get(), list);
            return vVista;
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
        return null;
    }
    
    @PostMapping
    public ResponseEntity<Venta> agregarVenta(@RequestBody Venta ventaDTO) {
        Venta ventaGuardada = ventaService.guardarVenta(ventaDTO);
        return new ResponseEntity<>(ventaGuardada, HttpStatus.CREATED);
    }
    
    
    @DeleteMapping("/{id}")
     public ResponseEntity<Venta> delete(@PathVariable Long id) {   
        try {
            Optional<Venta> _v = ventaRepository.findById(id);
            List<VentaDetalle> list = ventaDetalleRepository.findByVentaId(id);
            for(VentaDetalle dd: list){
                ventaDetalleRepository.delete(dd);
            }
            ventaRepository.delete(_v.get());
            return new ResponseEntity<>(_v.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }
    //este es controller de ventas
}
