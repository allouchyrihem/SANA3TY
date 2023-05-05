/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.util;


import java.io.IOException;
import java.util.Properties;
import javafx.fxml.FXML;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author khiar
 */
public class Gmailer {
    @FXML
    void EnvoyerMail(String emailDestination , String obj , String core) throws IOException {

        try {
            Properties p = new Properties();
            p.put("mail.smtp.auth", true);
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.port", 587);
            p.put("mail.smtp.starttls.enable", true);
            p.put("mail.transport.protocl", "smtp");

            Session session = Session.getInstance(p, new Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("sana3ty@gmail.com", "qdxerugmjpbamfhl");
                }

            });

            Message message = new MimeMessage(session);
            message.setSubject(obj);

            Address addressTo = new InternetAddress(emailDestination);
            message.setRecipient(Message.RecipientType.TO, addressTo);

            MimeMultipart mutipart = new MimeMultipart();
            MimeBodyPart attachment = new MimeBodyPart();

            //attachment.attachFile(new File(""));
            MimeBodyPart attachment2 = new MimeBodyPart();

            MimeBodyPart m = new MimeBodyPart();
                m.setContent("<h1> \n"
                        + " Bonjour : "  +" </h1>"
                        + "<h3> Nous avons reçu une demande de D'inscription à Sana3ty.\n"
                        + core+"\n " +  "</h3>", "text/html");
                mutipart.addBodyPart(m);
              
                message.setContent(mutipart);
                Transport.send(message);
              
              

            
        } catch (MessagingException ex) {
            
        }

    }
    
}