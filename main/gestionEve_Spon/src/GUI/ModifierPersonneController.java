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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import GUI.AfficherPersonneController;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Myconnexion;

/**
 * FXML Controller class
 *
 * @author malak_6
 */
public class ModifierPersonneController implements Initializable {

   
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
    
    ObservableList<String> list= FXCollections.observableArrayList("admin","respo","fan");
    ObservableList<String> list_etat= FXCollections.observableArrayList("active","desactive");
    @FXML
    private Button txtmodifier;
    private Boolean update;
    
    String query=null;
    @FXML
    private TextField txtid;
    
    private int idp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          txtrole.setItems(list); 
          txtetat.setItems(list_etat);
         
        
        
         
    }    

  

    @FXML
    private void retourAcceuil(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("DocFXML.fxml"));
        Parent root = loader.load();
        DocFXMLController ap= loader.getController();
        txtacceuil.getScene().setRoot(root);
    
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        
        
         
 
       query = "UPDATE `personne` SET "
                    + "`nom`=?,"
                    + "`prenom`=?,"
                    + "`datenaissance`=?,"
                    + "`adresse`=?,"
                    + "`mail`=?,"
                    + "`tel`=?,"
                    + "`role`=?,"
                    + "`mdp`=?,"
                    + "`nomEquipe`=?,"
                    + "`etat`= ? WHERE id = '"+idp+"'";
        
    insert();
    }
    
    
    void setUpdate(boolean b) {
        this.update = b;

    }
    
   public void setTextField(int id,String nom,String prenom,Date d,String adresse, String mail,int tel,String role,String mdp,String nomEquipe,String etat )
   {   idp=id;
       txtnom.setText(nom);
       txtprenom.setText(prenom);
       txtdatenaissance.setValue(d.toLocalDate());
       txtadresse.setText(adresse);
      
       txtmail.setText(mail);
       txtrole.setValue(role);
       txtmdp.setText(mdp);
       txtnomEquipe.setText(nomEquipe);
       txtetat.setValue(etat);
       
   }
   
   private void insert() throws SQLException {

       Connection cnx =Myconnexion.getInstance().getCnx(); 
    
          PreparedStatement ste= cnx.prepareStatement(query);
          
          ste.setString(1,txtnom.getText());
          ste.setString(2,txtprenom.getText());
          ste.setString(3,String.valueOf(txtdatenaissance.getValue()));
          ste.setString(4,txtadresse.getText());
          ste.setString(5,txtmail.getText());
          ste.setString(6,txttel.getText());
          ste.setString(7,String.valueOf(txtrole.getValue()));
          ste.setString(8,txtmdp.getText());
          ste.setString(9,txtnomEquipe.getText());
          ste.setString(10,String.valueOf(txtetat.getValue()));
   }
}

