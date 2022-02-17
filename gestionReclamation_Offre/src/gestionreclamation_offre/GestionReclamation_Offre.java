/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionreclamation_offre;
import java.sql.Date;
import java.time.LocalDate;
import entity.Reclamation;
import entity.Offre;
import service.ServiceReclamation;
import service.ServiceOffre;
/**
 *
 * @author Radhouan
 */
public class GestionReclamation_Offre {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServiceReclamation sr=new ServiceReclamation();
        ServiceOffre so=new ServiceOffre();
        // TODO code application logic here
        Date d =Date.valueOf(LocalDate.now());
        Reclamation r1=new Reclamation("g","cv","C",d);
        Reclamation r2=new Reclamation("h","cc","D",d);
        Offre o1=new Offre("h","cc","D",d,5);
        Offre o2=new Offre("g","cv","C",d,6);
        
      //sr.ajouter(r1);
      //so.ajouter(o1);
        so.supprimer(2);
        //sr.modifier(1,r2); 
        System.out.println(so.afficher());
        System.out.println(so.afficherById(1));
        System.out.println(sr.afficher());
        System.out.println(sr.afficherById(1));

            
    }
    
}
