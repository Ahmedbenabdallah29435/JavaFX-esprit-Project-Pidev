/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Sponsor;
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
 * @author AhmedBenAbdallah
 */
public class ServiceSponsor implements IService<Sponsor> {

    Connection cnx;
    public ServiceSponsor(){
        cnx=Myconnexion.getInstance().getCnx();
    }
    @Override
    public void ajouter(Sponsor s) {
        try {
            Statement st;
            
            st=cnx.createStatement();
            
            
            String query="INSERT INTO `sponsor`(`nom`, `type`, `adresse`, `tel`, `email`) VALUES "
                    + "('"+s.getNom()+"',"
                    + "'"+s.getType()+"',"
                    + "'"+s.getAdresse()+"',"
                    + "'"+s.getTel()+"',"
                    + "'"+s.getEmail()
                    +"')";
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSponsor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            Statement st;
            st=cnx.createStatement();
            String query="DELETE FROM `sponsor` WHERE idS="+id;
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSponsor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(int id_amodifier, Sponsor modifier) {
        try {
            Statement st;
            st=cnx.createStatement();
            String query="UPDATE `sponsor` SET `nom`='"+modifier.getNom()+"',"
                    + "`type`='"+modifier.getType()+"',"
                    + "`adresse`='"+modifier.getAdresse()+"',"
                    + "`tel`='"+modifier.getTel()+"',"
                    + "`email`='"+modifier.getEmail()+"' "
                    + "WHERE idS="+id_amodifier;
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSponsor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Sponsor> afficher() {
        List<Sponsor> ls =new ArrayList<>();
        try {
            Statement st;
            st=cnx.createStatement();
            
            String query="SELECT * FROM `sponsor`";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Sponsor s=new Sponsor();
                s.setIdS(rs.getInt("idS"));
                s.setNom(rs.getString("nom"));
                s.setType(rs.getString("type"));
                s.setAdresse(rs.getString("adresse"));
                s.setTel(rs.getInt("tel"));
                s.setEmail(rs.getString(5));
                ls.add(s);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSponsor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }

    @Override
    public List<Sponsor> afficherById(int id) {
        List<Sponsor> ls =new ArrayList<>();
        try {
            Statement st;
            st=cnx.createStatement();
            
            String query="SELECT * FROM `sponsor` WHERE idS="+id;
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Sponsor s=new Sponsor();
                s.setIdS(rs.getInt("idS"));
                s.setNom(rs.getString("nom"));
                s.setType(rs.getString("type"));
                s.setAdresse(rs.getString("adresse"));
                s.setTel(rs.getInt("tel"));
                s.setEmail(rs.getString(5));
                ls.add(s);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSponsor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }

   
    
}
