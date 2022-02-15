/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Evennement;
import java.sql.Connection;
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
 * @author Mortadha
 */
public class ServiceEvennement implements IService<Evennement>{
    Connection cnx;
    public ServiceEvennement(){
        cnx=Myconnexion.getInstance().getCnx();
    }
    @Override
    public void ajouter(Evennement e) {
        try {
            Statement st;
            
            st=cnx.createStatement();
            
            
            String query="INSERT INTO `evenement`(`nom`, `date`, `lieu`, `prix`, `nbre_particip`) VALUES "
                    + "('"+e.getNom()+"',"
                    + "'"+e.getDate()+"',"
                    + "'"+e.getLieu()+"',"
                    + "'"+e.getPrix()+"',"
                    + "'"+e.getNbre_particip()
                    +"')";
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            Statement st;
            st=cnx.createStatement();
            String query="DELETE FROM `evenement` WHERE idEv="+id;
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(int id_amodifier, Evennement modifier) {
        try {
            Statement st;
            st=cnx.createStatement();
            String query="UPDATE `evenement` SET `nom`='"+modifier.getNom()+"',"
                    + "`date`='"+modifier.getDate()+"',"
                    + "`lieu`='"+modifier.getLieu()+"',"
                    + "`prix`='"+modifier.getPrix()+"',"
                    + "`nbre_particip`='"+modifier.getNbre_particip()+"' "
                    + "WHERE idEv="+id_amodifier;
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Evennement> afficher() {
        List<Evennement> le =new ArrayList<>();
        try {
            Statement st;
            st=cnx.createStatement();
            
            String query="SELECT * FROM `evenement`";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Evennement e=new Evennement();
                e.setIdEv(rs.getInt("idEv"));
                e.setNom(rs.getString("nom"));
                e.setLieu(rs.getString("lieu"));
                e.setDate(rs.getDate("date"));
                e.setPrix(rs.getFloat("prix"));
                e.setNbre_particip(rs.getInt(5));
                le.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return le;
    }
    
}
