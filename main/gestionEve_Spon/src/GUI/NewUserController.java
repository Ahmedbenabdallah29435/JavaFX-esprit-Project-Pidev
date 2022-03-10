/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.PersonneService;
import entity.Personne;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author malak_6
 */
public class NewUserController implements Initializable {

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
    private Button txtajouter;
    @FXML
    private Button txtacceuil;
    @FXML
    private ComboBox<String> txtrole;
    @FXML
    private TextField txtmail;
     ObservableList<String> list= FXCollections.observableArrayList("fan");
    ObservableList<String> list_etat= FXCollections.observableArrayList("active","desactive");
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
     Personne p=new Personne(nom,prenom,d,adresse,mail,tel,txtrole.getSelectionModel().getSelectedItem(),mdp,nomEquipe,txtetat.getSelectionModel().getSelectedItem());
      PersonneService sp=new PersonneService();
      
      sp.ajouter(p);
      /*
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Utilisateur ajouté avec Sucees!");
            alert.show();*/
      Notifications notificationBuilder=Notifications.create()
              .title("Alert").text("Utilisateur ajouuté avec succès").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
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

    @FXML
    private void retourAcceuil(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
   LoginController ap= loader.getController();
        txtacceuil.getScene().setRoot(root);
    }
    
}
