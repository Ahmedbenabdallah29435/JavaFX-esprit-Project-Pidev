/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.PersonneService;
import entity.Personne;
import static GUI.main.Userconnected;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author malak_6
 */
public class ProfilFanController implements Initializable {

    @FXML
    private Label lb_nom;
    @FXML
    private Label lb_prenom;
    @FXML
    private Label lb_adresse;
    @FXML
    private Label lb_datenaissance;
    @FXML
    private Label lb_tel;
    @FXML
    private Label lb_mail;
    @FXML
    private Label lb_id;
    @FXML
    private Button btnsupp;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnacceuil;
private Personne u;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Personne u=  UserSession.getInstace().getUtilisateur();
      //System.out.println(u);
        setlb_nom(Userconnected.getNom());
        setLb_prenom(Userconnected.getPrenom());
        setLb_adresse(Userconnected.getAdresse());
        setLb_tel(Userconnected.getTel());
        
        setLb_mail(Userconnected.getMail());
        setLb_date(Userconnected.getDatenaissance());
        setLb_id(Userconnected.getId());
    }    
     public void setlb_nom(String email) {
        this.lb_nom.setText(email);
    }
        public void setLb_prenom(String email) {
        this.lb_prenom.setText(email);
    }
        
        public void setLb_mail(String email) {
        this.lb_mail.setText(email);
    }public void setLb_date(Date d) {
        this.lb_datenaissance.setText(d.toString());
    }public void setLb_tel(int email) {
        this.lb_tel.setText(Integer.toString(email));
    }
         public void setLb_adresse(String email) {
        this.lb_adresse.setText(email);
    }
    
    public void setLb_id(int email) {
        this.lb_id.setText(Integer.toString(email));
    }
    
         public void initData(Personne u) throws SQLException{
      
       this.u=u;
        System.out.println(u.getMail());
      
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException, IOException {
            int id = Integer.parseInt(lb_id.getText());

        PersonneService svUser = new PersonneService();

        svUser.supprimer(id);
        
        JOptionPane.showMessageDialog(null, " votre compte a été bien supprimer ");
        
       
        
       FXMLLoader loader= new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        LoginController ap= loader.getController();
        btnsupp.getScene().setRoot(root);
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
     FXMLLoader loader= new FXMLLoader(getClass().getResource("modifierFan.fxml"));
        Parent root = loader.load();
        ModifierFanController ap= loader.getController();
        btnmodif.getScene().setRoot(root);
    }
    

    @FXML
    private void acceuilR(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("acceuilFan.fxml"));
        Parent root = loader.load();
        AcceuilFanController ap= loader.getController();
        btnacceuil.getScene().setRoot(root);
    }
    
}
