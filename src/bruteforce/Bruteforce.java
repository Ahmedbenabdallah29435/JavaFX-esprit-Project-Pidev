/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bruteforce;

import Service.CategorieCrud;
import Service.JoueurCrud;
import entity.Categorie;
import entity.Joueur;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * 
 */
//kif tjeneri javafix yjibou howa
public class Bruteforce extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Categorie c= new Categorie(1,"lol");
         CategorieCrud p=new CategorieCrud();
         p.AjouterCategorie(c);
        // p.SupprimerCategorie(2);
        // p.ModifierCategorie(c); 
        
        for(Categorie pd : p.AfficherCategorie(c))
        {
         System.out.println("IdCategorie=>>> "+pd.getIdCategorie()+" NomCategorie=>>> "+pd.getNomCategorie());
        }
        System.out.println("\n"); 
        System.out.println("+-+-+-+-+-+-+Metier-+-+-+-+-+-+  ===>>>>>  Categorie");
        for(Categorie pdd : p.rechercheCategorie("lo"))
        {
               System.out.println("Found ==>>>>> "+pdd.getNomCategorie());   // Total Categorie
        }
        System.out.println("NombreTotalCategorie ==>>>>> "+p.countTotalCatgeorie());   // Total Categorie   
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
  //+-+-+-+-+-+-+-+//+-+-+-+-+-+-+-+//+-+-+-+-+-+-+-+//+-+-+-+-+-+-+-+//+-+-+-+-+-+-+-+       
        Categorie ccc= new Categorie(1);
        Joueur j=new  Joueur(13,"rania1","aouadi",ccc);
        JoueurCrud ok= new JoueurCrud() {};
        ok.AjouterJoueur(j);
        //ok.ModifierJoueur(j);
        //ok.SupprimerJoueur(12);
          
        System.out.println("\n");
        
        for(Joueur pd : ok.AfficherJoueur(j))
        {
               System.out.println("IdJoueur =>> "+pd.getIdJoueur()+" NomJoueur =>>"+pd.getNomJoueur()+" PrenomJoueur ==>> "+pd.getPrenomJoueur());
        }
        System.out.println("\n");
    
        System.out.println("+-+-+-+-+-+-+Metier-+-+-+-+-+-+  ===>>>>>  Joueur");
        for(Joueur pdd : ok.rechercheParFiltre("Prenom","ay")) // Nom ou Prenom
        {
        System.out.println("Found ==>>>>> "+pdd.getNomJoueur()+" "+pdd.getPrenomJoueur()); 
        }
        
        for(Categorie pd : ok.MaxCategorieInJoueur())
        {
         System.out.println("MaxCategorieInJoueur =>>> "+pd.getNomCategorie());
        }
        
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        
    }
    
    
    
    
}
