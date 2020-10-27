/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.tareasspring.model.service;

import com.analistas.tareasspring.model.domain.Tarea;
import com.analistas.tareasspring.model.domain.Usuario;
import java.util.List;

/**
 *
 * @author ander
 */
public interface ITareaService {
    
    public List<Tarea> listarPorUsuario(Usuario usuario);
    
    public void guardar(Tarea tarea);
    
    public void borrar(int id);
    
    public void cambiarEstado(int idTarea);
    
}
