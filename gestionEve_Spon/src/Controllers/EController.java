/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import GUI.ChartController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.Evennement;
import entity.Sponsor;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.util.StringConverter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.ServiceEvennement;
import service.ServiceSponsor;
import utils.Myconnexion;

/**
 * FXML Controller class
 *
 * @author AhmedBenAbdallah
 */
public class EController implements Initializable {
    
    ServiceEvennement se=new ServiceEvennement();
    ObservableList<Evennement> evenList = FXCollections.observableArrayList(); 
      Evennement even =null;
 ServiceSponsor ss =new ServiceSponsor();
   
     ObservableList<Sponsor> sponsorList = FXCollections.observableArrayList();

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
    private TextField txtrecherche;
    private ComboBox<String> combomag;
    private TextField nbmag;
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
    private ImageView imgSV;
    @FXML
    private DatePicker datefld;
    
    private ImageView imagev;
    int idsponsor;
    String col,ord ;
    @FXML
    private TableColumn<Evennement,String> lieu;
    @FXML
    private ComboBox<Sponsor> sponsor;
    @FXML
    private TextField sponsornom;
    @FXML
    private TableColumn<Evennement,String> id_S;
    @FXML
    private Button btnAcceuil;
    @FXML
    private TextField tot;
    @FXML
    private Button btnev;
    @FXML
    private Button btnsp;
    private Button btnev1;
    @FXML
    private Button btnSignout;
    @FXML
    private Button rr;
    @FXML
    private ImageView btnexcel;
    @FXML
    private Button totbtn;
    @FXML
    private Button btnjour;
    @FXML
    private Button btnstat;
    @FXML
    private Button imgSbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         sponsorList.addAll(ss.afficher());
       sponsor.setItems(sponsorList);
        StringConverter<Sponsor> converter = new StringConverter<Sponsor>() {
            @Override
            public String toString(Sponsor object) {
                return object.getNom();
            }

            @Override
            public Sponsor fromString(String string) {
                return null;
            }
        }; 
      sponsor.setConverter(converter);
      
