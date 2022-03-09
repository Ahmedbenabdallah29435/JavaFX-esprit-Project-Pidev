/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.Evennement;
import entity.Sponsor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import static javafx.util.Duration.millis;
import javax.swing.JFileChooser;
import service.ServiceEvennement;
import service.ServiceSponsor;

/**
 * FXML Controller class
 *
 * @author AhmedBenAbdallah
 */
public class EController implements Initializable {
    
    ServiceEvennement se=new ServiceEvennement();
    ObservableList<Evennement> evenList = FXCollections.observableArrayList(); 
      Evennement even =null;

    @FXML
    private Button btnAcceuil;
    @FXML
    private Button btnSignout;
    @FXML
    private TableView<Evennement> evenTable;
    @FXML
    private TableColumn<Evennement,String> nom;
    @FXML
    private TableColumn<Evennement,String> date;
    @FXML
    private TableColumn<Evennement,String> description;
    @FXML
    private TableColumn<Evennement,String> nb_place;
    @FXML
    private TableColumn<Evennement,String> imageE;
    @FXML
    private TableColumn<Evennement,String> edit;
    @FXML
    private ImageView imview;
    @FXML
    private Button rr;
    @FXML
    private TextField txtrecherche;
    @FXML
    private ComboBox<String> combomag;
    @FXML
    private TextField nbmag;
    @FXML
    private Button DESC;
    @FXML
    private Button ASC;
    @FXML
    private ImageView btnexcel;
    @FXML
    private ImageView btnstat;
    @FXML
    private TextField nomfld;
    @FXML
    private TextField lieufld;
    @FXML
    private TextField desfld;
    @FXML
    private TextField imgEfld;
    @FXML
    private TextField nbrfld;
    @FXML
    private Button imgSbtn;
    @FXML
    private ImageView imgSV;
    @FXML
    private DatePicker datefld;
    
    private ImageView imagev;
    int idsponsor;
    String col,ord ;
    @FXML
    private TableColumn<?, ?> lieu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      combomag.getItems().removeAll(combomag.getItems());
    combomag.getItems().addAll("nom","date", "lieu", "description","nb_place");
    combomag.getSelectionModel().select("nom");
    col="nom";
    ord="ASC";        
        LoadDate();
        
        txtrecherche.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               research();
            }
        });



