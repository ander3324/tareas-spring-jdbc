/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.tareasspring.model.repository;

import com.analistas.tareasspring.jdbc.ConexionJDBC;
import com.analistas.tareasspring.model.domain.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ander
 */
public class UsuarioRepository {

    private List<Usuario> usuarios;
    private Connection cn;

    public UsuarioRepository() {
        usuarios = new ArrayList<>();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public int validarUsuario(String nombre, String clave) {

        try {
            cn = new ConexionJDBC().getConnection();

            String validar = "select count(*) from usuarios "
                    + "where nombre like ? and clave like ?";

            PreparedStatement ps = cn.prepareStatement(validar);

            ps.setString(1, nombre);
            ps.setString(2, clave);

            ResultSet rs = ps.executeQuery();

            int cant = 0;
            while (rs.next()) {
                cant = rs.getInt(1);
            }

            //Cerrar conexiones...
            cn.close();
            ps.close();
            rs.close();

            return cant;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return 0;
        }
    }

    public Usuario selectOne(String nombre) {

        try {

            cn = new ConexionJDBC().getConnection();
            
            String sql = "select * from usuarios where nombre like ?";
            
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, nombre);
            
            ResultSet rs = ps.executeQuery();
            
            //Guardar el usuario para retornarlo
            Usuario u = new Usuario();
            
            while(rs.next()) {
                u.setId(rs.getInt(1));
                u.setNombre(rs.getString(2));
                u.setClave(rs.getString(3));
            }
            
            return u;
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
