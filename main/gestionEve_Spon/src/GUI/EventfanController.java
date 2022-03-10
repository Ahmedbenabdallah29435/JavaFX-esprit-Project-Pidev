/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import javafx.scene.image.Image;
import service.ServiceEvennement;
import entity.Evennement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.Myconnexion;

/**
 * FXML Controller class
 *
 * @author malak_6
 */
public class EventfanController implements Initializable {

   // List<Event> liste = new ArrayList <Event>();
     Connection cnx =Myconnexion.getInstance().getCnx();
    private TextField txtnom;
    
    private TextField txtprix;
   
    @FXML
    private Button reserv;
    @FXML
    private Button txtacceuil;
    private ImageView ima;
    private Label nom;
       private Label description;
    private ImageView ImageView;
    @FXML
    private VBox box;
    
    ServiceEvennement ev=new  ServiceEvennement();
    List<Evennement> liste=new ArrayList<>();
    private Evennement o;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        HBox item = new HBox();
                   box.getChildren().add(item);
           liste=ev.afficher();
        int taille=liste.size();
          for( int i=0;i<taille;i++)
            {
               
                if(i % 3 == 0){
                    item = new HBox();
                    box.getChildren().add(item);
                }
                VBox content = new VBox();
                File file=new File( liste.get(i).getImageE());
                Image image = new Image(file.toURI().toString());
                // Image image = new Image( file);
                ImageView ImageView = new ImageView(image);
                Label title = new Label();
                o=liste.get(i);
                Label nom = new Label((liste.get(i).getNom()));
                nom.setStyle("-fx-strikethrough: true");
               // nom.getStyleClass().add("barre");
               nom.setStyle("-fx-font-weight: bold");
                Label description = new Label((liste.get(i).getDescription()));
               description.setStyle("-fx-strikethrough: true");
                //prix.getStyleClass().add("barre");
                ImageView.setFitHeight(100);
                        ImageView.setFitWidth(100);
                content.getChildren().addAll(ImageView,nom,description);
                Button btn = new Button("",content);
                btn.setOnAction(event -> {
                    
                    
               });
                btn.setPrefWidth(150);
                item.getChildren().add(btn);
                box.setSpacing(80);
                item.setSpacing(80);
    }    
      
 
   /*  SEvent p=new SEvent();
         Connection cnx =MaConnexion.getInstance().getCnx();
         List <Event> l=p.afficherE();
         
         data=FXCollections.observableArrayList(l);
       
      
       
        txtnom.setCellValueFactory(new PropertyValueFactory <Event,String>("nom"));
         txtlieu.setCellValueFactory(new PropertyValueFactory <Event,String>("lieu"));
          txtdate.setCellValueFactory(new PropertyValueFactory <Event,Date>("date"));
           txtnbr.setCellValueFactory(new PropertyValueFactory <Event,Integer>("nbparticipation"));
           txtdesc.setCellValueFactory(new PropertyValueFactory <Event,String>("description"));
            txtprix.setCellValueFactory(new PropertyValueFactory <Event,Float>("prix"));
             txtids.setCellValueFactory(new PropertyValueFactory <Event,Integer>("ids"));
             
              
        event.setItems(data);
         txtnom.setCellFactory(TextFieldTableCell.forTableColumn());
        txtnom.setOnEditCommit((e) ->
                {
                    
 
                 if( p.updateNom(event.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     event.getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
                     event.refresh();
         });
        txtlieu.setCellFactory(TextFieldTableCell.forTableColumn());
        txtlieu.setOnEditCommit((e) ->
                {
                    
 
                 if( p.updateLieu(event.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     event.getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
                     event.refresh();
         });
        txtdesc.setCellFactory(TextFieldTableCell.forTableColumn());
        txtdesc.setOnEditCommit((e) ->
                {
                    
 
                 if( p.updateDescripton(event.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     event.getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
                     event.refresh();
         });
       
           event.setEditable(true);*/
   //Event e = new Event();
   // javafx.scene.image.Image img = new javafx.scene.image.Image("C:\\Users\\Espace Sboui\\Desktop\\connectsport\\src\\GUI\\img\\2.jpg");
 // Image img=new Image(getClass().getResourceAsStream(e.getImage()));
    /*File file = new File(e.getImage());
        Image image = new Image(file.toURI().toString());
        //javafx.scene.image.Image image=new javafx.scene.image.Image(file);
        // javafx.scene.image.Image img = new javafx.scene.image.Image(file.toURI().toString());

       // imag.setText(file.toURI().toString());
        immm.setImage(img);*/
   
}

    @FXML
    private void reserver(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("reservation.fxml"));
        Parent root = loader.load();
        ReservationController ap= loader.getController();
        reserv.getScene().setRoot(root);
    }

    @FXML
    private void acceuil(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("acceuilFan.fxml"));
        Parent root = loader.load();
        AcceuilFanController ap= loader.getController();
        txtacceuil.getScene().setRoot(root);
    }


    private void next(ActionEvent event) {
         Statement st = null;
        int position=28;
     try {
         String sql="select id from evenement ";
         st= cnx.prepareStatement(sql);
                  ResultSet rs= st.executeQuery(sql);
                  if(rs.next())
                  {
                  position=rs.getInt("id");
                 
                 
                  }
         
     } catch (SQLException ex) {
         Logger.getLogger(EventfanController.class.getName()).log(Level.SEVERE, null, ex);
     }
             
     try {
         String sql="select nom,description,image from evenement  where id >'"+position+"'";
     
            //cnx = Connection .getInstance().getCnx();
            st= cnx.prepareStatement(sql);
               ResultSet rs= st.executeQuery(sql);
            if(rs.next())
            {
                     txtnom.setText(rs.getString("nom"));
              txtprix.setText(rs.getString("description"));
    String  image= rs.getString("image");
       
           File file = new File(image);
        Image img = new Image(file.toURI().toString());
           ImageView.setImage(img);
            position++;
            }
            
     } catch (SQLException ex) {
         System.out.println(ex);
     }
    
    }
    }

