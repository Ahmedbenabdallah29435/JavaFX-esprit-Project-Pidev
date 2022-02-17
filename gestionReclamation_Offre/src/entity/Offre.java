package entity;

import java.util.Date;

/**
 *
 * @author Radhouan
 */
public class Offre {
    private int idO ;
    private String nom ;
    private String prenom ;
    private String type ;
    private Date date  ;
    private int nbre ;
    public java.sql.Date getDate;

public Offre() {
}

public Offre(int idO, String nom, String prenom, String type, Date date, int nbre, java.sql.Date getDate) {
    this.idO = idO; 
    this.nom = nom;
    this.prenom = prenom;
    this.type = type;
    this.date = date;
    this.nbre = nbre;
    }

public Offre(String nom, String prenom, String type, Date date, int nbre) {
    this.nom = nom;
    this.prenom = prenom;
    this.type = type;
    this.date = date;
    this.nbre = nbre;
    }

public int getIdO() {
    return idO;
    }

public void setIdO(int idO) {
    this.idO = idO;
    }

public String getNom() {
    return nom;
    }

public void setNom(String nom) {
    this.nom = nom;
    }

public String getPrenom() {
    return prenom;
    }

public void setPrenom(String prenom) {
    this.prenom = prenom;
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

public int getNbre() {
    return nbre;
    }

public void setNbre(int nbrr) {
    this.nbre = nbre;
    }

    @Override
    public String toString() {
        return "Offre{" + "idO=" + idO + ", nom=" + nom + ", prenom=" + prenom + ", type=" + type + ", date=" + date + ", nbre=" + nbre + ", getDate=" + getDate + '}';
    }



    @Override
    public int hashCode() {
        int hash = 7;
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
        final Offre other = (Offre) obj;
        if (this.idO != other.idO) {
            return false;
        }
        return true;
    }



    
}
