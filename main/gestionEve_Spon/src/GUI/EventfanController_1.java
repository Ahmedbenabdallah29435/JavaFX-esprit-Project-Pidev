/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.PersonneService;
import entity.Personne;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.Myconnexion;

/**
 * FXML Controller class
 *
 * @author malak_6
 */
public class EventfanController_1 implements Initializable {

    @FXML
    private VBox afficheEvent;
    private PersonneService cp = new PersonneService();
      private Personne o;

     List<Personne> liste = new ArrayList <Personne>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       HBox item = new HBox();
                    afficheEvent.getChildren().add(item);
           liste=cp.afficher();
        int taille=liste.size();
          for( int i=0;i<taille;i++)
            {
               
                if(i % 3 == 0){
                    item = new HBox();
                    afficheEvent.getChildren().add(item);
                }
                VBox content = new VBox();
                Label title = new Label();
                o=liste.get(i);
                //Button btn1 =new Button("detail");
                Label nom = new Label((liste.get(i).getNom()));
                nom.setStyle("-fx-strikethrough: true");
                nom.getStyleClass().add("barre");
                Label prixpromo = new Label(liste.get(i).getPrenom());
                prixpromo.setStyle("-fx-font-weight: bold");
                Button btn = new Button("",content);
                System.out.println(o.getPrenom());
                Personne o1 = new Personne(o.getNom(),o.getPrenom(),o.getDatenaissance(),o.getAdresse(),o.getMail(),o.getTel(),o.getRole(),o.getMdp(),o.getNomEquipe(),o.getEtat());
                /* btn.setOnAction(event -> {
                try {
                
                //detailProduit(event);
                
                System.out.println("----------------------------");
                
                System.out.println(o1.getPrenom());
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AfficheDetailMedecin.fxml"));
                Parent root1 =(Parent) loader.load();
                Stage stage = new Stage();
                stage.setTitle("detail Medecin");
                stage.setScene(new Scene(root1));
                stage.show();
                
                /* ImageController dp=loader.getController();
                dp.setLb_idPatient(o.getId());*/
                
                /*  Scene scene = new Scene(loader.load());
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                
                stage.setScene(scene);
                
                stage.show();*/
                
                /*  AfficheDetailMedecinController controller = loader.<AfficheDetailMedecinController>getController();
                
                controller.initData(o1);
                
                
                
                } catch (IOException ex) {
                Logger.getLogger(AffichageListMedecinsImagesController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                });
                */
                btn.setPrefWidth(100);
                item.getChildren().add(btn);
                afficheEvent.setSpacing(20);
                item.setSpacing(20);
    }    
    }    
 
    
}
