/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author AhmedBenAbdallah
 */
public class A implements Initializable {

    @FXML
    private Button btnAcceuil1;
    @FXML
    private Button btnev1;
    @FXML
    private Button btnsp1;
    @FXML
    private Button btnSignout1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   @FXML
    private void acceuil1(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("../GUI/Acceuil.fxml"));
        btnAcceuil1.getScene().setRoot(root);
    }
@FXML
    private void gererevent1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/Even.fxml"));
        btnev1.getScene().setRoot(root);
    }
@FXML
    private void gerersponsor1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/ajouterSponsor.fxml"));
        btnsp1.getScene().setRoot(root);
    }
    @FXML
    private void logout(ActionEvent event) {
    }
    
}
