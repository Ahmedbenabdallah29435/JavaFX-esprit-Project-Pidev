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
import java.util.ArrayList;
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
    public void supprimer(int id) {
        try {
            Statement st;
            st=conn.createStatement();
            String query="DELETE FROM `reclamation` WHERE idR="+id;
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   @Override
    public void modifier(int id_amodifier, Reclamation t) {
        try {
            Statement st;
            st=conn.createStatement();
            String query="UPDATE reclamation SET `nom`='"+t.getNom()+"',"
                    + "`description`='"+t.getDescription()+"',"
                    + "`type`='"+t.getType()+"',"
                    + "`date`='"+t.getDate()+"',"
                    + "WHERE idR="+id_amodifier;
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Reclamation> afficher() {
    List<Reclamation> le =new ArrayList<>();
        try {
           
            ste=conn.createStatement();
            
            String query="SELECT * FROM reclamation";
            rs=ste.executeQuery(query);
            while(rs.next()){
                Reclamation e=new Reclamation();
                e.setIdR(rs.getInt("idR"));
                e.setNom(rs.getString("nom"));
                e.setDescription(rs.getString("Description"));
                e.setType(rs.getString("type"));
                e.setDate(rs.getDate("date"));
                le.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return le;
    }

    @Override
    public List<Reclamation> afficherById(int id) {
    List<Reclamation> le =new ArrayList<>();
        try {
           
            ste=conn.createStatement();
            
            String query="SELECT * FROM reclamation WHERE idR="+id;
            rs=ste.executeQuery(query);
            while(rs.next()){
                Reclamation e=new Reclamation();
                e.setIdR(rs.getInt("idR"));
                e.setNom(rs.getString("nom"));
                e.setDescription(rs.getString("Description"));
                e.setType(rs.getString("type"));
                e.setDate(rs.getDate("date"));
                le.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return le;
    }
    
    
}
