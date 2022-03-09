/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import entity.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.Myconnexion;

/**
 *
 * @author Espace Sboui
 */
public class SReservation implements Iservice1<Reservation> {
     //Connection cnx=MaConnexion.getInstance().getCnx();
     Connection cnx=Myconnexion.getInstance().getCnx();
     //Connection conx;
    Statement stm=null;
    
    public void ajouter(Reservation r) throws SQLException {
      String req = "INSERT INTO `reservation` (`nom`,`idr`,`prenom`,`age` ) VALUES ( '"
                + r.getNom() + "','" + r.getIdr()+ "','"+r.getPrenom()+"','"+r.getAge()+"') ";
        
             stm = cnx.createStatement();
       stm.executeUpdate(req);
    }

    @Override
    public List<Reservation> afficher() {
        List<Reservation> res = new ArrayList<>();
        String sql ="select * from reservation";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                Reservation r = new Reservation();
                r.setId(rs.getInt("id"));
                r.setNom(rs.getString("nom"));
                r.setPrenom(rs.getString("prenom"));
                r.setAge(rs.getInt("age"));
                r.setIdr(rs.getInt("idr"));
                res.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return res;
    }

    
   /* public void supprimer(Reservation r) {
        String requete = "DELETE FROM reservation WHERE id=?";
        try {
            
            PreparedStatement pst = MaConnexion.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,r.getId());
            pst.executeUpdate();
            System.out.println("reservation supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        
    }
    
    }*/
    public void supprimer(int t) throws SQLException {
        
         
         String req="DELETE FROM reservation WHERE id="+t+";";
   
        Statement st=cnx.createStatement();
        st.executeUpdate(req);
        
    }
   /* public void update(Reservation r) {
        
    String sql="update reservation set  nom=?  where id='"+r.getId()+"'";
            try {
            PreparedStatement ste =cnx.prepareStatement(sql);
            ste.setString(1, r.getNom());
            
            
           ste.executeUpdate();
            System.out.println("reservation Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
    public void modifier(Reservation r) throws SQLException{

        String req="UPDATE reservation SET `nom`='"+r.getNom()+"' WHERE `id`='"+r.getId()+"';";
        Statement st=cnx.createStatement();
        st.executeUpdate(req);
    }
    
}

    


