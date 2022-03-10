/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Controllers.A;
import entity.Personne;
import static GUI.main.Userconnected;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import utils.Myconnexion;
import utils.UserSession;

/**
 * FXML Controller class
 *
 * @author malak_6
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtmail;
    @FXML
    private Button btnlogin;
    @FXML
    private PasswordField txtmdp;

      ResultSet rs = null;
     PreparedStatement pst = null;
    @FXML
    private Button btninscrire;
    @FXML
    private Button btnmdp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
    }    

    @FXML
    private void login(ActionEvent event) throws NoSuchAlgorithmException, SQLException, IOException {
          
    /*
        String mdp =txtmdp.getText();
       
        Connection cnx =MaConnexion.getInstance().getCnx();
       MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(mdp.getBytes());

       byte byteData[] = md.digest();

        //convertir le tableau de bits en une format hexadécimal - méthode 1
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
           sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        String mdpCrypte = sb.toString();
       
        String requete ="Select * from personne where mail = ? and mdp = ? ";
        try{
            pst = cnx.prepareStatement(requete);
            pst.setString(1, txtmail.getText());
            pst.setString(2, txtmdp.getText());
           System.out.println("mot de passe crypte"+mdpCrypte);
            rs = pst.executeQuery();
           
             if(rs.next()){
             
                if(rs.getString("role").equals("admin")){
                    
                    JOptionPane.showMessageDialog(null, "Connection avec succées : email et mdp correcte ");
                     Personne u=new Personne(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getDate("datenaissance"),rs.getString("adresse"),rs.getString("mail"),
                  rs.getInt("tel"),rs.getString("role"),rs.getString("mdp"),rs.getString("nomEquipe"),rs.getString("etat")
                  
                  );
                  
                  
                   UserSession.setInstace(u);
                    Personne u1=  UserSession.getInstace().getUtilisateur();
                  //  btnlogin.getScene().getWindow().hide();

                  /* Personne p = new Personne();
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
                    
                  /////////  
                    
                  //  UserSession.setInstace(p);
                    //Personne u1=  UserSession.getInstace().getUtilisateur();
                    FXMLLoader loader= new FXMLLoader(getClass().getResource("DocFXML.fxml"));
                   Parent root = loader.load();
                  DocFXMLController ap= loader.getController();
                    btnlogin.getScene().setRoot(root);
                    
                }
              else  if(rs.getString("role").equals("respo")){
                    
                    JOptionPane.showMessageDialog(null, "Connection avec succées : email et mdp correcte ");
                 
                  Personne u=new Personne(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getDate("datenaissance"),rs.getString("adresse"),rs.getString("mail"),
                  rs.getInt("tel"),rs.getString("role"),rs.getString("mdp"),rs.getString("nomEquipe"),rs.getString("etat")
                  
                  );
                  
                  
                   UserSession.setInstace(u);
                    Personne u1=  UserSession.getInstace().getUtilisateur();
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("gui/ProfilResponsable.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage mainStage = new Stage();

                    ProfilResponsableController detailPatientC = loader.getController();

                    detailPatientC.initData(u);
                     mainStage.setScene(scene);
                    mainStage.show();

                    
                  //  UserSession.setInstace(p);
                    //Personne u1=  UserSession.getInstace().getUtilisateur();
                    /*FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/acceuilResponsable.fxml"));
                   Parent root = loader.load();
                  AcceuilResponsableController ap= loader.getController();
                    btnlogin.getScene().setRoot(root);
        ///////////////////////////////////////////////
                    
                }
                 else  if(rs.getString("role").equals("fan")){
                    
                    JOptionPane.showMessageDialog(null, "Connection avec succées : email et mdp correcte ");
                 
                  Personne u=new Personne(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getDate("datenaissance"),rs.getString("adresse"),rs.getString("mail"),
                  rs.getInt("tel"),rs.getString("role"),rs.getString("mdp"),rs.getString("nomEquipe"),rs.getString("etat")
                  
                  );
                  
                  
                   UserSession.setInstace(u);
                    Personne u1=  UserSession.getInstace().getUtilisateur();
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("gui/acceuilFan.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage mainStage = new Stage();

                    AcceuilFanController detailPatientC = loader.getController();

                   // detailPatientC.initData(u);
                     mainStage.setScene(scene);
                    mainStage.show();

                  //  UserSession.setInstace(p);
                    //Personne u1=  UserSession.getInstace().getUtilisateur();
                    /*FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/acceuilResponsable.fxml"));
                   Parent root = loader.load();
                  AcceuilResponsableController ap= loader.getController();
                    btnlogin.getScene().setRoot(root);
///////////////////////////////////////
                    
                }

            }else{
                JOptionPane.showMessageDialog(null, "Connection échouée : email et mdp incorrecte ");
            }
              
            
        }catch (Exception ex) {
             //JOptionPane.showMessageDialog(null, e);
              Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } */
    
    
    
    
    // code jdiiiiiiid !!!!!!!!!!!!!!!!!!!
    
    
     String mdp =txtmdp.getText();
       
        Connection cnx =Myconnexion.getInstance().getCnx();
       MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(mdp.getBytes());

       byte byteData[] = md.digest();

        //convertir le tableau de bits en une format hexadécimal - méthode 1
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
           sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        String mdpCrypte = sb.toString();
       
        String requete ="Select * from personne where mail = ? and mdp = ? ";
        try{
            pst = cnx.prepareStatement(requete);
            pst.setString(1, txtmail.getText());
            pst.setString(2, txtmdp.getText());
           System.out.println("mot de passe crypte"+mdpCrypte);
            rs = pst.executeQuery();
           
             if(rs.next()){
    
                  Userconnected.setId(rs.getInt("id"));
                  Userconnected.setNom(rs.getString("nom"));
                  Userconnected.setPrenom( rs.getString("prenom"));              
                  Userconnected.setDatenaissance(rs.getDate("datenaissance"));                       
                  Userconnected.setAdresse( rs.getString("adresse"));                               
                  Userconnected.setMail(rs.getString("mail"));                                     
                  Userconnected.setTel(rs.getInt("tel"));
                  Userconnected.setRole(rs.getString("role"));
                  Userconnected.setMdp(rs.getString("mdp"));
                  Userconnected.setNomEquipe(rs.getString("nomEquipe"));
                  Userconnected.setEtat(rs.getString("etat"));
             
                if(rs.getString("role").equals("admin")){
                    
                    JOptionPane.showMessageDialog(null, "Connection avec succées : email et mdp correcte ");
                  
                    FXMLLoader loader= new FXMLLoader(getClass().getResource("Acceuil.fxml"));
                   Parent root = loader.load();
                  A ap= loader.getController();
                    btnlogin.getScene().setRoot(root);
                }
                
                 else  if(rs.getString("role").equals("respo")){
                    
                    JOptionPane.showMessageDialog(null, "Connection avec succées : email et mdp correcte ");
                 
                 
                  
                  
                  
                   
                    FXMLLoader loader= new FXMLLoader(getClass().getResource("acceuilResponsable.fxml"));
                   Parent root = loader.load();
                 AcceuilResponsableController ap= loader.getController();
                    btnlogin.getScene().setRoot(root);
                 }
                
                  else  if(rs.getString("role").equals("fan")){
                    
                    JOptionPane.showMessageDialog(null, "Connection avec succées : email et mdp correcte ");
                 
                 
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("gui/acceuilFan.fxml"));
                       Parent root = loader.load();
                 AcceuilFanController ap= loader.getController();
                    btnlogin.getScene().setRoot(root);
                   
                  }
    
    
    
     }else{
                JOptionPane.showMessageDialog(null, "Connection échouée : email et mdp incorrecte ");
            }
              
            
        }catch (Exception ex) {
             //JOptionPane.showMessageDialog(null, e);
              Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } 
   

     }

    @FXML
    private void inscrire(ActionEvent event) throws IOException {
        
         FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/newUser.fxml"));
                   Parent root = loader.load();
                 NewUserController ap= loader.getController();
                    btninscrire.getScene().setRoot(root);
    }

    @FXML
    private void oublier(ActionEvent event) throws IOException {
    
      FXMLLoader loader= new FXMLLoader(getClass().getResource("/gui/motdepasseoublie.fxml"));
                   Parent root = loader.load();
                  MotdepasseoublieController ap= loader.getController();
                    btnmdp.getScene().setRoot(root);
    
    }

        
        
    }
    

