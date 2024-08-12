/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subscription;
import java.util.*; 
import javax.mail.*; 
import javax.mail.internet.*; 
import javax.mail.Session; 
import javax.mail.Transport; 
/**
 *
 * @author li162
 */
public class Emailer {
    public Emailer(){
    
    }
    public void send(String reciever, String Sender){
      // email ID of Recipient
      String recipient = reciever; 
  
      // email ID of  Sender. 
      String sender = Sender; 
  
      // using host as localhost 
      String host = "127.0.0.1"; 
  
      // Getting system properties 
      Properties properties = System.getProperties(); 
  
      // Setting up mail server 
      properties.setProperty("mail.smtp.host", host); 
  
      // creating session object to get properties 
      Session session = Session.getDefaultInstance(properties); 
  
      try 
      { 
         // MimeMessage object. 
         MimeMessage message = new MimeMessage(session); 
  
         // Set From Field: adding senders email to from field. 
         message.setFrom(new InternetAddress(sender)); 
  
         // Set To Field: adding recipient's email to from field. 
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); 
  
         // Set Subject: subject of the email 
         message.setSubject("Surplus Food Alert"); 
  
         // set body of the email. 
         message.setText("A store you're subscribed to has surplus food!"); 
  
         // Send email. 
         Transport.send(message);
      } 
      catch (MessagingException mex)  
      { 
         mex.printStackTrace(); 
      } 
    }
}
