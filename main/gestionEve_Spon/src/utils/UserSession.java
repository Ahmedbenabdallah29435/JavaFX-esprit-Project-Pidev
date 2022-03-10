/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.Personne;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author malak_6
 */
public final class UserSession {
    
    
     public static UserSession instance;

    private Personne utilisateur ;

    public UserSession(Personne utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Personne getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Personne utilisateur) {
        this.utilisateur = utilisateur;
    }

   

    public static UserSession getInstace() {
        return instance;
    }
    public static void setInstace(Personne utilisateur) {
        if(instance == null) {
            instance = new UserSession(utilisateur);
        }
    }
    
  
    
}
