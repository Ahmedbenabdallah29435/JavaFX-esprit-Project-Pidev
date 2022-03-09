/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.HttpURLConnection;
import java.net.*;
import java.util.Base64;
import java.io.*;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SMSController implements Initializable {

    @FXML
    private TextField number;
    @FXML
    private TextArea msg;
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 public void sms(String username, String password, String to,String message){
            try{
        
        String myURI = "https://api.bulksms.com/v1/messages";

    // change these values to match your own account
    // new compte pour envoyer sms ***********************************************************
    String myUsername = "";
    String myPassword = "";

    // the details of the message we want to send
    String myData = "{to: \""+to+"\", encoding: \"UNICODE\", body: \""+message+"\"}";

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
    }}
        /*public void sms(String username, String password, String to,String message){
               try {
            // Construct data
            String data = "";
            /*
             * Note the suggested encoding for certain parameters, notably
             * the username, password and especially the message.  ISO-8859-1
             * is essentially the character set that we use for message bodies,
             * with a few exceptions for e.g. Greek characters.  For a full list,
             * see:  https://www.bulksms.com/developer/eapi/submission/character-encoding/
             */
            //eya:https://bulksms.vsms.net/eapi//submission/send_sms/2/2.0
         /*   data += "username=" + URLEncoder.encode(username, ""+username+"");
            data += "&password=" + URLEncoder.encode(password, ""+password+"");
            data += "&message=" + URLEncoder.encode(message, ""+message+"");
            data += "&want_report=1";
            data += "&msisdn="+ URLEncoder.encode(message, ""+to+"");; //noumrou elli bech tabaathlou
                    String myURI = "https://bulksms.vsms.net/eapi//submission/send_sms/2/2.0";

            // Send data
            // Please see the FAQ regarding HTTPS (port 443) and HTTP (port 80/5567)
            URL url = new URL(myURI);

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                // Print the response output...
                System.out.println(line);
            }
            wr.close();
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
        
        
        
        
        
    
            
















    @FXML
    private void send(ActionEvent event) {
        SMSController smsc= new SMSController();
        smsc.sms("ahmedba123", "Azerty123456", number.getText(), msg.getText());
    }
       
    
}


