/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Personne;
import static GUI.main.Userconnected;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import utils.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class AcceuilResponsableController implements Initializable {

    @FXML
    private Button btnprofil;
    @FXML
    private Button btnlogout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void profil(ActionEvent event) throws IOException {
         
        
       // Personne u=  UserSession.getInstace().getUtilisateur();
         // System.out.println("khdhe");
           // System.out.println(u.getRole());
       FXMLLoader loader= new FXMLLoader(getClass().getResource("ProfilResponsable.fxml"));
        Parent root = loader.load();
        ProfilResponsableController ap= loader.getController();
        
        btnprofil.getScene().setRoot(root);
    
}

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Userconnected.setId(0);
                  Userconnected.setNom("");
                  Userconnected.setPrenom("");              
                  //Userconnected.setDatenaissance(d);                       
                  Userconnected.setAdresse("");                               
                  Userconnected.setMail("");                                     
                  Userconnected.setTel(0);
                  Userconnected.setRole("");
                  Userconnected.setMdp("");
                  Userconnected.setNomEquipe("");
                  Userconnected.setEtat("");
        
       FXMLLoader loader= new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        LoginController ap= loader.getController();
        
        btnlogout.getScene().setRoot(root); 
    }
    
}