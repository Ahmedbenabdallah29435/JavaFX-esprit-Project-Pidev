/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 *
 */
public class Categorie {
    int idCategorie;
    String NomCategorie;

    public Categorie() {}

    public Categorie(int idCategorie, String NomCategorie) {
        this.idCategorie = idCategorie;
        this.NomCategorie = NomCategorie;
    }
    
    public Categorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNomCategorie() {
        return NomCategorie;
    }

    public void setNomCategorie(String NomCategorie) {
        this.NomCategorie = NomCategorie;
    }

    @Override
    public String toString() {
        return "Categorie{" + "idCategorie=" + idCategorie + ", NomCategorie=" + NomCategorie + '}';
    }
   
}
