/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package org.uv.DSWeb_practica06.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uv.DSWeb_practica06.Empleado;

/**
 *
 * @author aaron-emiliano
 */
public interface RepositoryEmpleado extends JpaRepository<Empleado, Long> {
    
}
