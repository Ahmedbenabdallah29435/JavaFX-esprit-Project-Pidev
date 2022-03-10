/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.sql.Date;

/**
 *
 * @author ahmed
 */
public class Personne {
    private int id; 
    private String nom; 
    private String prenom ;
    private Date datenaissance;
    private String adresse; 
    private String mail; 
    private int tel; 
    private String role;
    private String mdp;
    private String nomEquipe;
    private String etat;
    
  
    
    
    public Personne(){}
    public Personne(int id, String nom, String prenom, Date datenaissance, String adresse, String mail, int tel, String role,String mdp,String nomEquipe, String etat) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.adresse = adresse;
        this.mail = mail;
        this.tel = tel;
        this.role = role;
         this.mdp = mdp;
         this.nomEquipe=nomEquipe;
         this.etat=etat;
    }

    public Personne(String nom, String prenom, Date datenaissance, String adresse, String mail, int tel, String role,String mdp,String nomEquipe, String etat) {
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.adresse = adresse;
        this.mail = mail;
        this.tel = tel;
        this.role = role;
         this.mdp = mdp;
         this.nomEquipe=nomEquipe;
         this.etat=etat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getMail() {
        return mail;
    }

    public int getTel() {
        return tel;
    }

    public String getRole() {
        return role;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Personne{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", datenaissance=" + datenaissance + ", adresse=" + adresse + ", mail=" + mail + ", tel=" + tel + ", role=" + role + ", mdp=" + mdp + ", nomEquipe=" + nomEquipe + ", etat=" + etat + '}';
    }

   
    public Personne(int id, String nom, String prenom, Date datenaissance, String adresse, String mail, int tel) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.adresse = adresse;
        this.mail = mail;
        this.tel = tel;
        
    }
    
}
