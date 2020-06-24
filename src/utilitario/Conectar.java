/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitario;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Elias
 */
public class Conectar {
    private static final String usuario = "root";
    private static final String senha = "abreu";
    private static final String url = "jdbc:mysql://localhost/consultorio";
    public static Connection getConectar(){
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, usuario, senha);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados "+ex.getMessage());
        }
       return con;
    }
}
