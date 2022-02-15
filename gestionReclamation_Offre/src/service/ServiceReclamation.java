/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Reclamation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Myconnexion;

/**
 *
 * @author Radhouan
 */
public class ServiceReclamation implements IService<Reclamation> {

    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public ServiceReclamation() {
        conn = Myconnexion.getInstance().getCnx();
    }
//Nouvelle Changement
    
    @Override
    public void ajouter(Reclamation t) {
       String req = "insert into reclamation (nom,description,type,date) values (?,?,?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getDescription());
            pst.setString(3, t.getType());
            pst.setDate(4, (Date) t.getDate());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void supprimer(Reclamation t) {
        
    }

    @Override
    public void modifier(Reclamation t) {
       
    }

    @Override
    public List<Reclamation> afficher() {
        return null;
        
    }

    @Override
    public Reclamation afficherById(int id) {
        return null;
        
    }

    
}
