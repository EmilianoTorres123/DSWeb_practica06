/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package org.uv.DSWeb_practica06.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uv.DSWeb_practica06.VentaDetalle;
import java.util.List;
/**
 *
 * @author aaron-emiliano
 */
public interface RepositoryDetalle extends JpaRepository<VentaDetalle, Long> {
    List<VentaDetalle> findByVentaId(Long id);
}
