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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 *
 */
public class JoueurCrud implements IJoueur<Joueur> {

    private ResultSet rs;

    @Override
    public boolean AjouterJoueur(Joueur j) {

        try {
            String requete = "INSERT INTO joueur(NomJoueur,PrenomJoueur,Categorie,DateDeNaissance,Age,Sexe,Ville,imgJoueur)" + "VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = Myconnexion.getInstance().getCnx().prepareStatement(requete);

            pst.setString(1, j.getNomJoueur());
            pst.setString(2, j.getPrenomJoueur());
            pst.setString(3, j.getCategorie());
            ////
            pst.setDate(4, j.getDateDeNaissance());
            pst.setInt(5, j.getAge());
            pst.setString(6, j.getSexe());
            pst.setString(7, j.getVille());
            pst.setString(8, j.getImgJ());
            /// 
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
            String requete = "UPDATE joueur SET NomJoueur =?,PrenomJoueur =?,Categorie =?,DateDeNaissance =?,Age =?,Sexe =?,Ville =?,imgJoueur=? WHERE idJoueur =?";
            PreparedStatement pst = Myconnexion.getInstance().getCnx().prepareStatement(requete);

            pst.setString(1, j.getNomJoueur());
            pst.setString(2, j.getPrenomJoueur());
            pst.setString(3, j.getCategorie());
            pst.setDate(4, j.getDateDeNaissance());
            pst.setInt(5, j.getAge());
            pst.setString(6, j.getSexe());
            pst.setString(7, j.getVille());
            pst.setString(8, j.getImgJ());
            pst.setInt(9, j.getIdJoueur());

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
            PreparedStatement pst = Myconnexion.getInstance().getCnx().prepareStatement(requete);
            pst.execute(requete);
            System.out.println("Joueur supprimée");

            return true;

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return false;
    }

    @Override
    public List<Joueur> AfficherJoueur(Joueur t) {
        List<Joueur> JoueurList = new ArrayList<>();
        try {
            String requete = "SELECT idJoueur,NomJoueur,PrenomJoueur,DateDeNaissance,Age,Sexe,Ville,imgJoueur,idCategorie,NomCategorie FROM joueur INNER JOIN categorie ON joueur.Categorie = categorie.idCategorie";
            Statement pst = Myconnexion.getInstance().getCnx().createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Joueur r = new Joueur();

                ImageView img = new ImageView();
                Image image;
                try {
                    if (rs.getString("imgJoueur") == null) {
                    } else if (rs.getString("imgJoueur") != null) {
                        image = new Image(new FileInputStream((rs.getString("imgJoueur"))));
                        img.setImage(image);
                        img.setPreserveRatio(true);
                        img.setFitWidth(50);
                        img.setFitHeight(50);
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                    img.setImage(new Image(getClass().getResource("/ressource/AvatarJ.png").toString()));
                }

                r.setIdJoueur(rs.getInt(1));
                r.setNomJoueur(rs.getString(2));
                r.setPrenomJoueur(rs.getString(3));
                r.setDateDeNaissance(rs.getDate(4));
                r.setAge(rs.getInt(5));
                r.setSexe(rs.getString(6));
                r.setVille(rs.getString(7));
                r.setImgJoueur(img);
                r.setCategorie(rs.getString(10));
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
    public List<Categorie> MaxCategorieInJoueur() {
        List<Categorie> myList = new ArrayList<Categorie>();
        try {
            String requete = "SELECT pr.Categorie, br.NomCategorie FROM joueur pr LEFT JOIN categorie br ON pr.Categorie = br.idCategorie WHERE pr.categorie = (SELECT MAX(categorie) FROM joueur)";
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

    public int countTotalHomme() {
        String req = "SELECT COUNT(*) as cu FROM joueur u WHERE sexe like '%Homme%'";
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
            ex.getStackTrace();
        }
        return cu;
    }

    public int countTotalFemme() {
        String req = "SELECT COUNT(*) as cu FROM joueur u WHERE sexe like '%Femme%'";
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
            ex.getStackTrace();
        }
        return cu;
    }

}
