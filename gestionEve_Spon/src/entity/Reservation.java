/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Espace Sboui
 */
public class Reservation {
    private int id;
    private String nom;
    private int idr;
    private String prenom;
    private int age;
    public Reservation() {
    }

    public Reservation(String nom, int idr) {
        this.nom = nom;
        this.idr=idr;
    }

    public Reservation(int id, String nom, int age,String prenom,int idr) {
        this.id = id;
        this.nom = nom;
        this.prenom=prenom;
        this.age=age;
        this.idr=idr;
    }
     public Reservation( String nom, int age,String prenom,int idr) {
       
        this.nom = nom;
        this.prenom=prenom;
        this.age=age;
        this.idr=idr;
    }

    public int getIdr() {
        return idr;
    }

    public void setIdr(int idr) {
        this.idr = idr;
    }
    

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", nom=" + nom + ", idr=" + idr + ", prenom=" + prenom + ", age=" + age + '}';
    }

  
   
    
}
