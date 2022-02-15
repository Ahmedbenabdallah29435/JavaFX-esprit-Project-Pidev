/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import connection.cnx;
import entites.Joueur;
import interfaces.IJoueur;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author yassin
 */
public class JoueurCrud implements IJoueur<Joueur> {

    @Override
    public boolean AjouterJoueur(Joueur j) {

    try {
    String requete = "INSERT INTO joueur(idJoueur,NomJoueur,PrenomJoueur,Categorie)"+ "VALUES (?,?,?,?)";
    PreparedStatement pst = cnx.getInstance().getCnx().prepareStatement(requete);    
    
            pst.setInt(1, j.getIdJoueur()); 
            pst.setString(2, j.getNomJoueur());
            pst.setString(3, j.getPrenomJoueur());
            pst.setInt(4, j.getCategorie());
             
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
            String requete = "UPDATE joueur SET NomJoueur =? , PrenomJoueur=? , Categorie =? WHERE idJoueur=?";
            PreparedStatement pst = cnx.getInstance().getCnx().prepareStatement(requete);
            
            pst.setString(1, j.getNomJoueur());
            pst.setString(2, j.getPrenomJoueur());
            pst.setInt(3, j.getCategorie());
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
    }

    @Override
    public boolean SupprimerJoueur(int idJoueur) {
        try {
            String requete = "DELETE FROM joueur where idJoueur=" + String.valueOf(idJoueur) + "";
            PreparedStatement pst = cnx.getInstance().getCnx().prepareStatement(requete);
            pst.execute(requete);
            System.out.println("Joueur supprimée");

            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;    }
    
}
