/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioneve_spon;

import entity.Sponsor;
import entity.Evennement;
import java.sql.Date;
import java.time.LocalDate;
import service.ServiceSponsor;
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
//        Date d =Date.valueOf(LocalDate.now());
//        Evennement e=new Evennement("aa",d,"lieu",55,2);
       ServiceEvennement se=new ServiceEvennement();
//        se.ajouter(e);
//        System.out.println(se.afficher());
        Date d =Date.valueOf(LocalDate.now());
       // Sponsor s=new Sponsor("aa","eef","er",55223333,"ZEAZE");
       // ServiceSponsor ss=new ServiceSponsor();
        Evennement e=new Evennement("aa",d,"lieu",55,2,1);
        
       // se.ajouter(e);
       
       // ss.ajouter(s);
       // System.out.println(se.afficher());
      // System.out.println(ss.afficherById(1));
//        System.out.println(se.afficherById(2));
se.modifier(6,new Evennement("aa",d,"lieu",5,59,11)); 

   //ss.modifier(3,new Sponsor("aa","eef","er",55223333,"ZE"));     
    }
    
}