 LoadDate();
        
    } 
     


     void setTextField(String nome, String lieue, String description,  int nb_place, String imageEe,Sponsor sponsoree) 
     {
      
        nomfld.setText(nome);
      
    
       
        lieufld.setText(lieue);
        desfld.setText(String.valueOf(description));
        nbrfld.setText(String.valueOf(nb_place));
        
        
        
        imgEfld.setText(imageEe);
       sponsornom.setText(String.valueOf(sponsoree));
        

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
   id_S.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getSponsor().getNom()));
        
        
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
                            loader.setLocation(getClass().getResource("../GUI/ajouterEven.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(ASController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                           EController eController = loader.getController();
                           
                        eController.setTextField(even.getNom(),even.getLieu(),even.getDescription(),
                               even.getNb_place(),even.getImageE(),even.getSponsor());
                         
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
       Sponsor s=sponsor.getSelectionModel().getSelectedItem();
       sponsornom.setText(String.valueOf(s));
         
        StringBuilder errors =new StringBuilder();
//      
 if(nomfld.getText().trim().isEmpty()){
            errors.append("Please enter a name\n");
        }
        if(( !Pattern.matches("[a-zA-Z]*", nomfld.getText()))||(nomfld.getText().trim().isEmpty())){
            errors.append("Please enter a valid name\n");
        }
        if(datefld.getValue()==null){
            errors.append("Please enter a date\n");
        }
        if(lieufld.getText().trim().isEmpty()){
            errors.append("Please enter an place\n");
        }
        if(Pattern.matches("[a-zA-Z]*",nbrfld.getText())||nbrfld.getText().isEmpty()){
            errors.append("please enter a valid number\n");
        
        }
     
        if(desfld.getText().trim().isEmpty()){
            errors.append("Please enter a description\n");
        }
        
        if(imgEfld.getText().trim().isEmpty()){
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
           
            
           Evennement e=new Evennement(nom,datee,lieu,description,nb_place,imageE,s);
            se.ajouter(e);
            clean();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Event Ajouté");
            alert.showAndWait();
            
        }
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
        sponsornom.setText(null);
          imgEfld.setText(null);
    }
    private void updatee(ActionEvent event) throws ParseException {
      
         String nom =nomfld.getText();
           long millis=System.currentTimeMillis();  
         java.sql.Date date = new java.sql.Date(millis); 
         
        Date datee = Date.valueOf(datefld.getValue());
          
      String lieu=lieufld.getText();
      
       String description =desfld.getText();
      
       Integer nb_place = Integer.parseInt(nbrfld.getText());
       
        String imageE =imgEfld.getText();
       Sponsor s=sponsor.getSelectionModel().getSelectedItem();
       sponsornom.setText(String.valueOf(s));
         
        StringBuilder errors =new StringBuilder();
        if(nomfld.getText().trim().isEmpty()){
            errors.append("Please enter a name\n");
        }
        if(( !Pattern.matches("[a-zA-Z]*", nomfld.getText()))||(nomfld.getText().trim().isEmpty())){
            errors.append("Please enter a valid name\n");
        }
        if(datefld.getValue()==null){
            errors.append("Please enter a date\n");
        }
        if(lieufld.getText().trim().isEmpty()){
            errors.append("Please enter an place\n");
        }
        if(Pattern.matches("[a-zA-Z]*",nbrfld.getText())||nbrfld.getText().isEmpty()){
            errors.append("please enter a valid number\n");
        
        }
     
        if(desfld.getText().trim().isEmpty()){
            errors.append("Please enter a description\n");
        }
        
        if(imgEfld.getText().trim().isEmpty()){
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
            
           Evennement e=new Evennement(nom,datee,lieu,description,nb_place,imageE,s);
            se.modifier(e);
            clean();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Event Ajouté");
            alert.showAndWait();
            
            
    }
    }
//    
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
    private void ordremag(ActionEvent event) {
        
            ord="ASC";
      
        
        research();
    }
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
         id_S.setCellValueFactory(new PropertyValueFactory<>("idS")); 
       
         
         
    evenTable.setItems(datalist); 
    
    nbmag.setText(String.valueOf(lm.size()));
}


   
    @FXML
    private void select(ActionEvent event) {
      
        String s=sponsor.getSelectionModel().getSelectedItem().getNom();
        sponsornom.setText(s);
       
    }

    

    @FXML
    private void logout(ActionEvent event) {
    }

    @FXML
    private void exportExcel(MouseEvent event)throws SQLException, FileNotFoundException, IOException {
        Connection cnx = Myconnexion.getInstance().getCnx();
        String query = "Select * from evenement";
         PreparedStatement pst = cnx.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Détails evenement");
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("Nom");
            header.createCell(1).setCellValue("Date");
            header.createCell(2).setCellValue("Lieu");
            header.createCell(3).setCellValue("Description");
             header.createCell(4).setCellValue("Nb_place");
      
              
            
            int index = 1;
            while(rs.next()){
                XSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getString("nom"));
                row.createCell(1).setCellValue(rs.getDate("date"));
                row.createCell(2).setCellValue(rs.getString("lieu"));
                row.createCell(3).setCellValue(rs.getString("description"));
                row.createCell(4).setCellValue(rs.getInt("nb_place"));
                
                
               
                index++;
            }
            
            FileOutputStream file = new FileOutputStream("Détails evenement.xlsx");
            wb.write(file);
            file.close();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Exportation effectuée!!!");
            alert.showAndWait();
            pst.close();
            rs.close();
            File myFile = new File("C:/Users/AhmedBenAbdallah/Desktop/gestionEve_Spon/Détails evenement.xlsx");
             Desktop.getDesktop().open(myFile);
    }
    


    @FXML
    private void totalevent(ActionEvent event) {
         ServiceEvennement s=new ServiceEvennement();
        Connection cnx =Myconnexion.getInstance().getCnx();
         
              String l=s.TotalEvent();
              
                    
                    
                    
                    tot.setText(l);
    }


    @FXML
    private void rester(ActionEvent event) {
        Evennement e=evenTable.getSelectionModel().getSelectedItem();
       ServiceEvennement se=new ServiceEvennement();
       //sp.supprimer(sponsor.getId());
       String x=null;
      x=se.test1(e);
              JOptionPane.showMessageDialog(null,"jours restants "+x);
    }

    @FXML
    private void showchart(ActionEvent event) {
           try {
            List<Evennement> le=se.afficher();
    ObservableList<Evennement> data =FXCollections.observableArrayList(le);
            FXMLLoader chart= new FXMLLoader(getClass().getResource("../GUI/chart.fxml"));
            Parent root = chart.load();
            ChartController mc = chart.getController();
           
           
            Scene scene = new Scene(root);
           Stage modifStage = new Stage();
            
            modifStage.setTitle("Nombre des Evenements / Sponsors");
            modifStage.setScene(scene);
            modifStage.show();
            
             ChartController controller = chart.getController();
        controller.setEvennementData(data,le.size());
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(EController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private void acceuil(ActionEvent event) throws IOException {
       
         Parent root = FXMLLoader.load(getClass().getResource("../GUI/Acceuil.fxml"));
        btnAcceuil.getScene().setRoot(root);

    }
    private void acceuil1(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("../GUI/Acceuil.fxml"));
        btnAcceuil.getScene().setRoot(root);
    }
    private void gererevent1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/Even.fxml"));
        btnev1.getScene().setRoot(root);
    }
    private void gerersponsor1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/ajouterSponsor.fxml"));
        btnsp.getScene().setRoot(root);
    }
    
}
