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
public class Fan extends Personne{
    

    public Fan(){}
    public Fan( int id, String nom, String prenom, Date datenaissance, String adresse, String mail, int tel,String mdp,String nomEquipe,String etat) {
        super(id, nom, prenom, datenaissance, adresse, mail, tel,"fan",mdp,"",etat);
        
    }

    public Fan( String nom, String prenom, Date datenaissance, String adresse, String mail, int tel, String role,String mdp,String nomEquipe,String etat) {
        super(nom, prenom, datenaissance, adresse, mail, tel, "fan",mdp,"",etat);
      
    }

   
    
    
}
