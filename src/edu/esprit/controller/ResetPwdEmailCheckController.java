/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;


import edu.esprit.dao.UserDao;
import edu.esprit.test.ConnexionBD;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
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
 * FXML Controller class
 *
 * @author Fattouma PC
 */
public class ResetPwdEmailCheckController implements Initializable {

    @FXML
    private TextField emailField;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField search;

    @FXML
    private Button buttonSearch;

    @FXML
    private Hyperlink acceuil;

    @FXML
    private Hyperlink boutique;

    @FXML
    private Hyperlink details;

    @FXML
    private Hyperlink reclamation;

    @FXML
    private Hyperlink seConnecter;

    @FXML
    private Hyperlink sinscrire;

    @FXML
    private Hyperlink evenement;

    @FXML
    private Button resetPwdBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sinscrire.setOnAction(e -> {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/SignUp.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
});
        seConnecter.setOnAction(e -> {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/Login.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
});
    
    evenement.setOnAction(e -> {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/AfficherEventClient.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
});
    acceuil.setOnAction(e -> {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/Accueil1.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
});
    boutique.setOnAction(e -> {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/Boutique.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
});
    
     reclamation.setOnAction(e -> {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/AjouterReclamation.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
});
     
        resetPwdBtn.setOnAction(event -> {
            if (!isValidEmailFormat(emailField.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("L'email fournit n'est pas valide.");
                alert.show();
            } else {
                if (!verifMailUser(emailField.getText())) {
                    try {
                        codeSending();
                        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/ResetPwdCodeCheck.fxml"));
                        Scene scene = new Scene(page1);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                      
                        System.out.println("bibi");
                       
                    } catch (IOException ex) {
                        Logger.getLogger(SignUpClientController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Aucun compte n'est associé à ce mail");
                    alert.show();
                }
            }

        });

    }

    public static boolean isValidEmailFormat(String email) {
        // Regular expression for email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pattern.matcher(email).matches();
    }

    public static boolean verifMailUser(String email) {
        UserDao pdao = UserDao.getInstance();
        if (pdao.findUserByEmail(email) == null) {
            return true;
        }
        return false;
    }

    public static String generateRandomNumber() {
        Random rand = new Random();
        int num = rand.nextInt(900000) + 100000; // generates a random number between 100000 and 999999
        return String.valueOf(num);
    }

    public void codeSending() {
        UserDao pdao = UserDao.getInstance();
        ConnexionBD app = new ConnexionBD();
        app.setEmail(emailField.getText());
        String code = generateRandomNumber();
        pdao.updateCode(emailField.getText(), code);
        try {
            envoyerMail(emailField.getText(), code);
        } catch (Exception e) {
        }

    }

    @FXML
    void envoyerMail(String emailDestination, String code) throws IOException {

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
            message.setSubject("Reset mot de passe");

            Address addressTo = new InternetAddress(emailDestination);
            message.setRecipient(Message.RecipientType.TO, addressTo);

            MimeMultipart mutipart = new MimeMultipart();
            MimeBodyPart attachment = new MimeBodyPart();

            //attachment.attachFile(new File(""));
            MimeBodyPart attachment2 = new MimeBodyPart();

            MimeBodyPart m = new MimeBodyPart();
            m.setContent("<h1> \n"
                    + " Bonjour : " + " </h1>"
                    + "<h3> Nous avons reçu une demande de De reintailiser votre mdp à Sana3ty .\n" + "Votre code est " + code
                    + "\n " + "</h3>", "text/html");
            mutipart.addBodyPart(m);

            message.setContent(mutipart);
            Transport.send(message);

        } catch (MessagingException ex) {

        }

    }

}
