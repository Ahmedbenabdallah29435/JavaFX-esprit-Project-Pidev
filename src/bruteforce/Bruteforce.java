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
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * 
 */
//kif tjeneri javafix yjibou howa
public class Bruteforce extends Application {
    double xOffset, yOffset;
    public static Stage stage = null;
    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Joueur.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        this.stage = primaryStage;
        primaryStage.show();
        
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
      
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
       
//         Categorie c= new Categorie(1,"lol");
//         CategorieCrud p=new CategorieCrud();
//         p.AjouterCategorie(c);
//        // p.SupprimerCategorie(2);
//        // p.ModifierCategorie(c); 
//        
//        for(Categorie pd : p.AfficherCategorie(c)) //Afficher tableau Categorie
//        {
//         System.out.println("IdCategorie=>>> "+pd.getIdCategorie()+" NomCategorie=>>> "+pd.getNomCategorie());
//        }
//        System.out.println("\n"); 
//        System.out.println("+-+-+-+-+-+-+Metier-+-+-+-+-+-+  ===>>>>>  Categorie");
//        for(Categorie pdd : p.rechercheCategorie("lo"))
//        {
//               System.out.println("Found ==>>>>> "+pdd.getNomCategorie());   // Metier --> Rechercher selon le nom categorie
//        }
//        System.out.println("NombreTotalCategorie ==>>>>> "+p.countTotalCatgeorie()); // Metier --> Total Categorie dabs base de donnée
//        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
//  //+-+-+-+-+-+-+-+//+-+-+-+-+-+-+-+//+-+-+-+-+-+-+-+//+-+-+-+-+-+-+-+//+-+-+-+-+-+-+-+       
//        Categorie ccc= new Categorie(1);
//        Joueur j=new  Joueur(13,"rania1","aouadi",ccc);
//        JoueurCrud ok= new JoueurCrud() {};
//        ok.AjouterJoueur(j);
//        //ok.ModifierJoueur(j);
//        //ok.SupprimerJoueur(12);
//          
//        System.out.println("\n");
//        
//        for(Joueur pd : ok.AfficherJoueur(j))
//        {
//               System.out.println("IdJoueur =>> "+pd.getIdJoueur()+" NomJoueur =>>"+pd.getNomJoueur()+" PrenomJoueur ==>> "+pd.getPrenomJoueur());
//        }
//        System.out.println("\n");
//    
//        System.out.println("+-+-+-+-+-+-+Metier-+-+-+-+-+-+  ===>>>>>  Joueur");
//        for(Joueur pdd : ok.rechercheParFiltre("Prenom","ay")) // Metier --> Nom ou Prenom (filtre et recherche selon filtre)
//        {
//        System.out.println("Found ==>>>>> "+pdd.getNomJoueur()+" "+pdd.getPrenomJoueur()); 
//        }
//        
//        for(Categorie pd : ok.MaxCategorieInJoueur()) // Metier --> Show Nom Categorie plus utilisé dans table Joueur
//        {
//         System.out.println("MaxCategorieInJoueur =>>> "+pd.getNomCategorie());
//        }
//        
//        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
//        
//    }
//    
    
    
    
}
