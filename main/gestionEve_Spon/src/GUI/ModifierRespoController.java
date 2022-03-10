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
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author malak_6
 */
public class ModifierRespoController implements Initializable {

    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_mail;
    @FXML
    private TextField tf_tel;
    @FXML
    private TextField tf_adresse;
    @FXML
    private TextField tf_prenom;
    @FXML
    private TextField tf_date;
    @FXML
    private Button btnmodif;
    @FXML
    private Label lb_id;
    @FXML
    private Button btnacceuil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    // Personne u=  UserSession.getInstace().getUtilisateur();
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
        this.tf_nom.setText(email);
    }
        public void setLb_prenom(String email) {
        this.tf_prenom.setText(email);
    }
        
        public void setLb_mail(String email) {
        this.tf_mail.setText(email);
    }public void setLb_date(Date d) {
        this.tf_date.setText(d.toString());
    }public void setLb_tel(int email) {
        this.tf_tel.setText(Integer.toString(email));
    }
         public void setLb_adresse(String email) {
        this.tf_adresse.setText(email);
    }
    
    public void setLb_id(int email) {
        this.lb_id.setText(Integer.toString(email));
    }
        

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
          
        
       
         int id=Integer.valueOf(lb_id.getText());
        
         
        String nom = tf_nom.getText();
        String prenom = tf_prenom.getText();
        String email = tf_mail.getText();
       Date d = Date.valueOf(tf_date.getText());
      int tel=Integer.valueOf(tf_tel.getText());
        String adresse = tf_adresse.getText();
       
        

       Personne u=new Personne(id,nom,prenom,d,adresse,email,tel);
       PersonneService sp=new PersonneService();
       sp.modifierP(u);
       
        JOptionPane.showMessageDialog(null, "Modification avec succés");
        
    }

    @FXML
    private void acceuil(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("acceuilResponsable.fxml"));
        Parent root = loader.load();
        AcceuilResponsableController ap= loader.getController();
        btnacceuil.getScene().setRoot(root);
    }
    
}
