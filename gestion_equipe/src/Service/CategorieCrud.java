/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import connection.cnx;
import interfaces.ICategorie;
import entites.Categorie;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
/**
 *
 * @author yassin
 */
public class CategorieCrud implements ICategorie<Categorie>  {

    @Override
    public boolean AjouterCategorie(Categorie c) {
    try {
    String requete = "INSERT INTO categorie(idCategorie,NomCategorie)"+ "VALUES (?,?)";
    PreparedStatement pst = cnx.getInstance().getCnx().prepareStatement(requete);        
            pst.setInt(1, c.getIdCategorie()); 
            pst.setString(2, c.getNomCategorie());
            
            pst.executeUpdate();
            
            System.out.println("Categorie ajouté!");
            return true;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

    @Override
    public boolean ModifierCategorie(Categorie c) {
        try {
            String requete = "UPDATE categorie SET NomCategorie=? WHERE idCategorie=?";
            PreparedStatement pst = cnx.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, c.getIdCategorie());
            pst.setString(2, c.getNomCategorie());

            pst.executeUpdate();
            
            System.out.println("Categorie été modifiée");

            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

    @Override
    public boolean SupprimerCategorie(int idCategorie) {
        try {
            String requete = "DELETE FROM categorie where idCategorie=" + String.valueOf(idCategorie) + "";
            PreparedStatement pst = cnx.getInstance().getCnx().prepareStatement(requete);
            pst.execute(requete);
            System.out.println("Categorie supprimée");

            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }
    
}
