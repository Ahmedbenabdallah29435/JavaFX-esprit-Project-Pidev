/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *

 */
public class Joueur {
    int idJoueur;
    String NomJoueur;
    String PrenomJoueur;
    Categorie categ;

    public Joueur(int idJoueur, String NomJoueur, String PrenomJoueur, Categorie categ) {
        this.idJoueur = idJoueur;
        this.NomJoueur = NomJoueur;
        this.PrenomJoueur = PrenomJoueur;
        this.categ = categ;
    }

    public Joueur() {}

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

    public Categorie getCateg() {
        return categ;
    }

    public void setCateg(Categorie categ) {
        this.categ = categ;
    }

    @Override
    public String toString() {
        return "Joueur{" + "idJoueur=" + idJoueur + ", NomJoueur=" + NomJoueur + ", PrenomJoueur=" + PrenomJoueur + ", categ=" + categ + '}';
    }


    
    
    
    
}
