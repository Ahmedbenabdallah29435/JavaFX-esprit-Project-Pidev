/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Mortadha
 */
public class Myconnexion {
   final String URL="jdbc:mysql://127.0.0.1:3307/projetpidev";
    final String USER ="root";
    final String PWD ="";
  private static java.sql.Connection cnx ;
  private static Myconnexion instance ;
  

    private  Myconnexion() {
    
       try {
                cnx = DriverManager.getConnection(URL, USER, PWD);
                System.out.println("connexion etablie ......");
        } catch (SQLException ex) {
           
           System.out.println(ex.getMessage());
        }
    }
  public static Myconnexion getInstance(){
      
      if (instance == null){
            instance = new Myconnexion();
        }
      
      return instance;
  }
         public static java.sql.Connection getCnx (){
             return cnx;
         }
  
}
