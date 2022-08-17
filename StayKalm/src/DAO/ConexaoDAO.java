/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author dumed
 */
public class ConexaoDAO {

    public Connection conn = null;

    public Connection conectaBD() {

        String DATABASE_URL = "jdbc:postgresql://localhost:5432/aula2";
        String USER = "postgres";
        String PASSWORD = "postgres";

        try {
            //pega a conexao
            conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ConexaoDAO conectaBD" + erro.getMessage());
        }

        return conn;
    }
}
