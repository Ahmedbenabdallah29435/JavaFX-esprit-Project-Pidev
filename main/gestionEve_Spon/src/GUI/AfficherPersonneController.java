/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import javafx.scene.image.Image;
import Services.PersonneService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
//import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.Account;
import entity.Personne;
import static GUI.main.Userconnected;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.Notifications;
import utils.Myconnexion;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javax.swing.JOptionPane;


import javafx.scene.image.*;

import java.net.*;
import java.util.Base64;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;

import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class AfficherPersonneController implements Initializable {

    @FXML
    private TableColumn<Personne,String> txtnom;
    @FXML
    private TableColumn<Personne,String> txtprenom;
    @FXML
    private TableColumn<Personne, Date> txtdatenaissance;
    @FXML
    private TableColumn<Personne, String> txtadresse;
    @FXML
    private TableColumn<Personne, String> txtmail;
    @FXML
    private TableColumn<Personne,Integer> txttel;
    @FXML
    private TableColumn<Personne,String> txtrole;
    @FXML
    private TableColumn<Personne,String> txtmdp;
    @FXML
    private TableColumn<Personne,String> txtnomEquipe;
    @FXML
    private TableColumn<Personne,String> txtetat;
    @FXML
    private Button txtajouter;
   
    @FXML
    private TableView<Personne> tablePersonne;
    @FXML
    private Button retourAcceuil;
    
    public ObservableList <Personne> data=FXCollections.observableArrayList();
    @FXML
    private Button afficher;
    
     @FXML
    private Button btnavertir;
    @FXML
    private Button btnsupprimer;
    
    @FXML
    private TextField rechfield;
    @FXML
    private Button rech;
   
        public ObservableList <Personne> data1=FXCollections.observableArrayList();
        public ObservableList <Personne> data2=FXCollections.observableArrayList();
    @FXML
    private Button btnpdf;
    @FXML
    private ImageView rech1;
    @FXML
    private Button btnlogout;
    @FXML
    private Label nomadmin;
    @FXML
    private Button btnexcel;
    @FXML
    private Button btnsms;
    @FXML
    private TableColumn<Personne, Integer> txtid;
    @FXML
    private ComboBox<String> tri;
    @FXML
    private Button btntri;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          setlb_nom(Userconnected.getNom());
          ObservableList<String>  options = FXCollections.observableArrayList("adresse","mail","nom","prenom");
          tri.setItems(options);
             
         
        
         
    }    
    
     PersonneService sp=new PersonneService();
    public void setlb_nom(String email) {
        this.nomadmin.setText(email);
    }
     
    @FXML
    private void ajouter(ActionEvent event) throws IOException {
          FXMLLoader loader= new FXMLLoader(getClass().getResource("ajoutPersonne.fxml"));
        Parent root = loader.load();
        AjoutPersonneController ap= loader.getController();
        txtajouter.getScene().setRoot(root);
    }

   

    @FXML
    private void retourAcceuil(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("DocFXML.fxml"));
        Parent root = loader.load();
        DocFXMLController ap= loader.getController();
        retourAcceuil.getScene().setRoot(root);
    }

    @FXML
    private void afficher(ActionEvent event) {
       
        PersonneService p=new PersonneService();
         Connection cnx =Myconnexion.getInstance().getCnx();
         List <Personne> l=p.afficher();
         
         data=FXCollections.observableArrayList(l);
       
        /*try
        {
                Connection cnx =MaConnexion.getInstance().getCnx();
              String sql ="select * from personne";
              
        Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                Personne p = new Personne();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setDatenaissance(rs.getDate("datenaissance"));
                p.setAdresse(rs.getString("adresse"));
                p.setMail(rs.getString("mail"));
                p.setTel(rs.getInt("tel"));
                p.setRole(rs.getString("role"));
                p.setMdp(rs.getString("mdp"));
                p.setNomEquipe(rs.getString("nomEquipe"));
                p.setEtat(rs.getString("etat"));
                data.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        */
       txtid.setCellValueFactory(new PropertyValueFactory <Personne,Integer>("id"));
        txtnom.setCellValueFactory(new PropertyValueFactory <Personne,String>("nom"));
         txtprenom.setCellValueFactory(new PropertyValueFactory <Personne,String>("prenom"));
          txtdatenaissance.setCellValueFactory(new PropertyValueFactory <Personne,Date>("datenaissance"));
           txtadresse.setCellValueFactory(new PropertyValueFactory <Personne,String>("adresse"));
            txtmail.setCellValueFactory(new PropertyValueFactory <Personne,String>("mail"));
             txttel.setCellValueFactory(new PropertyValueFactory <Personne,Integer>("tel"));
              txtrole.setCellValueFactory(new PropertyValueFactory <Personne,String>("role"));
              txtmdp.setCellValueFactory(new PropertyValueFactory <Personne,String>("mdp"));
             
        txtnomEquipe.setCellValueFactory(new PropertyValueFactory <Personne,String>("nomEquipe"));
        txtetat.setCellValueFactory(new PropertyValueFactory <Personne,String>("etat"));
        tablePersonne.setItems(data);
        
        // modification 
        
        
        txtnom.setCellFactory(TextFieldTableCell.forTableColumn());
        txtnom.setOnEditCommit((e) ->
                {
                    
 
                 if( p.updateName(tablePersonne.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     tablePersonne.getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
                     tablePersonne.refresh();
         });
       
        txtprenom.setCellFactory(TextFieldTableCell.forTableColumn());
        txtprenom.setOnEditCommit((e) ->
                {
                    
 
                 if( p.updatePrenom(tablePersonne.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     tablePersonne.getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
                     tablePersonne.refresh();
         });
        
        txtadresse.setCellFactory(TextFieldTableCell.forTableColumn());
        
         txtadresse.setOnEditCommit((e) ->
                {
                    
 
                 if( p.updateadresse(tablePersonne.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     tablePersonne.getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
                     tablePersonne.refresh();
         });
        txtnomEquipe.setCellFactory(TextFieldTableCell.forTableColumn());
        
         txtnomEquipe.setOnEditCommit((e) ->
                {
                    
 
                 if( p.updatenomEquipe(tablePersonne.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     tablePersonne.getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
                     tablePersonne.refresh();
         });
        txtmail.setCellFactory(TextFieldTableCell.forTableColumn());
        
         txtmail.setOnEditCommit((e) ->
                {
                    
 
                 if( p.updatemail(tablePersonne.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     tablePersonne.getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
                     tablePersonne.refresh();
         });
        
         txtmdp.setCellFactory(TextFieldTableCell.forTableColumn());
        
         txtmdp.setOnEditCommit((e) ->
                {
                    
 
                 if( p.updatemdp(tablePersonne.getItems().get(e.getTablePosition().getRow()),e.getNewValue()))
                     tablePersonne.getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
                     tablePersonne.refresh();
         });
        
         
         
         
         
         
         
         
        
        tablePersonne.setEditable(true);
                    
                    
                    
        }
        
       


    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
       Personne personne=tablePersonne.getSelectionModel().getSelectedItem();
       PersonneService sp=new PersonneService();
       sp.supprimer(personne.getId());
       data.clear();
       this.afficher(event);
       Notifications notificationBuilder=Notifications.create()
              .title("Alert").text("Utilisateur supprimé avec succès").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER_LEFT)
              .onAction(new EventHandler<ActionEvent>(){
                  public void handle(ActionEvent event)
                      {
                          System.out.println("clicked ON");
                      }
              });
      notificationBuilder.darkStyle();
      notificationBuilder.show();
    }
    
   public Personne modifierP(ActionEvent event) throws IOException
   {
        Personne personne=tablePersonne.getSelectionModel().getSelectedItem();
      return personne;
   }

    @FXML
    private void recherche(ActionEvent event) throws SQLException {
       
      /*    String nom=rechfield.getText();
        String email=rechfield.getText();
        String adresse=rechfield.getText();
        PersonneService p=new PersonneService();
      List <Personne> l=  p.rechercherParNom(nom);
       List <Personne> l1=  p.rechercherParEmail(email);
      List <Personne> l2=p.rechercherParAdresse(adresse);
      data=FXCollections.observableArrayList(l);
        data=FXCollections.observableArrayList(l1);
           data=FXCollections.observableArrayList(l2);
          txtid.setCellValueFactory(new PropertyValueFactory <Personne,Integer>("id"));
        txtnom.setCellValueFactory(new PropertyValueFactory <Personne,String>("nom"));
         txtprenom.setCellValueFactory(new PropertyValueFactory <Personne,String>("prenom"));
          txtdatenaissance.setCellValueFactory(new PropertyValueFactory <Personne,Date>("datenaissance"));
           txtadresse.setCellValueFactory(new PropertyValueFactory <Personne,String>("adresse"));
            txtmail.setCellValueFactory(new PropertyValueFactory <Personne,String>("mail"));
             txttel.setCellValueFactory(new PropertyValueFactory <Personne,Integer>("tel"));
              txtrole.setCellValueFactory(new PropertyValueFactory <Personne,String>("role"));
              txtmdp.setCellValueFactory(new PropertyValueFactory <Personne,String>("mdp"));
             
        txtnomEquipe.setCellValueFactory(new PropertyValueFactory <Personne,String>("nomEquipe"));
        txtetat.setCellValueFactory(new PropertyValueFactory <Personne,String>("etat"));
        tablePersonne.setItems(data);
     */
      
      
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Personne> filteredData = new FilteredList<>(data, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		rechfield.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(p -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (p.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (p.getMail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (p.getAdresse().indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
               
      
      
      // 3. Wrap the FilteredList in a SortedList. 
		SortedList<Personne> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tablePersonne.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tablePersonne.setItems(sortedData);
      
    }

    @FXML
    private void pdf(ActionEvent event) {
    
    String FILE_NAME = "C:\\Users\\Desktop\\java.pdf";
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));
            document.open();
            Paragraph p = new Paragraph();
            p.add("Liste Mailliing Utilisateurs");
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            
            Connection cnx =Myconnexion.getInstance().getCnx();
            String query = "select * from personne";
            Statement stmt = null;
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Paragraph p3 = null;
            while (rs.next()) {
                p3 = new Paragraph();
                
                
              
                p3.add(rs.getString("mail"));
                document.add(p3);
            }
            document.close();
            System.out.println("Done");
            Notifications notificationBuilder=Notifications.create()
              .title("Alert").text("PDF crée avec succès").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER_LEFT)
              .onAction(new EventHandler<ActionEvent>(){
                  public void handle(ActionEvent event)
                      {
                          System.out.println("clicked ON");
                      }
              });
      notificationBuilder.darkStyle();
      notificationBuilder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     @FXML
    private void avertir(ActionEvent event) {
    
     Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("benmansourmalak18@gmail.com", "malak2001");
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("benmansourmalak18@gmail.com"));
                        String mail=((String) txtmail.getCellData(tablePersonne.getSelectionModel().getFocusedIndex()));
			message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(mail));
			message.setSubject("Avertissement!!");
			message.setText("Salut Monsieur "+((String) txtnom.getCellData(tablePersonne.getSelectionModel().getFocusedIndex()))+" "+((String) txtprenom.getCellData(tablePersonne.getSelectionModel().getFocusedIndex()))+".\n\n Votre compte est en etat de desactivation veuillez verifier vos parametres!! ");
                        
			Transport.send(message);
                         Notifications notificationBuilder=Notifications.create()
              .title("Alert").text("Email Envoyé ").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER_LEFT)
              .onAction(new EventHandler<ActionEvent>(){
                  public void handle(ActionEvent event)
                      {
                          System.out.println("clicked ON");
                      }
              });
      notificationBuilder.darkStyle();
      notificationBuilder.show();
                }catch (Exception e){
                }
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
               FXMLLoader loader= new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
       LoginController ap= loader.getController();
        
        btnlogout.getScene().setRoot(root);
    }

    @FXML
    private void excel(ActionEvent event) {
        
        try{
          Connection cnx =Myconnexion.getInstance().getCnx();
            String query = "select * from personne";
            Statement stmt = null;
            stmt = cnx.createStatement();  
             ResultSet rs = stmt.executeQuery(query);
               XSSFWorkbook wb=new XSSFWorkbook();
               XSSFSheet sheet=wb.createSheet("malak");
               XSSFRow header=sheet.createRow(0);
               header.createCell(0).setCellValue("id");
               header.createCell(1).setCellValue("nom");
               header.createCell(2).setCellValue("prenom");
               header.createCell(3).setCellValue("datenaissance");
               header.createCell(4).setCellValue("adresse");
               header.createCell(5).setCellValue("mail");
               header.createCell(6).setCellValue("tel");
               header.createCell(7).setCellValue("role");
               header.createCell(8).setCellValue("mdp");
               header.createCell(9).setCellValue("nomEquipe");
               header.createCell(10).setCellValue("etat");
               int index=1;
               while (rs.next()) {
                XSSFRow row=sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getString("id"));
                 
         row.createCell(1).setCellValue(rs.getString("nom"));
          row.createCell(2).setCellValue(rs.getString("prenom"));
          row.createCell(3).setCellValue(rs.getString("datenaissance"));
          row.createCell(4).setCellValue(rs.getString("adresse"));
          row.createCell(5).setCellValue(rs.getString("mail"));
          row.createCell(6).setCellValue(rs.getString("tel"));
          row.createCell(7).setCellValue(rs.getString("role"));
           row.createCell(8).setCellValue(rs.getString("mdp"));
            row.createCell(9).setCellValue(rs.getString("nomEquipe"));
             row.createCell(10).setCellValue(rs.getString("etat"));
             index++;
           
        }
               FileOutputStream fileout= new FileOutputStream("User.xlsx");
               wb.write(fileout);
               fileout.close();
               
            } catch (SQLException | FileNotFoundException ex) {
                Logger.getLogger(AfficherPersonneController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AfficherPersonneController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
          Notifications notificationBuilder=Notifications.create()
              .title("Alert").text("EXCEL crée avec succès").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER_LEFT)
              .onAction(new EventHandler<ActionEvent>(){
                  public void handle(ActionEvent event)
                      {
                          System.out.println("clicked ON");
                      }
              });
      notificationBuilder.darkStyle();
      notificationBuilder.show();
        
    
             }  

    @FXML
    private void sms(ActionEvent event) throws IOException {
          int tel=((int) txttel.getCellData(tablePersonne.getSelectionModel().getFocusedIndex()));
          
          try {
			// Construct data
			String apiKey = "apikey=" + "NTQ0OTcwNDMzMDM0NmE2YzQ5NDg2OTU3NTI1NzdhMzE=";
			String message = "&message=" + "This is your message";
			String sender = "&sender=" + "malakJava";
			String numbers = "&numbers=" + tel;
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				//stringBuffer.append(line);
                                 JOptionPane.showMessageDialog(null, "message"+line);
                                  Notifications notificationBuilder=Notifications.create()
              .title("Notifications").text("SMS Envoyé avec succès").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              .position(Pos.CENTER_LEFT)
              .onAction(new EventHandler<ActionEvent>(){
                  public void handle(ActionEvent event)
                      {
                          System.out.println("clicked ON");
                      }
              });
      notificationBuilder.darkStyle();
      notificationBuilder.show();
			}
			rd.close();
			
			//return stringBuffer.toString();
		} catch (Exception e) {
			//System.out.println("Error SMS "+e);
                        JOptionPane.showMessageDialog(null, e);
			//return "Error "+e;
		}
        


//sendSMS("malakpi","Malak2001");
    }

     private void sendSMS(String username,String password) throws MalformedURLException, IOException
    {  int tel=((int) txttel.getCellData(tablePersonne.getSelectionModel().getFocusedIndex()));
        
    try{
      String myURI = "https://api.bulksms.com/v1/messages";

    // change these values to match your own account
    String myUsername = ""+username+"";
    String myPassword = ""+password+"";

    // the details of the message we want to send
    String myData = "{to: \""+tel+"\", encoding: \"UNICODE\", body: \"sms pi java\"}";

    // if your message does not contain unicode, the "encoding" is not required:
    // String myData = "{to: \"1111111\", body: \"Hello Mr. Smith!\"}";

    // build the request based on the supplied settings
    URL url = new URL(myURI);
    HttpURLConnection request = (HttpURLConnection) url.openConnection();
    request.setDoOutput(true);

    // supply the credentials
    String authStr = myUsername + ":" + myPassword;
    String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
    request.setRequestProperty("Authorization", "Basic " + authEncoded);

    // we want to use HTTP POST
    request.setRequestMethod("POST");
    request.setRequestProperty( "Content-Type", "application/json");

    // write the data to the request
    OutputStreamWriter out = new OutputStreamWriter(request.getOutputStream());
    out.write(myData);
    out.close();

    // try ... catch to handle errors nicely
    try {
      // make the call to the API
      InputStream response = request.getInputStream();
      BufferedReader in = new BufferedReader(new InputStreamReader(response));
      String replyText;
      while ((replyText = in.readLine()) != null) {
        System.out.println(replyText);
      }
      in.close();
    } catch (IOException ex) {
      System.out.println("An error occurred:" + ex.getMessage());
      BufferedReader in = new BufferedReader(new InputStreamReader(request.getErrorStream()));
      // print the detail that comes with the error
      String replyText;
      while ((replyText = in.readLine()) != null) {
        System.out.println(replyText);
      }
      in.close();
    }
    request.disconnect();      
     
    }catch(Exception e)
    {
        System.out.println(e);
    }
  
}       

    @FXML
    private void trier(ActionEvent event) throws SQLException {
        String type=tri.getValue();
        List <Personne> list=new ArrayList <>();
          PersonneService sp =new PersonneService();
        
        if(type=="adresse")
        {
          list=sp.TrierParAdresse();
        }
        else if(type=="nom")
        {
            list=sp.TrierParNOM();
        }
        else if(type=="prenom")
        {
            list=sp.TrierParPrenom();
        }
        else 
        {
            list=sp.TrierParEmail();
        }
         ObservableList<Personne> observableList = FXCollections.observableArrayList();

       txtid.setCellValueFactory(new PropertyValueFactory <Personne,Integer>("id"));
        txtnom.setCellValueFactory(new PropertyValueFactory <Personne,String>("nom"));
         txtprenom.setCellValueFactory(new PropertyValueFactory <Personne,String>("prenom"));
          txtdatenaissance.setCellValueFactory(new PropertyValueFactory <Personne,Date>("datenaissance"));
           txtadresse.setCellValueFactory(new PropertyValueFactory <Personne,String>("adresse"));
            txtmail.setCellValueFactory(new PropertyValueFactory <Personne,String>("mail"));
             txttel.setCellValueFactory(new PropertyValueFactory <Personne,Integer>("tel"));
              txtrole.setCellValueFactory(new PropertyValueFactory <Personne,String>("role"));
              txtmdp.setCellValueFactory(new PropertyValueFactory <Personne,String>("mdp"));
             
        txtnomEquipe.setCellValueFactory(new PropertyValueFactory <Personne,String>("nomEquipe"));
        txtetat.setCellValueFactory(new PropertyValueFactory <Personne,String>("etat"));
        
        
         list.forEach(e -> {
                
                observableList.add(e);
                //System.out.println(observableList);
            });
            tablePersonne.setItems(observableList);
    }
        
           

        
    }
      

