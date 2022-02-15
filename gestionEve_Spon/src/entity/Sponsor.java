/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author AhmedBenAbdallah
 */
public class Sponsor {
    private int idS;
    private String nom;
    private String type;
    private String adresse;
    private int tel;
    private String email;

    public Sponsor() {
    }

    public Sponsor(String nom, String type, String adresse, int tel, String email) {
        this.nom = nom;
        this.type = type;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
    }

    public Sponsor(int idS, String nom, String type, String adresse, int tel, String email) {
        this.idS = idS;
        this.nom = nom;
        this.type = type;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
    }
    
    
     
    
    
    public int getIdS() {
        return idS;
    }

    public void setIdS(int idS) {
        this.idS = idS;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Sponsor{" + "idS=" + idS + ", nom=" + nom + ", type=" + type + ", adresse=" + adresse + ", tel=" + tel + ", email=" + email + '}'+"/n";
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Sponsor other = (Sponsor) obj;
        if (this.idS != other.idS) {
            return false;
        }
        return true;
    }
    
}
