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
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import utils.Myconnexion;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author malak_6
 */
public class DocFXMLController implements Initializable {
Connection cnx = Myconnexion.getInstance().getCnx();
    ObservableList<PieChart.Data> piechartdata;
     ObservableList<PieChart.Data> piechartdata1;
     ObservableList<PieChart.Data> piechartdata2;
    @FXML
    private Button txtpersonne;
    @FXML
    private PieChart stat_admin;
    @FXML
    private PieChart admin_stat;
    @FXML
    private PieChart stat;
    @FXML
    private Button btngoogle;
    @FXML
    private Label nomadmin;
    @FXML
    private Button btnlogout;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         //Personne u=  UserSession.getInstace().getUtilisateur();
      //System.out.println(u);
        setlb_nom(Userconnected.getNom());
        try {
            // TODO
            loadDataPie();
            loadDataPie1();
            loadDataPie2();
        } catch (SQLException ex) {
            Logger.getLogger(DocFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        stat_admin.setData(piechartdata);
        admin_stat.setData(piechartdata1);
        stat.setData(piechartdata2);
    }    
public void setlb_nom(String email) {
        this.nomadmin.setText(email);
    }
    @FXML
    private void afficherPersonne(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("afficherPersonne.fxml"));
        Parent root = loader.load();
        AfficherPersonneController ap= loader.getController();
        txtpersonne.getScene().setRoot(root);
    }
     public void loadDataPie() throws SQLException
    {
        piechartdata = FXCollections.observableArrayList();
        String requete = "Select u.etat,Count(u.etat) as nbr From personne u Group BY u.etat";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            piechartdata.add(new PieChart.Data(rs.getString("etat"), rs.getInt("nbr")));

        }
    }
     
      public void loadDataPie1() throws SQLException
    {
        piechartdata1 = FXCollections.observableArrayList();
        String requete = "Select u.nomEquipe,Count(u.nomEquipe) as nbr From personne u Group BY u.nomEquipe";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            piechartdata1.add(new PieChart.Data(rs.getString("nomEquipe"), rs.getInt("nbr")));

        }
    }
       public void loadDataPie2() throws SQLException
    {
        piechartdata2 = FXCollections.observableArrayList();
        String requete = "Select u.role,Count(u.role) as nbr From personne u Group BY u.role";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            piechartdata2.add(new PieChart.Data(rs.getString("role"), rs.getInt("nbr")));

        }
    }

    @FXML
    private void google(ActionEvent event) {
       
       
        
    }

    @FXML
    private void logout(ActionEvent event) throws IOException{
        
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
