/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Offre;
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
public class ServiceOffre implements IService<Offre>{
    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

public ServiceOffre() {
    conn = Myconnexion.getInstance().getCnx();
    }
    
@Override
    public void ajouter(Offre t) {
       String req = "insert into offre (nom,prenom,type,date,nbre) values (?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setString(3, t.getType());
            pst.setDate(4, (Date) t.getDate());
            pst.setInt(5, t.getNbre());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
@Override
    public void supprimer(int id) {
        try {
            Statement st;
            st=conn.createStatement();
            String query="DELETE FROM `offre` WHERE idO="+id;
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

@Override
    public void modifier(int id_amodifier, Offre modifier) {
    try {
            Statement st;
            st=conn.createStatement();
            String query="UPDATE offre SET `nom`='"+modifier.getNom()+"',"
                    + "`prenom`='"+modifier.getPrenom()+"',"
                    + "`type`='"+modifier.getType()+"',"
                    + "`date`='"+modifier.getDate()+"',"
                    + "`nbreJ`='"+modifier.getNbre()+"',"
                    + "WHERE idO="+id_amodifier;
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

  @Override
    public List<Offre> afficher() {
        List<Offre> le =new ArrayList<>();
        try {
           
            ste=conn.createStatement();
            
            String query="SELECT * FROM offre";
            rs=ste.executeQuery(query);
            while(rs.next()){
                Offre e=new Offre();
                e.setIdO(rs.getInt("idO"));
                e.setNom(rs.getString("nom"));
                e.setPrenom(rs.getString("prenom"));
                e.setType(rs.getString("type"));
                e.setDate(rs.getDate("date"));
                e.setNbre(rs.getInt("nbre"));
                le.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return le; 
    }

    @Override
    public List<Offre> afficherById(int id) {
    List<Offre> le =new ArrayList<>();

    try {
           
            ste=conn.createStatement();
            
            String query="SELECT * FROM offre WHERE idO="+id;
            rs=ste.executeQuery(query);
            while(rs.next()){
                Offre e=new Offre();
                e.setIdO(rs.getInt("idO"));
                e.setNom(rs.getString("nom"));
                e.setPrenom(rs.getString("prenom"));
                e.setType(rs.getString("type"));
                e.setDate(rs.getDate("date"));
                e.setNbre(rs.getInt("nbre"));
                le.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return le;
        
    }

    
}
    

