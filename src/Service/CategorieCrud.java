/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import utils.Myconnexion;
import interfaces.ICategorie;
import entity.Categorie;
import java.sql.PreparedStatement;//If you want to execute a Statement object many times, it usually reduces execution time to use a PreparedStatement object instead.
import java.sql.ResultSet;//A ResultSet object maintains a cursor pointing to its current row of data
import java.sql.SQLException;
import java.sql.Statement;//The object used for executing a static SQL statement and returning the results it produces.
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rania
 */
public class CategorieCrud implements ICategorie<Categorie>  {

    @Override
    public boolean AjouterCategorie(Categorie c) {
    try {
    String requete = "INSERT INTO categorie(idCategorie,NomCategorie)"+ "VALUES (?,?)";
    PreparedStatement pst = Myconnexion.getInstance().getCnx().prepareStatement(requete);        
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
            PreparedStatement pst = Myconnexion.getInstance().getCnx().prepareStatement(requete);
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
            PreparedStatement pst = Myconnexion.getInstance().getCnx().prepareStatement(requete);
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
    
    @Override
     public List<Categorie> AfficherCategorie(Categorie t) {
        List<Categorie> CategorieList = new ArrayList<>();
        try {
            String requete = "SELECT idCategorie,NomCategorie FROM categorie  ORDER BY idCategorie DESC";
            Statement pst = Myconnexion.getInstance().getCnx().createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Categorie r = new Categorie();
                r.setIdCategorie(rs.getInt(1));
                r.setNomCategorie(rs.getString(2));
                CategorieList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return CategorieList;
    }
     @Override
      public int countTotalCatgeorie() {
        String req = "SELECT COUNT(*) as cu FROM categorie  ";
        ResultSet rs = null;
        try {
            Statement ste = Myconnexion.getInstance().getCnx().createStatement();
            rs = ste.executeQuery(req);
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        int cu = 0;
        try {
            while (rs.next()) {
                cu = rs.getInt("cu");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return cu;
    }
      
      @Override
      public List<Categorie> rechercheCategorie(String valeur) {
        List<Categorie> myList = new ArrayList<Categorie>();
        String requete = null;
        try {
            requete = "SELECT * from categorie where NomCategorie like '%" + valeur + "%'"; 

            Statement pst = Myconnexion.getInstance().getCnx().prepareStatement(requete); 
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Categorie r = new Categorie();
                r.setIdCategorie(rs.getInt("idCategorie"));
                r.setNomCategorie(rs.getString("NomCategorie"));
                myList.add(r);
            }

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return myList;

    }
        
    
}
