/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Ahmed
 */
public class Sponsor {
    private int id;
    private String nom;
    private String type;
    private String adresse;
    private int tel;
    private String email;
    private String imgS;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImgS() {
        return imgS;
    }

    public void setImgS(String imgS) {
        this.imgS = imgS;
    }

    public Sponsor() {
    }

    public Sponsor(int id, String nom, String type, String adresse, int tel, String email, String imgS) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
        this.imgS = imgS;
    }

    public Sponsor(String nom, String type, String adresse, int tel, String email, String imgS) {
        this.nom = nom;
        this.type = type;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
        this.imgS = imgS;
    }

    @Override
    public String toString() {
        return "Sponsor{" + "idS=" + id + ", nom=" + nom + ", type=" + type + ", adresse=" + adresse + ", tel=" + tel + ", email=" + email + ", imgS=" + imgS + '}';
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
        final Sponsor other = (Sponsor) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
