/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.itextpdf.text.BaseColor;
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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.Locale;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.Myconnexion;
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
    @FXML
    private ImageView btnpdf;
    @FXML
    private ImageView btnexcel;
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

    @FXML
    private void exportPDF(MouseEvent event)throws ClassNotFoundException, ClassNotFoundException, SQLException, IOException, URISyntaxException, DocumentException {
        try {
                
            Font blueFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new CMYKColor(255, 0, 0, 0));
Font redFont = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, new CMYKColor(0, 255, 0, 0));
Font Sponsor = FontFactory.getFont(FontFactory.TIMES_BOLD, 40, Font.UNDERLINE, new CMYKColor(0, 100, 0, 50));
Font titledate = FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, new CMYKColor(0, 100, 100, 80));

Font titledesc = FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, new CMYKColor(50, 100, 0, 0));
            
            Class.forName("com.mysql.jdbc.Driver");
                 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bruteforce", "root", "");
                 PreparedStatement pt = con.prepareStatement("select * from sponsor");
                 ResultSet rs = pt.executeQuery();
            
                       /* Step-2: Initialize PDF documents - logical objects */

                       Document my_pdf_report = new Document(){};
                       
                       PdfWriter.getInstance(my_pdf_report, new FileOutputStream("pdf_report_from_sql_using_java.pdf"));
                       
                              my_pdf_report.open();  
//                             my_pdf_report.add(new Paragraph(new Date().toString()));
//                            Image img = Image.getInstance("c:/6.png");
//                            my_pdf_report.add(img);
                             my_pdf_report.add(new Paragraph("                                                                Sponsor"));
                             my_pdf_report.add(new Paragraph("                         "));
                             my_pdf_report.addCreationDate();
              long millis = System.currentTimeMillis();
        java.sql.Date DateRapport = new java.sql.Date(millis);

        String DateLyoum = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(DateRapport);//yyyyMMddHHmmss
        System.out.println("DateLyoummmmmmmmmmmmmmmmmmmmm   " + DateLyoum);
                             my_pdf_report.add(new Paragraph("                                                              "+DateRapport));
            my_pdf_report.add(new Paragraph("                         "));
                       
                       //we have four columns in our table
                       PdfPTable my_report_table = new PdfPTable(5);
                             
                       //create a cell object
                       PdfPCell table_cell;
                       
                       
                                       table_cell=new PdfPCell(new Phrase(" nom"));
                                      table_cell.setBackgroundColor(BaseColor.WHITE);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase(" type"));
                                      table_cell.setBackgroundColor(BaseColor.WHITE);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("adresse"));
                                       table_cell.setBackgroundColor(BaseColor.WHITE);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase(" tel"));
                                      table_cell.setBackgroundColor(BaseColor.WHITE);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("email"));
                                       table_cell.setBackgroundColor(BaseColor.WHITE);
                                       my_report_table.addCell(table_cell);
                                       
                                       

                                      while(rs.next()){
                                      
                                       String nom= rs.getString("nom");
                                       table_cell=new PdfPCell(new Phrase(nom));
                                       my_report_table.addCell(table_cell);
                                       String type= rs.getString("type");
                                       table_cell=new PdfPCell(new Phrase(type));
                                       my_report_table.addCell(table_cell);
                                       String adresse=rs.getString("adresse");
                                       table_cell=new PdfPCell(new Phrase(adresse));
                                       my_report_table.addCell(table_cell);
                                       String tel= rs.getString("tel");
                                       table_cell=new PdfPCell(new Phrase(tel));
                                       my_report_table.addCell(table_cell);
                                       String email=rs.getString("email");
                                       table_cell=new PdfPCell(new Phrase(email));
                                       my_report_table.addCell(table_cell);
                                       
                                        
                       }
                       /* Attach report table to PDF */
                       
                       my_pdf_report.add(my_report_table); 
                       
                       System.out.println(my_pdf_report);
                       my_pdf_report.close();
                       JOptionPane.showMessageDialog(null, "impression effectuée");

                       /* Close all DB related objects */
                       rs.close();
                       pt.close(); 
                       con.close();               
    File myFile = new File("C:/Users/AhmedBenAbdallah/Desktop/gestionEve_Spon/pdf_report_from_sql_using_java.pdf");
    Desktop.getDesktop().open(myFile);

       } catch (FileNotFoundException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
       }


    
        
    }

    @FXML
    private void exportExcel(MouseEvent event) throws SQLException, FileNotFoundException, IOException {
        Connection cnx = Myconnexion.getInstance().getCnx();
        String query = "Select * from sponsor";
         PreparedStatement pst = cnx.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Détails sponsor");
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("Nom");
            header.createCell(1).setCellValue("Type");
            header.createCell(2).setCellValue("Adresse");
            header.createCell(3).setCellValue("Telephone");
             header.createCell(4).setCellValue("Email");
        
              
            
            int index = 1;
            while(rs.next()){
                XSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getString("nom"));
                row.createCell(1).setCellValue(rs.getString("type"));
                row.createCell(2).setCellValue(rs.getString("adresse"));
                row.createCell(3).setCellValue(rs.getInt("tel"));
                row.createCell(4).setCellValue(rs.getString("email"));
                
               
                index++;
            }
            
            FileOutputStream file = new FileOutputStream("Détails sponsor.xlsx");
            wb.write(file);
            file.close();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Exportation effectuée!!!");
            alert.showAndWait();
            pst.close();
            rs.close();
            File myFile = new File("C:/Users/AhmedBenAbdallah/Desktop/gestionEve_Spon/Détails sponsor.xlsx");
             Desktop.getDesktop().open(myFile);
    }

    
}
