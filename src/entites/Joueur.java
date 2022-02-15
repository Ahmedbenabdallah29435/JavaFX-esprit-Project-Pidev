/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author yassin
 */
public class Joueur {
    int idJoueur;
    String NomJoueur;
    String PrenomJoueur;
    int Categorie;

    public Joueur() {}
    
    public Joueur(int idJoueur, String NomJoueur, String PrenomJoueur, int Categorie) {
        this.idJoueur = idJoueur;
        this.NomJoueur = NomJoueur;
        this.PrenomJoueur = PrenomJoueur;
        this.Categorie = Categorie;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public String getNomJoueur() {
        return NomJoueur;
    }

    public void setNomJoueur(String NomJoueur) {
        this.NomJoueur = NomJoueur;
    }

    public String getPrenomJoueur() {
        return PrenomJoueur;
    }

    public void setPrenomJoueur(String PrenomJoueur) {
        this.PrenomJoueur = PrenomJoueur;
    }

    public int getCategorie() {
        return Categorie;
    }

    public void setCategorie(int Categorie) {
        this.Categorie = Categorie;
    }
    
    
    
    
}
