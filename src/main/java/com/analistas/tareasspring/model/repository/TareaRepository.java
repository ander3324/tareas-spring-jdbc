/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.tareasspring.model.repository;

import com.analistas.tareasspring.jdbc.ConexionJDBC;
import com.analistas.tareasspring.model.domain.Tarea;
import com.analistas.tareasspring.model.domain.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ander
 */
public class TareaRepository {

    private Connection cn;
    private List<Tarea> tareas;

    public TareaRepository() {
        tareas = new ArrayList<>();
    }

    public List<Tarea> selectByUsuario(Usuario usuario) {

        try {

            tareas.clear();

            cn = new ConexionJDBC().getConnection();

            String select = "SELECT * from tareas where fk_id_usuario = ?";

            PreparedStatement ps = cn.prepareStatement(select);
            ps.setInt(1, usuario.getId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tareas.add(
                        new Tarea(
                                rs.getInt(1),
                                rs.getString(2),
                                LocalDate.of(
                                        rs.getDate(3).getYear() + 1900,
                                        rs.getDate(3).getMonth() + 1,
                                        rs.getDate(3).getDate()),
                                rs.getBoolean(4),
                                usuario
                        )
                );
            }

            cn.close();
            rs.close();
            ps.close();

            return tareas;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public List<Tarea> selectById(int id) {

        try {

            tareas.clear();

            cn = new ConexionJDBC().getConnection();

            String select = "SELECT * from tareas where pk_id_tarea = ?";

            PreparedStatement ps = cn.prepareStatement(select);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tareas.add(
                        new Tarea(
                                rs.getInt(1),
                                rs.getString(2),
                                LocalDate.of(
                                        rs.getDate(3).getYear() + 1900,
                                        rs.getDate(3).getMonth() + 1,
                                        rs.getDate(3).getDate()),
                                rs.getBoolean(4),
                                null
                        )
                );
            }

            cn.close();
            rs.close();
            ps.close();

            return tareas;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public void updateCumplida(int idTarea) {

        try {

            cn = new ConexionJDBC().getConnection();

            String consultaSQL = "update tareas "
                    + "set cumplida = not cumplida "
                    + "where pk_id_tarea = ?";

            PreparedStatement ps = cn.prepareStatement(consultaSQL);
            ps.setInt(1, idTarea);

            ps.execute();

            cn.close();
            ps.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
            return;
        }
    }

    public void updateTarea(Tarea tarea) {

        try {

            cn = new ConexionJDBC().getConnection();

            String update = "update tareas "
                    + "set descripcion = ?, fecha = ? "
                    + "where pk_id_tarea = ?";

            PreparedStatement ps = cn.prepareStatement(update);
            ps.setString(1, tarea.getDescripcion());
            ps.setDate(2, Date.valueOf(tarea.getFecha()));
            ps.setInt(3, tarea.getId());

            ps.execute();

            cn.close();
            ps.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void insertTarea(Tarea tarea) {

        try {

            cn = new ConexionJDBC().getConnection();

            String update = "insert into tareas (descripcion, fecha, fk_id_usuario) "
                    + " values(?, ?, ?)";

            PreparedStatement ps = cn.prepareStatement(update);
            ps.setString(1, tarea.getDescripcion());
            ps.setDate(2, Date.valueOf(tarea.getFecha()));
            ps.setInt(3, tarea.getUsuario().getId());

            ps.execute();

            cn.close();
            ps.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}
