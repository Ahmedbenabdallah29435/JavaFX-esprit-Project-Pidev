/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author Mortadha
 */
public class Evennement {
    private int idEv;
    private String nom;
    private Date date;
    private String lieu;
    private float prix;
    private int nbre_particip;

    public Evennement() {
    }

    public Evennement(String nom, Date date, String lieu, float prix, int nbre_particip) {
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
        this.prix = prix;
        this.nbre_particip = nbre_particip;
    }

    public Evennement(int idEv, String nom, Date date, String lieu, float prix, int nbre_particip) {
        this.idEv = idEv;
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
        this.prix = prix;
        this.nbre_particip = nbre_particip;
    }
    
    public int getIdEv() {
        return idEv;
    }

    public void setIdEv(int idEv) {
        this.idEv = idEv;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getNbre_particip() {
        return nbre_particip;
    }

    public void setNbre_particip(int nbre_particip) {
        this.nbre_particip = nbre_particip;
    }

    @Override
    public String toString() {
        return "Evennement{" + "idEv=" + idEv + ", nom=" + nom + ", date=" + date + ", lieu=" + lieu + ", prix=" + prix + ", nbre_particip=" + nbre_particip + '}'+"\n";
    }
    
    
}
