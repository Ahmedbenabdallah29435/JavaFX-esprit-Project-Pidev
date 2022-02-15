/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioneve_spon;

import entity.Evennement;
import java.sql.Date;
import java.time.LocalDate;
import service.ServiceEvennement;
import utils.Myconnexion;

/**
 *
 * @author Mortadha
 */
public class GestionEve_Spon {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Date d =Date.valueOf(LocalDate.now());
        Evennement e=new Evennement("aa",d,"lieu",55,2);
        ServiceEvennement se=new ServiceEvennement();
        se.ajouter(e);
        System.out.println(se.afficher());
        
    }
    
}