combomag.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
    
    if (combomag.getSelectionModel().getSelectedItem().equals("nom"))
            col="nom";
    else if   (combomag.getSelectionModel().getSelectedItem().equals("date"))
            col="date" ;
        else if   (combomag.getSelectionModel().getSelectedItem().equals("lieu"))
            col="lieu" ;
        else  if (combomag.getSelectionModel().getSelectedItem().equals("description"))
             col="description";
    else if   (combomag.getSelectionModel().getSelectedItem().equals("nb_place"))
            col="nb_place" ;
        research();
}); 
        
    } 
     

    @FXML
    private void acceuil(ActionEvent event) {
    }

     void setTextField(String nome, String lieue, String description,  int nb_place, String imageEe) 
     {
      
        nomfld.setText(nome);
      
    
       
        lieufld.setText(lieue);
        desfld.setText(String.valueOf(description));
        nbrfld.setText(String.valueOf(nb_place));
        
        
        
        imgEfld.setText(imageEe);
       
        

    }
      private void LoadDate()
    {
        
       
         refreshTable();
         
      
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       date.setCellValueFactory(new PropertyValueFactory<>("date"));
        lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        nb_place.setCellValueFactory(new PropertyValueFactory<>("nb_place"));    
       
         imageE.setCellValueFactory(new PropertyValueFactory<>("imageE"));

        
        
          Callback<TableColumn<Evennement, String>, TableCell<Evennement, String>> cellFoctory = (TableColumn<Evennement, String> param) -> {
            // make cell containing buttons
            final TableCell<Evennement, String> cell = new TableCell<Evennement, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        
                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.REMOVE);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL);
                      FontAwesomeIconView addIcon = new FontAwesomeIconView(FontAwesomeIcon.PLUS_SQUARE_ALT);
                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#665E5F;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#730800;"
                        );
                        addIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        
                       
                        
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                           
                                even = evenTable.getSelectionModel().getSelectedItem();
                                se.supprimer(even);
                                refreshTable();  

                        });
                         editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            even = evenTable.getSelectionModel().getSelectedItem();
                           
                               FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("../GUI/ajouterEven_1.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(ASController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                           EController eController = loader.getController();
                           
                        eController.setTextField(even.getNom(),even.getLieu(),even.getDescription(),
                               even.getNb_place(),even.getImageE());
                         
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            
                             
                             
                           

                        });
                          addIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                             even = evenTable.getSelectionModel().getSelectedItem();
                             Image image;
                            try {
                                image = new Image(new FileInputStream(even.getImageE()));
                                imview.setImage(image); 
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(ASController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                                     
                        });
                         
                         
                         
                         
                         
                        HBox managebtn = new HBox( deleteIcon,editIcon,addIcon);
                        managebtn.setStyle("-fx-alignment:center");
                         
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                       HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                         HBox.setMargin(addIcon, new Insets(2, 3, 0, 2));
                      
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         edit.setCellFactory(cellFoctory);
         evenTable.setItems(evenList);
       
    }
    
    
    
    
    
    @FXML
    private void logout(ActionEvent event) {
    }

    @FXML
    private void refreshTable() {
        evenList.clear();
          evenList.addAll(se.afficher());
          evenTable.setItems(evenList);
    }
     public void onEdit() {
 even = evenTable.getSelectionModel().getSelectedItem();
                             Image image;
                            try {
                                image = new Image(new FileInputStream(even.getImageE()));
                            
                                imagev.setImage(image); 
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(ASController.class.getName()).log(Level.SEVERE, null, ex);
                            }
    
}


    @FXML
    private void ajouter(ActionEvent event) throws ParseException {
            String nom =nomfld.getText();
           long millis=System.currentTimeMillis();  
         java.sql.Date date = new java.sql.Date(millis); 
         
        Date datee = Date.valueOf(datefld.getValue());
          
      String lieu=lieufld.getText();
      
       String description =desfld.getText();
      
       Integer nb_place = Integer.parseInt(nbrfld.getText());
       
        String imageE =imgEfld.getText();
         
        StringBuilder errors =new StringBuilder();
//        if(nomfld.getText().trim().isEmpty()){
//            errors.append("Please enter a name\n");
//        }
//        if(( !Pattern.matches("[a-zA-Z]*", nomfld.getText()))||(nomfld.getText().trim().isEmpty())){
//            errors.append("Please enter a valid name\n");
//        }
//        if(typefld.getText().trim().isEmpty()){
//            errors.append("Please enter a type\n");
//        }
//        if(adressefld.getText().trim().isEmpty()){
//            errors.append("Please enter an adresse\n");
//        }
//        if(Pattern.matches("[a-zA-Z]*", telfld.getText())||telfld.getText().isEmpty()){
//            errors.append("please enter a valid number\n");
//        
//        }
//     
//        if(emailfld.getText().trim().isEmpty()){
//            errors.append("Please enter an email\n");
//        }
//        
//        if(imgSfld.getText().trim().isEmpty()){
//            errors.append("Please enter a photo\n");
//        }
//       
//        
//        if(errors.length()>0){
//            Alert alert =new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Errors");
//            alert.setContentText(errors.toString());
//            alert.showAndWait();
//        }
//        else
//        {
            
           Evennement e=new Evennement(nom,datee,lieu,description,nb_place,imageE);
            se.ajouter(e);
            clean();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Event Ajouté");
            alert.showAndWait();
            
        }
    

    @FXML
    private void getImgS(ActionEvent event) {
        
       JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        String filename=f.getAbsolutePath();
        imgEfld.setText(filename);
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
       
      lieufld.setText(null);
       desfld.setText(null);
        nbrfld.setText(null);
          imgEfld.setText(null);
    }
    @FXML
    private void updatee(ActionEvent event) throws ParseException {
      
         
        String nome =nomfld.getText();
        
         Date datee = Date.valueOf(datefld.getValue());
          
      String lieu=lieufld.getText();
      
       String description =desfld.getText();
      
       Integer nb_place = Integer.parseInt(nbrfld.getText());
       
        String imageE =imgEfld.getText();
         
         
      
          Evennement e=new Evennement(nome,datee,lieu,description,nb_place,imageE);
            se.modifier(e);
            clean();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Event Ajouté");
            alert.showAndWait();
            
    }
//    
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private void trimag(ActionEvent event) {
        if (combomag.getSelectionModel().equals("nom"))
            col="nom";
        else if   (combomag.getSelectionModel().equals("date"))
            col="date" ;
        else if   (combomag.getSelectionModel().equals("lieu"))
            col="lieu" ;
        else  if (combomag.getSelectionModel().equals("description"))
            col="description";
        else if   (combomag.getSelectionModel().equals("nb_place"))
            col="nb_place" ;
        research();
        

        

    }
//
    @FXML
    private void ordremag(ActionEvent event) {
        
            ord="ASC";
      
        
        research();
    }
    @FXML
    private void ordremag1(ActionEvent event) {
        
            ord="DESC";
      
        
        research();
    }
public void research()
{
  ServiceEvennement sm = new ServiceEvennement ();
        List<Evennement> lm = sm.recherchermutli(txtrecherche.getText(),col,ord);
   evenTable.getItems().clear();
      ObservableList<Evennement> datalist = FXCollections.observableArrayList(lm);
        
         
        
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       date.setCellValueFactory(new PropertyValueFactory<>("date"));
        lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        nb_place.setCellValueFactory(new PropertyValueFactory<>("nb_place"));   
         imageE.setCellValueFactory(new PropertyValueFactory<>("imageE"));   
       
         
         
    evenTable.setItems(datalist); 
    
    nbmag.setText(String.valueOf(lm.size()));
}


   
    @FXML
    private void exportExcel(MouseEvent event) {
    }

    @FXML
    private void showchart(MouseEvent event) {
    }

   
    
}
