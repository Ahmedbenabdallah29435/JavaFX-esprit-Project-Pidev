/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author Radhouan
 */
public class Reclamation {
    private int idR ;
    private String nom ;
    private String description ;
    private String type ;
    private Date date ;
    public java.sql.Date getDate;

    public Reclamation() {
    }

    public Reclamation(int idR, String nom, String description, String type, Date date) {
        this.idR = idR;
        this.nom = nom;
        this.description = description;
        this.type = type;
        this.date = date;
    }

    public Reclamation(String nom, String description, String type, Date date) {
        this.nom = nom;
        this.description = description;
        this.type = type;
        this.date = date;
    }

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "idR=" + idR + ", nom=" + nom + ", description=" + description + ", type=" + type + ", date=" + date + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
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
        final Reclamation other = (Reclamation) obj;
        if (this.idR != other.idR) {
            return false;
        }
        return true;
    }
    
    
}
