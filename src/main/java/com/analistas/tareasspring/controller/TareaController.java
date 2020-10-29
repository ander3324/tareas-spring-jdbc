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
    public String abrirFormulario(Model model) {
        
        model.addAttribute("titulo", "Nueva Tarea");
        model.addAttribute("subtitulo", "Completar los Campos:");
        
        return "form";
    }
    
    @GetMapping("/cambiar/{id}")
    public String finalizarTarea(@PathVariable int id) {
        
        tarServ.cambiarEstado(id);
        return "redirect:/tareas";
    }
}
