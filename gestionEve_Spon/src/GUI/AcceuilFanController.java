/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
 * @author malak_6
 */
public class AcceuilFanController implements Initializable {

    @FXML
    private Button equipebtn;
    @FXML
    private Button magasinbtn;
    @FXML
    private Button actualitebtn;
    @FXML
    private Button eventbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goequipe(ActionEvent event) {
    }

    @FXML
    private void goevent(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("eventfan.fxml"));
        Parent root = loader.load();
        EventfanController ap= loader.getController();
        eventbtn.getScene().setRoot(root);
    }
    
}
