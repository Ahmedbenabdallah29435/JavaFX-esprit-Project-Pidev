/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionreclamation_offre;
import java.sql.Date;
import java.time.LocalDate;
import entity.Reclamation;
import service.ServiceReclamation;

/**
 *
 * @author Radhouan
 */
public class GestionReclamation_Offre {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Date d =Date.valueOf(LocalDate.now());
        Reclamation r1=new Reclamation("g", "cv","C",d);
        ServiceReclamation sr=new ServiceReclamation();
      // sr.ajouter(r1);
      //sr.supprimer(4);
        sr.modifier(new Reclamation(6,"h", "c","B",d));
    }
    
}
