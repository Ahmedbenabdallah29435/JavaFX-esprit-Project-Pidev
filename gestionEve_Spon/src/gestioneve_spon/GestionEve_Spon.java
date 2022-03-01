/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioneve_spon;

import entity.Evennement;
import entity.Sponsor;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import service.ServiceEvennement;
import service.ServiceSponsor;
import utils.Myconnexion;

/**
 *
 * @author Ahmed
 */
public class GestionEve_Spon {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        Date d =Date.valueOf(LocalDate.now());
        
        ServiceEvennement se=new ServiceEvennement();
        ServiceSponsor ss=new ServiceSponsor();
         
        SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
           String dob="1990-12-01";
           java.util.Date dn=sd.parse(dob);
           long ms=dn.getTime();
           java.sql.Date sdo=new java.sql.Date(ms);

//Sponsor s4=new Sponsor("d","d","e",5,"f","r");
//Sponsor s5=new Sponsor(91,"dd","d","e",5,"f","r");
//Evennement e1=new Evennement(71,"bbbb",d,"e","j",2,"y",s5);
Sponsor s4=new Sponsor("d","d","e",5,"f","r");
Sponsor s5=new Sponsor(91,"dd","d","e",5,"f","r");
Evennement e1=new Evennement(71,"bbbb",d,"e","j",2,"y",s5);
//se.ajouter(e1);
//se.modifier(e1); 
//se.supprimer(e1)
//
System.out.println(se.afficher());    
 //System.out.println(ss.afficher());    
//
// 
// Sponsor s7=new Sponsor(51,"dopo","d","e",5,"f","r");
////ss.ajouter(s7);
//ss.modifier(s7); 
//ss.supprimer(s7);
//
//System.out.println(se.afficher_id(73));    
 //System.out.println(ss.afficher_id(50));  



//Sponsor s=ss.afficherById(2);
        //se.affecterSponsoreEvenement(1, s);
        //System.out.println(ss.afficher());
        
    
//        System.out.println(ss.afficher());
        /*
        System.out.println("**************************");
        System.out.println(se.rechercheParId(7));
        System.out.println("**************************");
        System.out.println(se.orderByDate());
        */
        
        
    }
    
}
