/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.DSWeb_practica06;

/**
 *
 * @author aaron-emiliano
 */
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ojeda
 */

public class DTOVistaVenta {
    private Long id;
    private Date fecha;
    private BigDecimal total;
    private List<VentaDetalle> detalleList;
    
    public void llenarVista(Venta v, List<VentaDetalle> list){
        this.detalleList = list;
        this.id = v.getIdventa();
        this.fecha = v.getFecha();
        this.total = v.getTotal();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<VentaDetalle> getDetalleList() {
        return detalleList;
    }

    public void setDetalleList(List<VentaDetalle> detalleList) {
        this.detalleList = detalleList;
    }
    
    
}