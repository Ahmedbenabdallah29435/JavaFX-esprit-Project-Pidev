/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entity.Categorie;
import utils.Myconnexion;
import entity.Joueur;
import interfaces.IJoueur;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 
 */
public abstract class JoueurCrud implements IJoueur<Joueur> {
private ResultSet rs;
    @Override
    public boolean AjouterJoueur(Joueur j) {

    try {
    String requete = "INSERT INTO joueur(NomJoueur,PrenomJoueur,Categorie)"+ "VALUES (?,?,?)";
    PreparedStatement pst = Myconnexion.getInstance().getCnx().prepareStatement(requete);    
    
        
            pst.setString(1, j.getNomJoueur());
            pst.setString(2, j.getPrenomJoueur());
            pst.setInt(3, j.getCateg().getIdCategorie());
             
            pst.executeUpdate();
            
            System.out.println("Joueur ajouté!");
            return true;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

    @Override
   public boolean ModifierJoueur(Joueur j) {
        try {
            String requete = "UPDATE joueur SET NomJoueur =? ,  PrenomJoueur =? , Categorie =? WHERE idJoueur =?";
            PreparedStatement pst = Myconnexion.getInstance().getCnx().prepareStatement(requete);
            
            pst.setString(1, j.getNomJoueur());
            pst.setString(2, j.getPrenomJoueur());
            pst.setInt(3, j.getCateg().getIdCategorie());
            pst.setInt(4, j.getIdJoueur());

            pst.executeUpdate();
            
            System.out.println("Joueur été modifiée");

            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
        ////
    }

    
    @Override
    public boolean SupprimerJoueur(int idJoueur) {
        try {
            String requete = "DELETE FROM joueur where idJoueur=" + String.valueOf(idJoueur) + "";
            PreparedStatement pst = Myconnexion.getInstance().getCnx().prepareStatement(requete);
            pst.execute(requete);
            System.out.println("Joueur supprimée");

            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;    }
    

    @Override
       public List<Joueur> AfficherJoueur(Joueur t) {
        List<Joueur> JoueurList = new ArrayList<>();
        try {
            String requete = "SELECT idJoueur,NomJoueur,PrenomJoueur,Categorie FROM joueur  ORDER BY idJoueur DESC";
            Statement pst = Myconnexion.getInstance().getCnx().createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Joueur r = new Joueur();
                r.setIdJoueur(rs.getInt(1));
                r.setNomJoueur(rs.getString(2));
                r.setPrenomJoueur(rs.getString(3));
                r.setCateg(t.getCateg());
                JoueurList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return JoueurList;
    }
       
     @Override  
    public List<Joueur> rechercheParFiltre(String type, String valeur) {
        List<Joueur> myList = new ArrayList<Joueur>();
        String requete = null;
        try {
            if (type.equals("Nom")) {
                requete = "SELECT * from joueur where NomJoueur like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Prenom")) {
                requete = "SELECT * from joueur where PrenomJoueur like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            }
            Statement pst = Myconnexion.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Joueur r = new Joueur();

                //r.setIdJoueur(rs.getInt("idJoueur"));
                r.setNomJoueur(rs.getString("NomJoueur"));
                r.setPrenomJoueur(rs.getString("PrenomJoueur"));
                //r.setCateg(rs.getString("Categorie"));

                myList.add(r);

            }

        } catch (SQLException ex) {
                        System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return myList;

    }
    
    
    
    

        
        @Override
        public List<Categorie> MaxCategorieInJoueur() {
        List<Categorie> myList = new ArrayList<Categorie>();
        try {
            String requete ="SELECT pr.Categorie, br.NomCategorie FROM joueur pr LEFT JOIN categorie br ON pr.Categorie = br.idCategorie WHERE pr.categorie = (SELECT MAX(categorie) FROM joueur)";
            Statement pst = Myconnexion.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Categorie c = new Categorie();
                c.setNomCategorie(rs.getString(2));            
                myList.add(c);
                break;
            }

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return myList;

    }
    
    
}
