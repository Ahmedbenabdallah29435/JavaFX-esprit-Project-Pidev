/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Evennement;
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
 * @author AhmedBenAbdallah
 */


public class ServiceEvennement implements IService<Evennement>{
    
    private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public ServiceEvennement(){
        cnx=Myconnexion.getInstance().getCnx();
    }
    @Override
    public void ajouter(Evennement e) {
        try {
            
            
            st=cnx.createStatement();
            
            
            String query="INSERT INTO `evenement`(`nom`, `date`, `lieu`, `prix`, `nbre_particip`,`idS`) VALUES "
                    + "('"+e.getNom()+"',"
                    + "'"+e.getDate()+"',"
                    + "'"+e.getLieu()+"',"
                    + "'"+e.getPrix()+"',"
                    + "'"+e.getNbre_particip()+"',"
                    + "'"+e.getIdS()
                    +"')";
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      public void ajouterpst(Evennement t) {
       String req = "insert into evenement (nom,date,lieu,prix,nbre_particip,idS) values (?,?,?,?,?,?)";
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, t.getNom());
            pst.setDate(2, (Date) t.getDate());
            pst.setString(3, t.getLieu());
            pst.setInt(4, (int) t.getPrix());
            pst.setInt(5, (int) t.getNbre_particip());
            pst.setInt(6, (int) t.getIdS());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void supprimer(int id) {
        try {
           
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
            
            st=cnx.createStatement();
            String query="UPDATE `evenement` SET `nom`='"+modifier.getNom()+"',"
                    + "`date`='"+modifier.getDate()+"',"
                    + "`lieu`='"+modifier.getLieu()+"',"
                    + "`prix`='"+modifier.getPrix()+"',"
                    + "`idS`='"+modifier.getIdS()+"',"
                    + "`nbre_particip`='"+modifier.getNbre_particip()+"' "
                    
                    + "WHERE idEv="+id_amodifier;
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    public void modifierpst(int id_amodifier, Evennement e) {
         
           String req = "UPDATE evenement SET nom=?,date=?,lieu=?,Prix=?,nbre_particip=?,idS=? where idEv=?";
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, e.getNom());
            pst.setDate(2, (Date) e.getDate());
            pst.setString(3, e.getLieu());
            pst.setFloat(4, e.getPrix());
            pst.setInt(5, e.getNbre_particip());
            pst.setInt(6, e.getIdS());
            pst.setInt(7, id_amodifier);
            pst.executeUpdate();
        } catch (SQLException ex) { 
              Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<Evennement> afficher() {
        List<Evennement> le =new ArrayList<>();
        try {
           
            st=cnx.createStatement();
            
            String query="SELECT * FROM `evenement`";
            rs=st.executeQuery(query);
            while(rs.next()){
                Evennement e=new Evennement();
                e.setIdEv(rs.getInt("idEv"));
                e.setNom(rs.getString("nom"));
                e.setLieu(rs.getString("lieu"));
                e.setDate(rs.getDate("date"));
                e.setPrix(rs.getFloat("prix"));
                e.setNbre_particip(rs.getInt(6));
                e.setIdS(rs.getInt(7));
                le.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return le;
    }

    @Override
    public List<Evennement> afficherById(int id) {
        List<Evennement> le =new ArrayList<>();
        try {
            
            st=cnx.createStatement();
            
            String query="SELECT * FROM `evenement` WHERE idEv="+id;
            rs=st.executeQuery(query);
            while(rs.next()){
                Evennement e=new Evennement();
                e.setIdEv(rs.getInt("idEv"));
                e.setNom(rs.getString("nom"));
                e.setLieu(rs.getString("lieu"));
                e.setDate(rs.getDate("date"));
                e.setPrix(rs.getFloat("prix"));
                e.setNbre_particip(rs.getInt(6));
                e.setIdS(rs.getInt(7));
                le.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return le;
    }
    
    public Evennement afficherrById(int id) {
            String query="SELECT * FROM `evenement` WHERE idEv=?";
            Evennement e=null;
        try {
                pst = cnx.prepareStatement(query);
                pst.setInt(1,id);
                rs = pst.executeQuery();
                if (rs.next()){
                    e = new Evennement(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getFloat(5), rs.getInt(6), rs.getInt(7));
                } 
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
    }
    
}
