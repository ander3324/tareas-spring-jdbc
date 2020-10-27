/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.tareasspring.model.service;

import com.analistas.tareasspring.model.domain.Usuario;
import com.analistas.tareasspring.model.repository.UsuarioRepository;

/**
 *
 * @author ander
 */
public class UsuarioServiceImpl implements IUsuarioService{

    UsuarioRepository repo = new UsuarioRepository();
    
    @Override
    public boolean validar(String nombre, String clave) {
        
//        if(repo.validarUsuario(nombre, clave) == 1){ 
//            return true;
//        } else {
//            return false;
//        }
        
          return repo.validarUsuario(nombre, clave) == 1 ? true : false;  //operador ternario...
    }

    @Override
    public Usuario buscarPorNombre(String nombre) {
        return repo.selectOne(nombre);
    }
}
