/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entity.Sponsor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JFileChooser;
import service.ServiceSponsor;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author AhmedBenAbdallah
 */
public class ASController implements Initializable {

     ServiceSponsor ss=new ServiceSponsor();
    ObservableList<Sponsor> SponsorList = FXCollections.observableArrayList(); 
      Sponsor sponsor=null;
    
    @FXML
    private Button btnAcceuil;
    @FXML
    private Button btnSignout;
    @FXML
    
    private TextField nomfld;
    @FXML
    private TextField adressefld;
    @FXML
    private TextField telfld;
    @FXML
    private TextField imgSfld;
    @FXML
    private TextField typefld;
    @FXML
    private TextField emailfld;
    @FXML
    private Button imgSbtn;
    @FXML
    private ImageView imgSV;
    @FXML
    private Button rr;
     @FXML
    private TableView<Sponsor> sponsorTable;
    @FXML
    private TableColumn<Sponsor,String> nom;
    @FXML
    private TableColumn<Sponsor,String> type;
    @FXML
    private TableColumn<Sponsor,String> adresse;
    @FXML
    private TableColumn<Sponsor,String> tel;
    @FXML
    private TableColumn<Sponsor,String> email;  
    @FXML
    private TableColumn<Sponsor,String> image;
    @FXML
    private TableColumn<Sponsor,String> edit;
    private ImageView imagev;
    int idsponsor;
    String col,ord ;
    
    
 private StackPane contentArea;
    @FXML
    private ImageView imview;
    @FXML
    private TextField txtrecherche;
    @FXML
    private ComboBox<String> combomag;
    @FXML
    private Button ASC;
    @FXML
    private Button DESC;
    @FXML
    private TextField nbmag;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      combomag.getItems().removeAll(combomag.getItems());
    combomag.getItems().addAll("nom","type", "adresse", "tel","email");
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
    else if   (combomag.getSelectionModel().getSelectedItem().equals("nype"))
            col="type" ;
        else if   (combomag.getSelectionModel().getSelectedItem().equals("adresse"))
            col="adresse" ;
        else  if (combomag.getSelectionModel().getSelectedItem().equals("tel"))
             col="tel";
    else if   (combomag.getSelectionModel().getSelectedItem().equals("email"))
            col="email" ;
        research();
}); 
        
    } 
     public static boolean isNumericint(String strNum){
        
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @FXML
    private void acceuil(ActionEvent event) {
    }
     @FXML
    private void refreshTable() {
       
          SponsorList.clear();
          SponsorList.addAll(ss.afficher());
          sponsorTable.setItems(SponsorList);
       
    }
    
     void setTextField(int id,String nome, String typee, String adressee, int tele,  String emaile, String imgSe) 
     {
        idsponsor=id;
        nomfld.setText(nome);
        typefld.setText(typee);
        adressefld.setText(adressee);
        telfld.setText(String.valueOf(tele));
        emailfld.setText(emaile);
        
        
        
        imgSfld.setText(imgSe);
       
        

    }
      private void LoadDate()
    {
        
       
         refreshTable();
         
      
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));    
       
         image.setCellValueFactory(new PropertyValueFactory<>("imgS"));

        
        
          Callback<TableColumn<Sponsor, String>, TableCell<Sponsor, String>> cellFoctory = (TableColumn<Sponsor, String> param) -> {
            // make cell containing buttons
            final TableCell<Sponsor, String> cell = new TableCell<Sponsor, String>() {
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
                            
                           
                                sponsor = sponsorTable.getSelectionModel().getSelectedItem();
                                ss.supprimer(sponsor);
                                refreshTable();  

                        });
                         editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            sponsor = sponsorTable.getSelectionModel().getSelectedItem();
                           
                               FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("../GUI/ajouterSponsor_1.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(ASController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            ASController asController = loader.getController();
                           
                        asController.setTextField(sponsor.getId(),sponsor.getNom(),sponsor.getType(),sponsor.getAdresse(),sponsor.getTel(),
                                sponsor.getEmail(),sponsor.getImgS());
                         
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            
                             
                             
                           

                        });
                          addIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                             sponsor = sponsorTable.getSelectionModel().getSelectedItem();
                             Image image;
                            try {
                                image = new Image(new FileInputStream(sponsor.getImgS()));
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
         sponsorTable.setItems(SponsorList);
       
    }
      
    
  public void onEdit() {
 sponsor = sponsorTable.getSelectionModel().getSelectedItem();
                             Image image;
                            try {
                                image = new Image(new FileInputStream(sponsor.getImgS()));
                            
                                imagev.setImage(image); 
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(ASController.class.getName()).log(Level.SEVERE, null, ex);
                            }
    
}
    @FXML
    private void logout(ActionEvent event) {
    }


    @FXML
    private void ajouter(ActionEvent event) {
            String nom =nomfld.getText();
            
        String type =typefld.getText();
      String adresse=adressefld.getText();
       Integer tel = Integer.parseInt(telfld.getText());
       String email =emailfld.getText();
        String imgS =imgSfld.getText();
        StringBuilder errors =new StringBuilder();
//        if(nomfld.getText().trim().isEmpty()){
//            errors.append("Please enter a name\n");
//        }
        if(( !Pattern.matches("[a-zA-Z]*", nomfld.getText()))||(nomfld.getText().trim().isEmpty())){
            errors.append("Please enter a valid name\n");
        }
        if(typefld.getText().trim().isEmpty()){
            errors.append("Please enter a type\n");
        }
        if(adressefld.getText().trim().isEmpty()){
            errors.append("Please enter an adresse\n");
        }
        if(Pattern.matches("[a-zA-Z]*", telfld.getText())||telfld.getText().isEmpty()){
            errors.append("please enter a valid number\n");
        
        }
     
        if(emailfld.getText().trim().isEmpty()){
            errors.append("Please enter an email\n");
        }
        
        if(imgSfld.getText().trim().isEmpty()){
            errors.append("Please enter a photo\n");
        }
       
        
        if(errors.length()>0){
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
        else
        {
            
            Sponsor s=new Sponsor(nom,type,adresse,tel,email,imgS);
            ss.ajouter(s);
            clean();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Sponsor Ajouté");
            alert.showAndWait();
            
        }
    }

    @FXML
    private void getImgS(ActionEvent event) {
        
       JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        String filename=f.getAbsolutePath();
        imgSfld.setText(filename);
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
        typefld.setText(null);
        adressefld.setText(null);
       telfld.setText(null);
         emailfld.setText(null);
          imgSfld.setText(null);
    }
    @FXML
    private void updatee(ActionEvent event) throws ParseException {
      
         
        String nome =nomfld.getText();
         String type =typefld.getText();
         String adresse=adressefld.getText();
          Integer tele = Integer.parseInt(telfld.getText());
         String emaile=emailfld.getText();
               
         
        
         String imagee = imgSfld.getText();
       
         
         
      
           Sponsor s=new Sponsor(nome,type,adresse,tele,emaile,imagee);
           s.setId(idsponsor);
            ss.modifier(s);
             clean();
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Sponsor modifié");
            alert.showAndWait();
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private void trimag(ActionEvent event) {
        if (combomag.getSelectionModel().equals("nom"))
            col="nom";
        else if   (combomag.getSelectionModel().equals("type"))
            col="type" ;
        else if   (combomag.getSelectionModel().equals("adresse"))
            col="adresse" ;
        else  if (combomag.getSelectionModel().equals("tel"))
            col="tel";
        else if   (combomag.getSelectionModel().equals("email"))
            col="email" ;
        research();
        

        

    }

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
  ServiceSponsor sm = new ServiceSponsor ();
        List<Sponsor> lm = sm.recherchermutli(txtrecherche.getText(),col,ord);
    sponsorTable.getItems().clear();
      ObservableList<Sponsor> datalist = FXCollections.observableArrayList(lm);
        
         
        
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));   
         image.setCellValueFactory(new PropertyValueFactory<>("image"));   
       
         
         
    sponsorTable.setItems(datalist); 
    
    nbmag.setText(String.valueOf(lm.size()));
}
}
