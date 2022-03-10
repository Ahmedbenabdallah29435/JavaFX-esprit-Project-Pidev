/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.PersonneService;
import java.net.URL;
import java.util.ResourceBundle;
import static java.util.Collections.list;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import entity.Personne;
import java.io.IOException;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import org.controlsfx.control.Notifications;



/**
 * FXML Controller class
 *
 * @author malak_6
 */
public class AjoutPersonneController implements Initializable {

    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtprenom;
    @FXML
    private TextField txtadresse;
    @FXML
    private TextField txttel;
    @FXML
    private TextField txtmdp;
    @FXML
    private TextField txtnomEquipe;
    @FXML
    private ComboBox<String> txtetat;
    @FXML
    private DatePicker txtdatenaissance;
    @FXML
    private Button txtacceuil;
    @FXML
    private ComboBox<String> txtrole;
    @FXML
    private TextField txtmail;
    
    ObservableList<String> list= FXCollections.observableArrayList("fan");
    ObservableList<String> list_etat= FXCollections.observableArrayList("active","desactive");
    
    
    @FXML
    private Button txtajouter;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          txtrole.setItems(list); 
          txtetat.setItems(list_etat);
          
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
      String nom=txtnom.getText();
      String prenom=txtprenom.getText();
        Date d = Date.valueOf(txtdatenaissance.getValue());
        String adresse=txtadresse.getText(); 
        String mail=txtmail.getText();
     int tel=Integer.valueOf(txttel.getText());
     String mdp=txtmdp.getText();
    
     String nomEquipe=txtnomEquipe.getText();
     ObservableList<String> list=txtrole.getItems();
     ObservableList<String> list_etat=txtetat.getItems(); 
    // controle de saisie //
    
       if ( nom.isEmpty() || nom.matches("[0-9]") || txttel.getText().length()!=8 || txttel.getText().isEmpty() || txttel.getText().matches("[a-z]") || txtprenom.getText().isEmpty() || txtprenom.getText().matches("[0-9]") 
          || txtmdp.getText().isEmpty() ||  txtmail.getText().isEmpty()  ||  txtadresse.getText().isEmpty() || txtadresse.getText().matches("[0-9]") ){
           /* Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setContentText("Verifier champ!");
            alert.show(); */   
        Notifications notificationBuilder=Notifications.create()
              .title("Alert").text("verifier les champs ecrits").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER_LEFT)
              .onAction(new EventHandler<ActionEvent>(){
                  public void handle(ActionEvent event)
                      {
                          System.out.println("clicked ON");
                      }
              });
      notificationBuilder.darkStyle();
      notificationBuilder.show();
       }
       
      
       else {
    
          
     ////
     Personne p=new Personne(nom,prenom,d,adresse,mail,tel,txtrole.getSelectionModel().getSelectedItem(),mdp,nomEquipe,txtetat.getSelectionModel().getSelectedItem());
      PersonneService sp=new PersonneService();
      
      sp.ajouter(p);
      /*
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Utilisateur ajouté avec Sucees!");
            alert.show();*/
      Notifications notificationBuilder=Notifications.create()
              .title("Notifications").text("Utilisateur ajouuté avec succès").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER_LEFT)
              .onAction(new EventHandler<ActionEvent>(){
                  public void handle(ActionEvent event)
                      {
                          System.out.println("clicked ON");
                      }
              });
      notificationBuilder.darkStyle();
      notificationBuilder.show();
     
      
       } } 

    @FXML
    private void retourAcceuil(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("afficherPersonne.fxml"));
        Parent root = loader.load();
       AfficherPersonneController ap= loader.getController();
        txtacceuil.getScene().setRoot(root);
    
    }


   
}
