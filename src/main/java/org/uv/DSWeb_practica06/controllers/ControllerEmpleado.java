/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package org.uv.DSWeb_practica06.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.uv.DSWeb_practica06.Empleado;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.uv.DSWeb_practica06.data.RepositoryEmpleado;
import java.util.Optional;
import org.springframework.validation.annotation.Validated;
/**
 *
 * @author aaron-emiliano
 */
@Validated
@RestController
@RequestMapping("/api/empleado")
public class ControllerEmpleado {
    
    @Autowired
    private RepositoryEmpleado repositoryEmpleado;
    
     @PostMapping
    public ResponseEntity<Empleado> post(@RequestBody Empleado empleado) {
        Empleado empRes=repositoryEmpleado.save(empleado);
        return ResponseEntity.ok(empRes);
    }
    
    @GetMapping("msg")
    public String msg(){
        return "hola mundo me morire";
    }
    
    @GetMapping()
    public List<Empleado> List(){
        return repositoryEmpleado.findAll();
    

    }
    
    @GetMapping("/{id}")
    public Empleado get(@PathVariable Long id){
        Optional<Empleado> optRes = repositoryEmpleado.findById(id);
        return optRes.isPresent() ? optRes.get():null;
    }
    
//    @GetMapping()
//    public List<Empleado> list() {
//        List<Empleado> lstEmpleado=new ArrayList<>();
//        Empleado e1=new Empleado(1L, "arturo", "av.1", "123");
//        Empleado e2=new Empleado(2L, "jose", "av.2", "222");
//        Empleado e3=new Empleado(3L, "pepeito", "av.3", "333");
//                
//        lstEmpleado.add(e1);
//        lstEmpleado.add(e2);
//        lstEmpleado.add(e3);
//        
//        return lstEmpleado;
//        
//    }
    
//    @GetMapping("/{id}")
//    public Object get(@PathVariable String id) {
//        Empleado e1=new Empleado(1L, "arturo", "av.1", "123");
//        Empleado e2=new Empleado(2L, "jose", "av.2", "222");
//        Empleado e3=new Empleado(3L, "pepeito", "av.3", "333");
//        
//        if (id.equals("1")) {
//            return e1;
//            
//        }
//        if (id.equals("2")) {
//            return e2;
//        }
//        if (id.equals("3")) {
//            return e3;
//        }
//        return null;
//        
//        
//    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
        return null;
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }
    
}
