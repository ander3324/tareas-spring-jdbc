/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.tareasspring.model.service;

import com.analistas.tareasspring.model.domain.Tarea;
import com.analistas.tareasspring.model.domain.Usuario;
import com.analistas.tareasspring.model.repository.TareaRepository;
import java.util.List;

/**
 *
 * @author ander
 */
public class TareaServiceImpl implements ITareaService{
    
    TareaRepository repo = new TareaRepository();

    @Override
    public List<Tarea> listarPorUsuario(Usuario usuario) {
        return repo.selectByUsuario(usuario);
    }

    @Override
    public void guardar(Tarea tarea) {
        
        //Si es modificación...
        if(tarea.getId() != 0)
            repo.updateTarea(tarea);
//        else
//            repo.insertTarea(tarea);

    }

    @Override
    public void borrar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cambiarEstado(int idTarea) {
        repo.updateCumplida(idTarea);
    }

    @Override
    public Tarea buscarPorId(int id) {
        return repo.selectById(id).get(0);
    }
    
}
