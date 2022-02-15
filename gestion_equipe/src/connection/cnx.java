/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

//port java.sql.cnx;
import java.sql.DriverManager;
import java.sql.SQLException;

public class cnx {
    String url="jdbc:mysql://localhost:3306/bruteforce";
    String login="root";
    String pwd="";
    java.sql.Connection cnx;
    public static cnx instance;
    
     
     private cnx() {
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

    
     public static cnx getInstance(){
        if(instance == null){
            instance = new cnx();
        }
        return instance;
    }

}
