/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import entity.Evennement;
import entity.Sponsor;
import java.sql.Connection;
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
/**
 *
 * @author Ahmed
 */
public class ServiceSponsor implements IService<Sponsor> {

    private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public ServiceSponsor(){
        cnx=Myconnexion.getInstance().getCnx();
    }

  @Override
    public boolean ajouter(Sponsor s) {
        String req="insert into sponsor (nom,type,adresse,tel,email,imgS) values (?,?,?,?,?,?)";
        Boolean inserted=false;
        try {
            pst=cnx.prepareStatement(req);
            pst.setString(1,s.getNom());
            pst.setString(2,s.getType());
            pst.setString(3,s.getAdresse());
         
             pst.setInt(4,s.getTel());
              pst.setString(5,s.getEmail());
               pst.setString(6,s.getImgS());
        
            inserted=pst.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSponsor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserted;
    }

    @Override
    public boolean supprimer(Sponsor s) {
         String req="DELETE FROM sponsor WHERE id=?";
      Boolean deleted=false;
        try {
             pst=cnx.prepareStatement(req);
             pst.setInt(1,s.getId());
             deleted=pst.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSponsor.class.getName()).log(Level.SEVERE, null, ex);
        }
     return deleted;
    }

    @Override
    public boolean modifier(Sponsor s) {
          String req="UPDATE sponsor SET nom=?,type=?,adresse=?,tel=?,email=?,imgS=? WHERE id=?";
       Boolean updated=false;
        try {
            pst=cnx.prepareStatement(req);
          
            pst.setString(1,s.getNom());
            pst.setString(2,s.getType());
            pst.setString(3,s.getAdresse());
             pst.setInt(4,s.getTel());
              pst.setString(5,s.getEmail());
               pst.setString(6,s.getImgS());
            pst.setInt(7,s.getId());
            updated=pst.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSponsor.class.getName()).log(Level.SEVERE, null, ex);
        }
     return updated;
    }

    @Override
    public List<Sponsor> afficher() {
        String req="select *from sponsor ";
        List<Sponsor> list =new ArrayList<>();
        try {
            st=cnx.createStatement();
            rs=st.executeQuery(req);
            while(rs.next())
            {
               list.add(new Sponsor(rs.getInt("id"),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7)));
              
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSponsor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Sponsor afficher_id(int id) {
         String req="select * from sponsor where id=?";
        Sponsor s=null;
        try {
            pst=cnx.prepareStatement(req);
            pst.setInt(1,id);
            rs = pst.executeQuery();
             if (rs.next()) {              
                    s = new Sponsor(rs.getInt(1), rs.getString(2), 
                          rs.getString(3), rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7));
                }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSponsor.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        return s;
    }
///////////////////////////////////////////////////
    
 public  List<Sponsor> recherchermutli(String critere, String col, String ordre)
       {
             List<Sponsor> lm =new ArrayList<>();
        try {
            System.out.println(critere);
            st=cnx.createStatement();
           // System.out.println(" SELECT * FROM  magasin where nom like '%"+critere+"%' or adresse like '%"+critere+"%' or cast(nombrebloc as char) like '%"+critere+"%' order by "+col+" "+ordre);
            String query=" SELECT * FROM  sponsor where nom like '%"+critere+"%' or type like '%"+critere+"%' or adresse like '%"+critere+"%' or cast(tel as char) like '%"+critere+"%' or email like '%"+critere+"%' order by "+col+" "+ordre;
           
                rs=st.executeQuery(query);
                while(rs.next()){
                Sponsor s =new Sponsor();
               
                 s.setNom(rs.getString("nom"));
                 s.setType(rs.getString("type"));
               
                s.setAdresse(rs.getString("Adresse"));
              s.setTel(rs.getInt("tel"));
               s.setEmail(rs.getString("email"));
                
              

                
                lm.add(s);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lm ;
       }
    
    
    
    
//    @Override
//    public void ajouter(Sponsor s) {
//        try {
//           
//            
//            st=cnx.createStatement();
//            
//            
//            String query="INSERT INTO sponsor (nom, type, adresse, tel, email) VALUES "
//                    + "('"+s.getNom()+"',"
//                    + "'"+s.getType()+"',"
//                    + "'"+s.getAdresse()+"',"
//                    + "'"+s.getTel()+"',"
//                    + "'"+s.getEmail()
//                    +"')";
//            st.executeUpdate(query);
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceSponsor.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//        public void ajouterpst(Sponsor t) {
//       String req = "insert into sponsor (nom,type,adresse,tel,email,evenement_id_ev) values (?,?,?,?,?,?)";
//        try {
//            pst = cnx.prepareStatement(req);
//            pst.setString(1, t.getNom());
//            pst.setString(2, t.getType());
//            pst.setString(3, t.getAdresse());
//            pst.setInt(4, (int) t.getTel());
//            pst.setString(5, t.getEmail());
//            pst.setInt(6, t.getEvennement().getIdEv());
//            pst.executeUpdate();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceSponsor.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//    @Override
//    public void supprimer(int id) {
//        try {
//       
//            st=cnx.createStatement();
//            String query="DELETE FROM sponsor WHERE idS="+id;
//            st.executeUpdate(query);
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceSponsor.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    @Override
//    public void modifier(int id_amodifier, Sponsor modifier) {
//        try {
//           
//            st=cnx.createStatement();
//            String query="UPDATE sponsor SET `nom`='"+modifier.getNom()+"',"
//                    + "`type`='"+modifier.getType()+"',"
//                    + "`adresse`='"+modifier.getAdresse()+"',"
//                    + "`tel`='"+modifier.getTel()+"',"
//                    + "`email`='"+modifier.getEmail()+"',"
//                    + "`evenement_id_ev`='"+modifier.getEvennement().getIdEv()+"'"
//                    + "WHERE idS="+id_amodifier;
//            st.executeUpdate(query);
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceSponsor.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    @Override
//    public List<Sponsor> afficher() {
//        ServiceEvennement se=new ServiceEvennement();
//        List<Sponsor> ls =new ArrayList<>();
//        try {
//          
//            st=cnx.createStatement();
//            
//            String query="SELECT * FROM sponsor";
//            rs=st.executeQuery(query);
//            while(rs.next()){
//                Sponsor s=new Sponsor();
//                Evennement e=se.rechercheParId(rs.getInt(7));
//                s.setIdS(rs.getInt("idS"));
//                s.setNom(rs.getString("nom"));
//                s.setType(rs.getString("type"));
//                s.setAdresse(rs.getString("adresse"));
//                s.setTel(rs.getInt("tel"));
//                s.setEmail(rs.getString(5));
//                s.setEvennement(e);
//                ls.add(s);
//            }
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceSponsor.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return ls;
//    }
//
//    
//    public Sponsor afficherById(int id) {
//      
//        ServiceEvennement se=new ServiceEvennement();
//        Sponsor s=new Sponsor();
//        try {
//        
//            st=cnx.createStatement();
//            
//            String query="SELECT * FROM sponsor WHERE idS="+id;
//            rs=st.executeQuery(query);
//            while(rs.next()){
//                
//                Evennement e=se.rechercheParId(rs.getInt(7));
//                s.setIdS(rs.getInt("idS"));
//                s.setNom(rs.getString("nom"));
//                s.setType(rs.getString("type"));
//                s.setAdresse(rs.getString("adresse"));
//                s.setTel(rs.getInt("tel"));
//                s.setEmail(rs.getString("email"));
//                s.setEvennement(e);
//                
//            }
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceSponsor.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return s;
//    }
//     public Sponsor afficherrById(int id) {
//         ServiceEvennement se=new ServiceEvennement();
//            String query="SELECT * FROM sponsor WHERE idS=?";
//            Sponsor s=null;
//        try {
//                pst = cnx.prepareStatement(query);
//                pst.setInt(1,id);
//                rs = pst.executeQuery();
//                if (rs.next()){
//                    Evennement e=se.rechercheParId(rs.getInt(7));
//                    s = new Sponsor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6),e);
//                } 
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceSponsor.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return s;
//    }
//     public List<Sponsor> findByEvent(int id_event){
//         List<Sponsor> sponsors=afficher();
//         List<Sponsor> resultat=
//                 sponsors.
//                 stream().
//                 filter(s->s.getEvennement().getIdEv()==id_event)
//                 .collect(Collectors.toList());
//         return resultat;
//     }
  

  
}
