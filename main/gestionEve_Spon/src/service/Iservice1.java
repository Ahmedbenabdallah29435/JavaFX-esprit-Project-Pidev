/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Espace Sboui
 */
public interface Iservice1<T> {
   // void ajouter(T e)throws SQLException;
    List<T> afficher();

}
