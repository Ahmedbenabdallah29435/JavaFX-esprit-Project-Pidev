/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entity.Personne;
import entity.Administrateur;
import entity.Fan;
import entity.Responsable;
import utils.Myconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author ahmed
 */
public class PersonneService implements PersonneInterface <Personne> {
        Connection cnx =Myconnexion.getInstance().getCnx();
    @Override
    public void ajouter(Personne t) throws SQLException{
        
    String req="INSERT INTO `personne`(`nom`, `prenom`, `datenaissance`, `adresse`, `mail`, `tel`, `role`,`mdp`,`nomEquipe`,`etat`) VALUES ('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getDatenaissance()+"','"+t.getAdresse()+"','"+t.getMail()+"','"+t.getTel()+"','"+t.getRole()+"','"+t.getMdp()+"','"+t.getNomEquipe()+"','"+t.getEtat()+"');";
        Statement st = null;
            
               
                st = cnx.createStatement();
           
                st.executeUpdate(req);
            }
          
      
    
    

    @Override
    public List<Personne> afficher() {
       List<Personne> Perso = new ArrayList<>();
        String sql ="select * from personne";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                Personne p = new Personne();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setDatenaissance(rs.getDate("datenaissance"));
                p.setAdresse(rs.getString("adresse"));
                p.setMail(rs.getString("mail"));
                p.setTel(rs.getInt("tel"));
                p.setRole(rs.getString("role"));
                p.setMdp(rs.getString("mdp"));
                p.setNomEquipe(rs.getString("nomEquipe"));
                p.setEtat(rs.getString("etat"));
                Perso.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Perso;
    }
    
     public void supprimer(int t) throws SQLException {
        
         
         String req="DELETE FROM `personne` WHERE id="+t+";";
   
        Statement st=cnx.createStatement();
        st.executeUpdate(req);
        
    }
    
    
    
        @Override
   public void modifier(Personne t) throws SQLException{

        String req="UPDATE `personne` SET `nom`='"+t.getNom()+"',`prenom`='"+t.getPrenom()+"',`datenaissance`='"+t.getDatenaissance()+"',`adresse`='"+t.getAdresse()+"',`mail`='"+t.getMail()+"',`tel`='"+t.getTel()+"',`role`='"+t.getRole()+"',`mdp`='"+t.getMdp()+"',`nomEquipe`='"+t.getNomEquipe()+"',`etat`='"+t.getEtat()+"' WHERE `id`='"+t.getId()+"';";
        Statement st=cnx.createStatement();
        st.executeUpdate(req);
     
     
     
   }

   // ************************** RECHERCHE ******************************************//
   
