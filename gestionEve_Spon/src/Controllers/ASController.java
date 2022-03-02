/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entity.Sponsor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javax.swing.JFileChooser;
import service.ServiceSponsor;
/**
 * FXML Controller class
 *
 * @author AhmedBenAbdallah
 */
public class ASController implements Initializable {

     ServiceSponsor ss=new ServiceSponsor();
    
    
    @FXML
    private Button btnAcceuil;
    @FXML
    private Button btnSignout;
    @FXML
    private ImageView logov;
    @FXML
    private TextField nomfld;
    @FXML
    private TextField adressefld;
    @FXML
    private TextField telfld;
    @FXML
    private TextField imgSfld;
    @FXML
    private TextField typefld;
    @FXML
    private TextField emailfld;
    @FXML
    private Button imgSbtn;
    @FXML
    private ImageView imgSV;
 private StackPane contentArea;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
     public static boolean isNumericint(String strNum){
        
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @FXML
    private void acceuil(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
    }

    @FXML
    private void select(ActionEvent event) {
    }

    @FXML
    private void ajouter(ActionEvent event) {
            String nom =nomfld.getText();
            
        String type =typefld.getText();
      String adresse=adressefld.getText();
       Integer tel = Integer.parseInt(telfld.getText());
       String email =emailfld.getText();
        String imgS =imgSfld.getText();
        StringBuilder errors =new StringBuilder();
        if(nomfld.getText().trim().isEmpty()){
            errors.append("Please enter a name\n");
        }
        if(typefld.getText().trim().isEmpty()){
            errors.append("Please enter a type\n");
        }
        if(adressefld.getText().trim().isEmpty()){
            errors.append("Please enter an adresse\n");
        }
        if(!isNumericint(telfld.getText())){
            errors.append("please enter a valid number\n");
        }
     
        if(emailfld.getText().trim().isEmpty()){
            errors.append("Please enter an email\n");
        }
        
        if(imgSfld.getText().trim().isEmpty()){
            errors.append("Please enter a photo\n");
        }
       
        
        if(errors.length()>0){
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
        else
        {
            
            Sponsor s=new Sponsor(nom,type,adresse,tel,email,imgS);
            ss.ajouter(s);
            clean();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Sponsor Ajouté");
            alert.showAndWait();
            
        }
    }

    @FXML
    private void getImgS(ActionEvent event) {
        
       JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        String filename=f.getAbsolutePath();
        imgSfld.setText(filename);
        Image image;
          try {
              image = new Image(new FileInputStream(filename));
               imgSV.setImage(image);
          } catch (FileNotFoundException ex) {
              Logger.getLogger(ASController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    private void clean() {
         nomfld.setText(null);
        typefld.setText(null);
        adressefld.setText(null);
       telfld.setText(null);
         emailfld.setText(null);
          imgSfld.setText(null);
    }
    
}
