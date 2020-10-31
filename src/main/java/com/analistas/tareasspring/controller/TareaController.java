/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.tareasspring.controller;

import com.analistas.tareasspring.model.domain.Tarea;
import com.analistas.tareasspring.model.domain.Usuario;
import com.analistas.tareasspring.model.service.TareaServiceImpl;
import com.analistas.tareasspring.model.service.UsuarioServiceImpl;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ander
 */
@Controller
public class TareaController {
    
    //Objetos de servicio
    UsuarioServiceImpl usuServ = new UsuarioServiceImpl();
    TareaServiceImpl tarServ = new TareaServiceImpl();
    
    @RequestMapping(value="/tareas", method = RequestMethod.GET)
    public String verTareas(Model model) {
        
        Usuario usuario = usuServ.buscarPorNombre("Jorgito");
        List<Tarea> tareas = tarServ.listarPorUsuario(usuario);
    
        model.addAttribute("titulo", "Lista de Tareas");
        model.addAttribute("tareas", tareas);
        
        return "list";
    }
    
    //@RequestMapping(value="/form", method = RequestMethod.GET)
    //Alias de RequestMapping tipo GET
    @GetMapping("/form")
    public String nuevo(Model model) {
        
        Usuario usuario = usuServ.buscarPorNombre("Jorgito"); //Mamarrachada temporal...
        
        Tarea tarea = new Tarea();
        tarea.setFecha(LocalDate.now());
        tarea.setUsuario(usuario);
     
        model.addAttribute("titulo", "Nueva Tarea");
        model.addAttribute("subtitulo", "Completar los Campos:");
        model.addAttribute("tarea", tarea);
        
        return "form";
    }
    
    @GetMapping("/form/{id}")
    public String editar(@PathVariable int id, Model model) {
        
        Usuario usuario = usuServ.buscarPorNombre("Jorgito");  //Mamarrachada temporal...
    
        Tarea tarea = tarServ.buscarPorId(id);
        tarea.setUsuario(usuario);
        
        model.addAttribute("titulo", "Modificar Tarea");
        model.addAttribute("subtitulo", "Editar los Campos:");
        model.addAttribute("tarea", tarea);
        
        return "form";
    }
    
    @GetMapping("/cambiar/{id}")
    public String finalizarTarea(@PathVariable int id) {
        
        tarServ.cambiarEstado(id);
        return "redirect:/tareas";
    }
}
