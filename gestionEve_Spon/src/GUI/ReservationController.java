/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entity.Evennement;
import entity.Reservation;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import service.ServiceEvennement;
import service.SReservation;
import utils.Myconnexion;

/**
 * FXML Controller class
 *
 * @author malak_6
 */
public class ReservationController implements Initializable {

    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtprenom;
    @FXML
    private TextField txtage;
    @FXML
    private ComboBox<Integer> txtevent;
    @FXML
    private Button btnajout;

    ServiceEvennement s= new ServiceEvennement();
    ObservableList<Integer> listids=FXCollections.observableArrayList(s.afficherid());
    @FXML
    private Button btnajout1;
    @FXML
    private Button btnpdf;
    ValidationSupport validationSupport = new ValidationSupport();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtevent.setItems(listids);
         validationSupport.registerValidator(txtnom, Validator.createEmptyValidator("text is required"));
         validationSupport.registerValidator(txtprenom, Validator.createEmptyValidator("text is required"));
         validationSupport.registerValidator(txtage, Validator.createEmptyValidator("text is required"));
          
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        if (txtnom.getText().length()==0)
        { txtnom.setStyle("-fx_border-color: red ; -fx-border-width : 2px;");
        new animatefx.animation.Shake(txtnom).play();
    }
        else 
            txtnom.setStyle(null);
        if(txtprenom.getText().length()==0)
        {
            txtprenom.setStyle("-fx_border-color: red ; -fx-border-width : 2px;");
        new animatefx.animation.Shake(txtprenom).play();
        }
        else 
            txtprenom.setStyle(null);    
   /* if(Date.valueOf(txtdate.getValue())==null)
        {
            txtdate.setStyle("-fx_border-color: red ; -fx-border-width : 2px;");
        new animatefx.animation.Shake(txtdate).play();
        }
        else 
            txtdate.setStyle(null);  */
    if(txtage.getText().length()==0)
        {
            txtage.setStyle("-fx_border-color: red ; -fx-border-width : 2px;");
        new animatefx.animation.Shake(txtage).play();
        }
        else 
            txtage.setStyle(null); 
        String nom=txtnom.getText();
        
       ObservableList<Integer> listids=txtevent.getItems();
       // ObservableList<String> list=txttype.getItems();
       String prenom=txtprenom.getText();
       int age=Integer.valueOf(txtage.getText());
       
   Reservation sp=new Reservation(nom,age,prenom,txtevent.getSelectionModel().getSelectedItem());
    SReservation e=new SReservation();
   e.ajouter(sp);
   ServiceEvennement s=new ServiceEvennement();
   s.modifierNB();
   Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Reservation effectuée");
            alert.showAndWait();
    }

    @FXML
    private void acceuil(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("acceuilFan.fxml"));
        Parent root = loader.load();
        AcceuilFanController ap= loader.getController();
        btnajout1.getScene().setRoot(root);
    }

    @FXML
    private void PDF(ActionEvent event) {
        String FILE_NAME = "C:\\Users\\AhmedBenAbdallah\\Desktop\\gestionEve_Spon\\reservation.pdf";
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));
            document.open();
            Paragraph p = new Paragraph();
            p.add("Merci pour votre reservation");
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            
            Connection cnx =Myconnexion.getInstance().getCnx();
            String query = "select nom,idr from reservation where id=(select max(id) from reservation) ";
            Statement stmt = null;
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Paragraph p3 = null;
            while (rs.next()) {
                p3 = new Paragraph();
                
                
              
                p3.add(rs.getString("nom"));
                document.add(p3);
            }
            document.close();
            System.out.println("Done");
             File myFile = new File("C:/Users/AhmedBenAbdallah/Desktop/gestionEve_Spon/reservation.pdf");
    Desktop.getDesktop().open(myFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
