/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Evennement;
import entity.Sponsor;
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
import java.util.stream.Collectors;
import utils.Myconnexion;




public class ServiceEvennement implements IService<Evennement>{
    private Connection cnx;
    private PreparedStatement pst;
    private ResultSet rs;
    private Statement st;
public ServiceEvennement(){
        cnx=Myconnexion.getInstance().getCnx();
    }    

    @Override
    public boolean ajouter(Evennement e) {
       String req="insert into evenement (nom,date,lieu,description,nb_place,imageE,idS) values (?,?,?,?,?,?,?)";
        Boolean inserted=false;
        try {
            pst=cnx.prepareStatement(req);
            pst.setString(1,e.getNom());
            pst.setDate(2, (Date) e.getDate());
            pst.setString(3,e.getLieu());
            pst.setString(4,e.getDescription());
            pst.setInt(5,e.getNb_place());
            pst.setString(6,e.getImageE());
            if(e.getSponsor()==null){
               pst.setNull(7,1);

            }else{
              pst.setInt(7,e.getSponsor().getId());

            }
            inserted=pst.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserted;
    }

    @Override
    public boolean supprimer(Evennement e) {
        String req="DELETE FROM evenement WHERE idEv=?";
      Boolean deleted=false;
        try {
             pst=cnx.prepareStatement(req);
             pst.setInt(1,e.getIdEv());
             deleted=pst.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
     return deleted;
    }

    @Override
    public boolean modifier(Evennement e) {
        String req="UPDATE evenement SET nom=?,date=?,lieu=?,description=?,nb_place=?,imageE=?,idS=? WHERE idEv=?";
       Boolean updated=false;
        try {
            pst=cnx.prepareStatement(req);
         
            pst.setString(1,e.getNom());
            pst.setDate(2, (Date) e.getDate());
            
            pst.setString(3,e.getLieu());
            pst.setString(4,e.getDescription());
           
            pst.setInt(5,e.getNb_place());
            pst.setString(6,e.getImageE());
            pst.setInt(7,e.getSponsor().getId());
             pst.setInt(8,e.getIdEv());
            
            updated=pst.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
     return updated;
    }

    @Override
    public List<Evennement> afficher() {
        String req="select * from evenement";
        List<Evennement> list =new ArrayList<>();
        try {
            st=cnx.createStatement();
            rs=st.executeQuery(req);
            ServiceSponsor se =new ServiceSponsor();
            while(rs.next())
            { 
                list.add(new Evennement(rs.getInt("idEv"),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),se.afficher_id(rs.getInt("idS"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Evennement afficher_id(int id) {
        String req="select * from evenement where idEv=?";
        Evennement j=null;
        try {
            pst=cnx.prepareStatement(req);
            pst.setInt(1,id);
            ServiceSponsor se =new ServiceSponsor();
            rs = pst.executeQuery();
             if (rs.next())
             {              
                 j = new Evennement(rs.getInt("idEv"),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),se.afficher_id(rs.getInt("idS")));
                
             }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        return j;
        
    }
    
    
    
    
    
     public  List<Evennement> recherchermutli(String critere, String col, String ordre)
       {
             List<Evennement> lm =new ArrayList<>();
        try {
            System.out.println(critere);
            st=cnx.createStatement();
           // System.out.println(" SELECT * FROM  magasin where nom like '%"+critere+"%' or adresse like '%"+critere+"%' or cast(nombrebloc as char) like '%"+critere+"%' order by "+col+" "+ordre);
            String query=" SELECT * FROM  evenement where nom like '%"+critere+"%' or cast(date as char) like '%"+critere+"%' or lieu like '%"+critere+"%' or description like '%"+critere+"%' or cast(nb_place as char) like '%"+critere+"%' order by "+col+" "+ordre;
           
                rs=st.executeQuery(query);
                while(rs.next()){
               Evennement s =new Evennement();
               
                 s.setNom(rs.getString("nom"));
                 s.setDate(rs.getDate("date"));
               
                s.setLieu(rs.getString("lieu"));
           
               s.setDescription(rs.getString("description"));
                   s.setNb_place(rs.getInt("nb_place"));
              

                
                lm.add(s);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lm ;
       }
}
//    Connection cnx;
//    public ServiceEvennement(){
//        cnx=Myconnexion.getInstance().getCnx();
//    }
//    
//    @Override
//    public void ajouter(Evennement e) {
//        try {
//            Statement st;
//            
//            st=cnx.createStatement();
//            
//            
//            String query="INSERT INTO `evenement`(`nom`, `date`, `lieu`, `prix`, `nbre_particip`) VALUES "
//                    + "('"+e.getNom()+"',"
//                    + "'"+e.getDate()+"',"
//                    + "'"+e.getLieu()+"',"
//                    + "'"+e.getPrix()+"',"
//                    + "'"+e.getNbre_particip()
//                    +"')";
//            st.executeUpdate(query);
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    @Override
//    public void supprimer(int id) {
//        try {
//            Statement st;
//            st=cnx.createStatement();
//            String query="DELETE FROM `evenement` WHERE id_ev="+id;
//            st.executeUpdate(query);
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    @Override
//    public void modifier(int id_amodifier, Evennement modifier) {
//        try {
//            Statement st;
//            st=cnx.createStatement();
//            String query="UPDATE `evenement` SET `nom`='"+modifier.getNom()+"',"
//                    + "`date`='"+modifier.getDate()+"',"
//                    + "`lieu`='"+modifier.getLieu()+"',"
//                    + "`prix`='"+modifier.getPrix()+"',"
//                    + "`nbre_particip`='"+modifier.getNbre_particip()+"' "
//                    + "WHERE id_ev="+id_amodifier;
//            st.executeUpdate(query);
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    @Override
//    public List<Evennement> afficher() {
//        List<Evennement> le =new ArrayList<>();
//        ServiceSponsor ss =new ServiceSponsor();
//        try {
//            Statement st;
//            st=cnx.createStatement();
//            
//            String query="SELECT * FROM `evenement`";
//            ResultSet rs=st.executeQuery(query);
//            while(rs.next()){
//                Evennement e=new Evennement();
//                e.setIdEv(rs.getInt("id_ev"));
//                e.setNom(rs.getString("nom"));
//                e.setLieu(rs.getString("lieu"));
//                e.setDate(rs.getDate("date"));
//                e.setPrix(rs.getFloat("prix"));
//                e.setNbre_particip(rs.getInt("nbre_particip"));
//                e.setSponsors(ss.findByEvent(e.getIdEv()));
//                le.add(e);
//            }
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return le;
//    }
//    public Evennement rechercheParId(int id){
//        Evennement e =new Evennement();
//        try {
//            Statement st;
//            st=cnx.createStatement();
//            
//            String query="SELECT * FROM `evenement` WHERE id_ev="+id;
//            ResultSet rs=st.executeQuery(query);
//            while(rs.next()){
//                
//                e.setIdEv(rs.getInt("id_ev"));
//                e.setNom(rs.getString("nom"));
//                e.setLieu(rs.getString("lieu"));
//                e.setDate(rs.getDate("date"));
//                e.setPrix(rs.getFloat("prix"));
//                e.setNbre_particip(rs.getInt("nbre_particip"));
//                
//            }
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return e;
//    }
//    public List<Evennement> orderByDate(){
//        List<Evennement> le =new ArrayList<>();
//        try {
//            Statement st;
//            st=cnx.createStatement();
//            
//            String query="SELECT * FROM `evenement` ORDER BY date";
//            ResultSet rs=st.executeQuery(query);
//            while(rs.next()){
//                Evennement e=new Evennement();
//                e.setIdEv(rs.getInt("id_ev"));
//                e.setNom(rs.getString("nom"));
//                e.setLieu(rs.getString("lieu"));
//                e.setDate(rs.getDate("date"));
//                e.setPrix(rs.getFloat("prix"));
//                e.setNbre_particip(rs.getInt("nbre_particip"));
//                le.add(e);
//            }
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return le;
//    }
//    
//    public void affecterSponsoreEvenement(int id_evenement,Sponsor s){
//        ServiceSponsor ss=new ServiceSponsor();
//        Evennement e=rechercheParId(id_evenement);
//        s.setEvennement(e);
//        
//      
//        ss.modifier(s.getIdS(), s);
//    }
//    
//}
