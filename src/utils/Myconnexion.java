/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

//port java.sql.cnx;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Myconnexion {

    public static Statement createStatement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    String url="jdbc:mysql://localhost:3306/bruteforce";
    String login="root";
    String pwd="";
    java.sql.Connection cnx;
    public static Myconnexion instance;
    
     
     private Myconnexion() {
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion réussite!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
     public java.sql.Connection getCnx() {
        return cnx;
    }

    
     public static Myconnexion getInstance(){
        if(instance == null){
            instance = new Myconnexion();
        }
        return instance;
    }

}