    @Override
    public List<Personne> rechercherParNom(String nom) throws SQLException {
     List<Personne> Perso = new ArrayList<>();   
    String req="SELECT * FROM `personne` WHERE nom='"+ nom+"'";
    
    try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(req);
            while(rs.next()){
                Personne p = new Personne();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setDatenaissance(rs.getDate("datenaissance"));
                p.setAdresse(rs.getString("adresse"));
                p.setMail(rs.getString("mail"));
                p.setTel(rs.getInt("tel"));
                p.setRole(rs.getString("role"));
                p.setMdp(rs.getString("mdp"));
                 p.setNomEquipe(rs.getString("nomEquipe"));
                 p.setNomEquipe(rs.getString("etat"));
                Perso.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     return Perso;
    }
    
    @Override
    public List<Personne> rechercherParPrenom(String prenom) throws SQLException {
     List<Personne> Perso = new ArrayList<>();   
    String req="SELECT * FROM `personne` WHERE email='"+ prenom+"'";
    
    try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(req);
            while(rs.next()){
                Personne p = new Personne();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setDatenaissance(rs.getDate("datenaissance"));
                p.setAdresse(rs.getString("adresse"));
                p.setMail(rs.getString("mail"));
                p.setTel(rs.getInt("tel"));
                p.setRole(rs.getString("role"));
                p.setMdp(rs.getString("mdp"));
                 p.setNomEquipe(rs.getString("nomEquipe"));
                 p.setNomEquipe(rs.getString("etat"));
                Perso.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     return Perso;
    }
    
    
        @Override
     public List<Personne> rechercherParAdresse(String adresse) throws SQLException {
     List<Personne> Perso = new ArrayList<>();   
    String req="SELECT * FROM `personne` WHERE adresse='"+ adresse+"'";
    
    try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(req);
            while(rs.next()){
                Personne p = new Personne();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setDatenaissance(rs.getDate("datenaissance"));
                p.setAdresse(rs.getString("adresse"));
                p.setMail(rs.getString("mail"));
                p.setTel(rs.getInt("tel"));
                p.setRole(rs.getString("role"));
                p.setMdp(rs.getString("mdp"));
                 p.setNomEquipe(rs.getString("nomEquipe"));
                 p.setNomEquipe(rs.getString("etat"));
                Perso.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     return Perso;
    }
      public List<Personne> rechercherParEmail(String email) throws SQLException {
     List<Personne> Perso = new ArrayList<>();   
    String req="SELECT * FROM `personne` WHERE mail='"+ email+"'";
    
    try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(req);
            while(rs.next()){
                Personne p = new Personne();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setDatenaissance(rs.getDate("datenaissance"));
                p.setAdresse(rs.getString("adresse"));
                p.setMail(rs.getString("mail"));
                p.setTel(rs.getInt("tel"));
                p.setRole(rs.getString("role"));
                p.setMdp(rs.getString("mdp"));
                 p.setNomEquipe(rs.getString("nomEquipe"));
                 p.setNomEquipe(rs.getString("etat"));
                Perso.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     return Perso;
    }
    
    // ************* AFFICHAGE COMPTES ACTIVES ET DESACTIVES *********************  //

    @Override
    public List<Personne> affichageActive() throws SQLException {
        
    List<Personne> Perso = new ArrayList<>();   
    String req="SELECT * FROM `personne` WHERE etat='"+"active" +"'";
    
    try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(req);
            while(rs.next()){
                Personne p = new Personne();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setDatenaissance(rs.getDate("datenaissance"));
                p.setAdresse(rs.getString("adresse"));
                p.setMail(rs.getString("mail"));
                p.setTel(rs.getInt("tel"));
                p.setRole(rs.getString("role"));
                p.setMdp(rs.getString("mdp"));
                 p.setNomEquipe(rs.getString("nomEquipe"));
                 p.setNomEquipe(rs.getString("etat"));
                Perso.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     return Perso;
    }

    @Override
    public List<Personne> affichageDesactive() throws SQLException {
        
     List<Personne> Perso = new ArrayList<>();   
    String req="SELECT * FROM `personne` WHERE etat='"+"desactive" +"'";
    
    try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(req);
            while(rs.next()){
                Personne p = new Personne();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setDatenaissance(rs.getDate("datenaissance"));
                p.setAdresse(rs.getString("adresse"));
                p.setMail(rs.getString("mail"));
                p.setTel(rs.getInt("tel"));
                p.setRole(rs.getString("role"));
                p.setMdp(rs.getString("mdp"));
                 p.setNomEquipe(rs.getString("nomEquipe"));
                 p.setNomEquipe(rs.getString("etat"));
                Perso.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     return Perso;
    
    
    
    }

    // ****************** nombre de comptes actives et desactives selon le role ************************//
    
    public String getRole(int id) throws SQLException {
        String req = "SELECT role from `personne` where id='" + id + "'";
        String role = null;

     try
    {     Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(req);
        
        while (rs.next()) {

             role = rs.getString("role");
            
        }
          
    } catch (SQLException ex)
    {
     System.out.println(ex.getMessage());   
    }
        return role;
    }
    
    
      // *********** TOTAL selon role ********************//  
      @Override
    public String totalFANActive() throws SQLException {
        
    String req = "SELECT  count(*) as total from `personne` where etat='" + "active" + "' and role='"+"fan"+"'" ;
     String total = null;   
     
     try
    {     Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(req);
        
        while (rs.next()) {

            total = rs.getString(1);
            System.out.println("Nombre des comptes fans actives "+total);
        }
          
    } catch (SQLException ex)
    {
     System.out.println(ex.getMessage());   
    }
        return total;
    }

   
    @Override
    public String totalFANDesactive() throws SQLException {
       
    
    String req = "SELECT count(*) from `personne` where etat='" + "desactive" + "' and role='"+"fan"+"'" ;
     String total = null;   
    try
    {     Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(req);
        
        while (rs.next()) {

            total = rs.getString(1);
            System.out.println("Nombre des comptes fans desactives "+total);
        }
          
    } catch (SQLException ex)
    {
     System.out.println(ex.getMessage());   
    }
        return total;
    
    
    }

    @Override
    public String totalADMINActive() throws SQLException {
       
     String req = "SELECT count(*) from `personne` where etat='" + "active" + "' and role='"+"admin"+"'" ;
     String total = null;   
    try
    {     Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(req);
        
        while (rs.next()) {

            total = rs.getString(1);
            System.out.println("Nombre des comptes administrateurs actives "+total);
        }
          
    } catch (SQLException ex)
    {
     System.out.println(ex.getMessage());   
    }
        return total;
    
    
    
    }

    @Override
    public String totalADMINdesactive() throws SQLException {
       
     String req = "SELECT count(*) from `personne` where etat='" + "desactive" + "' and role='"+"admin"+"'" ;
     String total = null;   
    try
    {     Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(req);
        
        while (rs.next()) {

            total = rs.getString(1);
            System.out.println("Nombre des comptes administrateurs desactives "+total);
        }
          
    } catch (SQLException ex)
    {
     System.out.println(ex.getMessage());   
    }
        return total;
    
    
    }

    @Override
    public String totalRESPOActive() throws SQLException {
       String req = "SELECT count(*) from `personne` where etat='" + "active" + "' and role='"+"respo"+"'" ;
     String total = null;   
    try
    {     Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(req);
        
        while (rs.next()) {

            total = rs.getString(1);
            System.out.println("Nombre des comptes responsables actives "+total);
        }
          
    } catch (SQLException ex)
    {
     System.out.println(ex.getMessage());   
    }
        return total; 
    
    
    }

    @Override
    public String totalRESPODesactive() throws SQLException {
       
     String req = "SELECT count(*) from `personne` where etat='" + "desactive" + "' and role='"+"respo"+"'" ;
     String total = null;   
    try
    {     Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(req);
        
        while (rs.next()) {

            total = rs.getString(1);
            System.out.println("Nombre des comptes responsables desactives "+total);
        }
          
    } catch (SQLException ex)
    {
     System.out.println(ex.getMessage());   
    }
        return total;
    
    }
   
   
   // **************** TRI ************************ //
     public List<Personne> TrierParPrenom() throws SQLException {
        List<Personne> Perso = new ArrayList<>();   
    String req="SELECT * FROM `personne` ORDER BY `prenom` asc";
    
    try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(req);
            while(rs.next()){
                Personne p = new Personne();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setDatenaissance(rs.getDate("datenaissance"));
                p.setAdresse(rs.getString("adresse"));
                p.setMail(rs.getString("mail"));
                p.setTel(rs.getInt("tel"));
                p.setRole(rs.getString("role"));
                p.setMdp(rs.getString("mdp"));
                 p.setNomEquipe(rs.getString("nomEquipe"));
                 p.setEtat(rs.getString("etat"));
                Perso.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     return Perso;
    }
    
    public List<Personne> TrierParAdresse() throws SQLException {
      List<Personne> Perso = new ArrayList<>();   
    String req="SELECT * FROM `personne` ORDER BY `adresse` asc";
    
    try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(req);
            while(rs.next()){
                Personne p = new Personne();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setDatenaissance(rs.getDate("datenaissance"));
                p.setAdresse(rs.getString("adresse"));
                p.setMail(rs.getString("mail"));
                p.setTel(rs.getInt("tel"));
                p.setRole(rs.getString("role"));
                p.setMdp(rs.getString("mdp"));
                 p.setNomEquipe(rs.getString("nomEquipe"));
                 p.setEtat(rs.getString("etat"));
                Perso.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     return Perso;
    }
    
    public List<Personne> TrierParNOM() throws SQLException {
        List<Personne> Perso = new ArrayList<>();   
    String req="SELECT * FROM `personne` ORDER BY `nom` asc";
    
    try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(req);
            while(rs.next()){
                Personne p = new Personne();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setDatenaissance(rs.getDate("datenaissance"));
                p.setAdresse(rs.getString("adresse"));
                p.setMail(rs.getString("mail"));
                p.setTel(rs.getInt("tel"));
                p.setRole(rs.getString("role"));
                p.setMdp(rs.getString("mdp"));
                 p.setNomEquipe(rs.getString("nomEquipe"));
                 p.setEtat(rs.getString("etat"));
                Perso.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     return Perso;
    }
    
    public List<Personne> TrierParEmail() throws SQLException {
      List<Personne> Perso = new ArrayList<>();   
    String req="SELECT * FROM `personne` ORDER BY `mail` asc";
    
    try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(req);
            while(rs.next()){
                Personne p = new Personne();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setDatenaissance(rs.getDate("datenaissance"));
                p.setAdresse(rs.getString("adresse"));
                p.setMail(rs.getString("mail"));
                p.setTel(rs.getInt("tel"));
                p.setRole(rs.getString("role"));
                p.setMdp(rs.getString("mdp"));
                 p.setNomEquipe(rs.getString("nomEquipe"));
                 p.setEtat(rs.getString("etat"));
                Perso.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     return Perso;
    }
     
// ********* Authentification ************************* //
    
    public Personne authentifier(String email, String mdp) throws SQLException {
        String req = "select * from `personne` WHERE mail=? and mdp=?";
        PreparedStatement steprep = cnx.prepareStatement(req);
        steprep.setString(1, email);
        steprep.setString(2,mdp);

        ResultSet rs = steprep.executeQuery();

        Personne u = null;
        if (rs.next()) {
             
           u=new Personne(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11));
                
        }

        return u;
    }

    
    // ******* UPDATE ****************** //
    
    public boolean updateName(Personne p, String newValue) {
        Boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("update personne set nom=? where id = ? ");
            if (newValue.matches("[a-zA-Z]+")) {
                req.setString(1, newValue);
                req.setInt(2, p.getId());
                req.executeUpdate();
                ok = true;
            } else {
                JOptionPane.showMessageDialog(null, "Le Nom ne peut pas être que des lettres !", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ok;
    
}
 
    
    public boolean updatePrenom(Personne p, String newValue) {
        Boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("update personne set prenom=? where id = ? ");
            if (newValue.matches("[a-zA-Z]+")) {
                req.setString(1, newValue);
                req.setInt(2, p.getId());
                req.executeUpdate();
                ok = true;
            } else {
                JOptionPane.showMessageDialog(null, "Le Prenom ne peut pas être que des lettres !", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ok;
    
}
    
    
     public boolean updateDate(Personne p, String newValue) {
        Boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("update personne set datenaissance=? where id = ? ");
            if (newValue.matches("[1-9]+")) {
                req.setString(1, newValue);
                req.setInt(2, p.getId());
                req.executeUpdate();
                ok = true;
            } else {
                JOptionPane.showMessageDialog(null, "Le Prenom ne peut pas être que des lettres !", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ok;
    
}
     public boolean updateadresse(Personne p, String newValue) {
        Boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("update personne set adresse=? where id = ? ");
            if (newValue.matches("[a-zA-Z]+")) {
                req.setString(1, newValue);
                req.setInt(2, p.getId());
                req.executeUpdate();
                ok = true;
            } else {
                JOptionPane.showMessageDialog(null, "Le'adresse ne peut pas être que des lettres !", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ok;
    
}
        public boolean updatenomEquipe(Personne p, String newValue) {
        Boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("update personne set nomEquipe=? where id = ? ");
            if (newValue.matches("[a-zA-Z]+")) {
                req.setString(1, newValue);
                req.setInt(2, p.getId());
                req.executeUpdate();
                ok = true;
            } else {
                JOptionPane.showMessageDialog(null, "Le nom Equipe ne peut pas être que des lettres !", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ok;
    
}
        public boolean updatemail(Personne p, String newValue) {
        Boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("update personne set mail=? where id = ? ");
           // PreparedStatement req = cnx.prepareStatement("update personne set nomEquipe=? where id = ? ");
            if (newValue.matches("[a-zA-Z1-9]+")){
                req.setString(1, newValue);
                req.setInt(2, p.getId());
                req.executeUpdate();
                ok = true;
            } else {
                JOptionPane.showMessageDialog(null, "Le nom Equipe ne peut pas être que des lettres !", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ok;
    
}
        public boolean updatemdp(Personne p, String newValue) {
        Boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("update personne set mdp=? where id = ? ");
            if (newValue.matches("[a-zA-Z]+")) {
                req.setString(1, newValue);
                req.setInt(2, p.getId());
                req.executeUpdate();
                ok = true;
            } else {
                JOptionPane.showMessageDialog(null, "Le mot de passe ne peut pas être que des lettres !", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ok;
    
}
    
    
        
       
        
        
        
         public void modifierP(Personne t) throws SQLException{

        String req="UPDATE `personne` SET `nom`='"+t.getNom()+"',`prenom`='"+t.getPrenom()+"',`datenaissance`='"+t.getDatenaissance()+"',`adresse`='"+t.getAdresse()+"',`mail`='"+t.getMail()+"',`tel`='"+t.getTel()+"' WHERE `id`='"+t.getId()+"';";
        Statement st=cnx.createStatement();
        st.executeUpdate(req);
     
     
     
   }
        
        
        
        
        
        
        
        
        
}
    
    
    
    
    
    
    
    
    
        
    

