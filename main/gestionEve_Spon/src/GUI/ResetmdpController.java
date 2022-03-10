/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import utils.Myconnexion;

/**
 * FXML Controller class ahmed
 *
 */
public class ResetmdpController implements Initializable {

    @FXML
    private TextField nvmdp;
    @FXML
    private TextField confirmMdp;
    @FXML
    private Button btnreset;
public String user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

      public void initData(String email){
        this.user=email;
    }
    
    @FXML
    private void reset(ActionEvent event) {
    
              Connection cnx =Myconnexion.getInstance().getCnx();
        if(nvmdp.getText().equals(confirmMdp.getText())){
            try{
                
                System.out.println(confirmMdp.getText());
                System.out.println(this.user);
                String test=confirmMdp.getText();
                
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(test.getBytes());


                byte byteData[] = md.digest();
                //convertir le tableau de bits en une format hexadécimal - méthode 1
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < byteData.length; i++) {
                    sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
                }

                String mdpCrypte = sb.toString();
                System.out.println(mdpCrypte);
                
                
                String req = "update personne set mdp=? where mail= ?";
                PreparedStatement st = cnx.prepareStatement(req);
                st.setString(1, mdpCrypte);
                st.setString(2, this.user);
                st.executeUpdate();

                JOptionPane.showMessageDialog(null, "reset succes");
                 FXMLLoader loader= new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
       LoginController ap= loader.getController();
        btnreset.getScene().setRoot(root);
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
                Logger.getLogger(ResetmdpController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }else{
            JOptionPane.showMessageDialog(null, "verifier mdp");

        }
        
    
    
    }
    
}
