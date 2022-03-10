/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Ahmed
 */
public class Evennement {
    private int idEv;
    private String nom;
    private Date date;
    private String lieu;
    private String description;
    private int nb_place;
    private String imageE;
    private Sponsor sponsor;

    public Evennement() {
    }

    public Evennement(int idEv, String nom, Date date, String lieu, String description, int nb_place, String imageE, Sponsor sponsor) {
        this.idEv = idEv;
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
        this.description = description;
        this.nb_place = nb_place;
        this.imageE = imageE;
        this.sponsor = sponsor;
    }

    public Evennement(String nom, Date date, String lieu, String description, int nb_place, String imageE, Sponsor sponsor) {
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
        this.description = description;
        this.nb_place = nb_place;
        this.imageE = imageE;
        this.sponsor = sponsor;
    }

    public Evennement(String nom, Date date, String lieu, String description, int nb_place, String imageE) {
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
        this.description = description;
        this.nb_place = nb_place;
        this.imageE = imageE;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNb_place() {
        return nb_place;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }

    public String getImageE() {
        return imageE;
    }

    public void setImageE(String imageE) {
        this.imageE = imageE;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evennement other = (Evennement) obj;
        if (this.idEv != other.idEv) {
            return false;
        }
        return true;
    }

  

  


  

  @Override
    public String toString() {
        return "Evennement{" + "idEv=" + idEv + ", nom=" + nom + ", date=" + date + ", lieu=" + lieu + ", description=" + description + ", nb_place=" + nb_place + ", imageE=" + imageE + ", sponsor=" + sponsor + '}';
    }

        
}
