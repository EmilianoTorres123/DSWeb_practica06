/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.DSWeb_practica06;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author zurisaddairj
 */
@Entity
@Table(name="venta")
public class Venta implements Serializable {
    @Id
    @GeneratedValue(generator = "venta_idventa_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "venta_idventa_seq", sequenceName = "venta_idventa_seq", 
            initialValue = 1, allocationSize = 1)
    @Column(name = "idventa")
    private Long idventa;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "total")
    private BigDecimal total;
    
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
//    private Cliente cliente;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "venta", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<VentaDetalle> ventasDetalle = new ArrayList<>();

    public List<VentaDetalle> getVentasDetalle() {
        return ventasDetalle;
    }

    public void setVentasDetalle(List<VentaDetalle> ventasDetalle) {
        this.ventasDetalle = ventasDetalle;
    }
    
    //

//    public Cliente getCliente() {
//        return cliente;
//    }
//
//    public void setCliente(Cliente cliente) {
//        this.cliente = cliente;
//    }

    public Long getIdventa() {
        return idventa;
    }

    public void setIdventa(Long idventa) {
        this.idventa = idventa;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
//
//    public Long getIdCliente() {
//        return idCliente;
//    }
//
//    public void setIdCliente(Long idCliente) {
//        this.idCliente = idCliente;
//    }
    
    
}