/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.tareasspring.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ander
 */
public class ConexionJDBC {
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/tareas_db", "root", "wacaballa"); //Cadena de conexión...
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
