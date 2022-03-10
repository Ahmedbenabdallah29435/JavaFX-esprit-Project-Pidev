/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import java.awt.Component;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import Services.PersonneService;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.AddressException;

/**
 * FXML Controller class
 *
 * @author malak_6
 */
public class MotdepasseoublieController implements Initializable {

    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_verif;
    @FXML
    private Button verifierCode;
    int randomCode;
    @FXML
    private Button btn_envoyer;
    @FXML
    private Button btnajout1;
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoyerMailMdp(ActionEvent event) throws SQLException, AddressException, MessagingException {
 
       try{
           
       
        Random rand =new Random();
         randomCode = rand.nextInt(999999);
        String host = "smtp.gmail.com";
        String user = "tnafes.tnafes@gmail.com";
        String pass="tnafes123";
        String to =tf_email.getText();
        String sujet ="reset pot de passe ";
        String message="your reset code is "+randomCode;
        boolean sessionDebug =  false;
        Properties pros = System.getProperties();
        pros.put("mail.smtp.starttls.enable","true");
        pros.put("mail.smtp.host","host");
        pros.put("mail.smtp.port", "587");
        pros.put("mail.smtp.auth", "true");
        pros.put("mail.smtp.starttls.required", "true");
        java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        Session mailSession = Session.getDefaultInstance(pros,null);
        mailSession.setDebug(sessionDebug);
        Message msg =new MimeMessage(mailSession);
        msg.setFrom(new InternetAddress(user));
        InternetAddress [] address = { new InternetAddress(to) };
        msg.setRecipients(Message.RecipientType.TO, address);
        msg.setSubject(sujet);
        msg.setText(message);
        Transport transport = mailSession.getTransport("smtp");
        transport.connect(host,user,pass);
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
        JOptionPane.showMessageDialog(null, "code envoye a la boite mail");
       }catch(Exception ex){
           Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane,ex);
            System.out.println(ex.getMessage());
 
       }
        
        
         
    }
    
    
 

    @FXML
    private void verifierCode(ActionEvent event) throws IOException {
         if(Integer.valueOf(tf_verif.getText())==randomCode)  {
          
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/resetmdp.fxml"));
           
            Scene scene = new Scene(loader.load());
            ResetmdpController controller = loader.getController();
            controller.initData(tf_email.getText());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
      }else{
          JOptionPane.showMessageDialog(null, "code ghalet");
      }
        
    }

    @FXML
    private void acceuil(ActionEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        LoginController ap= loader.getController();
        btnajout1.getScene().setRoot(root);
    }
    }
    

