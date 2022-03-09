/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entity.Reservation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import service.SReservation;
import service.ServiceSponsor;
import utils.Myconnexion;

/**
 * FXML Controller class
 *
 * @author Espace Sboui
 */
public class AffichereservationController implements Initializable {

    @FXML
    private Button btnsupprimer;
    @FXML
    private TextField txtrecherche;
    @FXML
    private Button btnsearch;
    private Button btnretour;
    public ObservableList <Reservation> data=FXCollections.observableArrayList();
    @FXML
    private TableColumn<Reservation, String> txtnom;
    @FXML
    private TableColumn<Reservation, String> txtprenom;
    @FXML
    private TableColumn<Reservation, Integer> txtage;
    @FXML
    private TableView<Reservation> listReservation;
    @FXML
    private TableColumn<Reservation, Integer> txtidr;
    @FXML
    private Button afficher;
private ObservableList<Reservation> masterData = FXCollections.observableArrayList();
    @FXML
    private Button btnAcceuil;
    @FXML
    private Button btnev;
    @FXML
    private Button btnsp;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btnres;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          SReservation p=new SReservation();
         Connection cnx =Myconnexion.getInstance().getCnx();
         List <Reservation> l=p.afficher();
         
         data=FXCollections.observableArrayList(l);
       
      
        txtidr.setCellValueFactory(new PropertyValueFactory <Reservation,Integer>("idr"));
        txtnom.setCellValueFactory(new PropertyValueFactory <Reservation,String>("nom"));
         txtprenom.setCellValueFactory(new PropertyValueFactory <Reservation,String>("prenom"));
          txtage.setCellValueFactory(new PropertyValueFactory <Reservation,Integer>("age"));
          
             // 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Reservation> filteredData = new FilteredList<>(masterData, r -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		txtrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(reservation -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (reservation.getNom().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (reservation.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Reservation> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(listReservation.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		listReservation.setItems(sortedData);
              
        listReservation.setItems(data);
         Notifications notificationBuilder = Notifications.create().title("reservation").text("afficher ").graphic(null).hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });
       
    }    

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
         Reservation R=listReservation.getSelectionModel().getSelectedItem();
       SReservation re=new SReservation();
       //sp.supprimer(sponsor.getId());
       re.supprimer(R.getId());
       data.clear();
      this.afficher(event);
        Notifications notificationBuilder = Notifications.create().title("supprimer").text("suppression avec succée ").graphic(null).hideAfter(Duration.seconds(5)).position(Pos.TOP_RIGHT).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });
        notificationBuilder.showConfirm();
    }

    @FXML
    private void rechercher(ActionEvent event) {
        FilteredList<Reservation> filteredData = new FilteredList<>(data, b -> true);

// 2. Set the filter Predicate whenever the filter changes.
txtrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
filteredData.setPredicate(p -> {
// If filter text is empty, display all persons.

if (newValue == null || newValue.isEmpty()) {
return true;
}

// Compare first name and last name of every person with filter text.
String lowerCaseFilter = newValue.toLowerCase();

if (p.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
return true; // Filter matches first name.
} else if (p.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
return true; // Filter matches last name.
}
    else  
    return false; // Does not match.
});
});
               
     
     
      // 3. Wrap the FilteredList in a SortedList.
SortedList<Reservation> sortedData = new SortedList<>(filteredData);

// 4. Bind the SortedList comparator to the TableView comparator.
//  Otherwise, sorting the TableView would have no effect.
sortedData.comparatorProperty().bind(listReservation.comparatorProperty());

// 5. Add sorted (and filtered) data to the table.
listReservation.setItems(sortedData);
    }

//    private void retourHome(ActionEvent event) throws IOException {
//        FXMLLoader loader= new FXMLLoader(getClass().getResource("EvenementSponsor.fxml"));
//        Parent root = loader.load();
//        
//        btnretour.getScene().setRoot(root);
//    }

    @FXML
    private void afficher(ActionEvent event) {
        SReservation p=new SReservation();
         Connection cnx =Myconnexion.getInstance().getCnx();
         List <Reservation> l=p.afficher();
         
         data=FXCollections.observableArrayList(l);
       
      
        txtidr.setCellValueFactory(new PropertyValueFactory <Reservation,Integer>("idr"));
        txtnom.setCellValueFactory(new PropertyValueFactory <Reservation,String>("nom"));
         txtprenom.setCellValueFactory(new PropertyValueFactory <Reservation,String>("prenom"));
          txtage.setCellValueFactory(new PropertyValueFactory <Reservation,Integer>("age"));
          
             
              
        listReservation.setItems(data);
         
    }
@FXML
    private void acceuil(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("../GUI/Acceuil.fxml"));
        btnAcceuil.getScene().setRoot(root);
    }
    @FXML
    private void gererevent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/Even.fxml"));
        btnev.getScene().setRoot(root);
    }
    @FXML
    private void gerersponsor(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/ajouterSponsor.fxml"));
        btnsp.getScene().setRoot(root);
    }
   
 @FXML
    private void gererreservation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/affichereservation.fxml"));
        btnres.getScene().setRoot(root);
    }

    @FXML
    private void logout(ActionEvent event) {
    }


}
