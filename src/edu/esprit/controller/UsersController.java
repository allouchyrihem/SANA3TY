/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;


import edu.esprit.dao.UserDao;
import edu.esprit.entity.User;
import edu.esprit.test.ConnexionBD;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
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
public class UsersController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<User> usersList;

    @FXML
    private TableColumn<User, String> nameCol;

    @FXML
    private TableColumn<User, String> emailCol;

    @FXML
    private TableColumn<User, String> roleCol;

    @FXML
    private TableColumn<User, String> statusCol;

    @FXML
    private TableColumn<User, ImageView> pictureCol;

    @FXML
    private Button validateBtn;

    @FXML
    private Button rejectBtn;


    @FXML
    private Button detailsBtn;


     @FXML
    private Hyperlink profileBtn;

       @FXML
    private Hyperlink decBtn;
    
    @FXML
    private TextField searchP;
    

    private ListData listdata = new ListData();
    @FXML
    private ImageView imageView;
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usersList.setItems(listdata.getPersons());
        nameCol.setCellValueFactory(cell -> cell.
                getValue().getNameProperty());
        emailCol.setCellValueFactory(cell -> cell.
                getValue().getEmailProperty());
        statusCol.setCellValueFactory(cell -> cell.
                getValue().getStatusProperty2());
        roleCol.setCellValueFactory(cell -> cell.
                getValue().getRoleProperty());
        pictureCol.setCellValueFactory(new PropertyValueFactory<>("picture"));
        ConnexionBD app = new ConnexionBD();
        User connectedUser = app.getConnectedUser();
        
        profileBtn.setText(connectedUser.getName());
        detailsBtn.setOnAction(event -> {
            try {
                Parent page2 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/ShowUser.fxml"));
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ShowUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        profileBtn.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/Profile.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        decBtn.setOnAction(event -> {
            try {
                app.signOut();
            } catch (IOException ex) {
                Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        
       searchP.textProperty().addListener((observable, oldValue, newValue) -> {
    // Create filter Predicate
    Predicate<User> filter = item -> {
        // Implement filter logic here
        String query = newValue.toLowerCase();
        boolean nameMatch = item.getName().toLowerCase().contains(query);
        boolean emailMatch = item.getEmail().toLowerCase().contains(query);
        boolean roleMatch = item.getRoles().stream().anyMatch(role -> role.toLowerCase().contains(query));
        boolean statusMatch = item.getStatusProperty2().get().toLowerCase().contains(query); // Using getStatusProperty2() function to get the status as a string
        return nameMatch || emailMatch || roleMatch || statusMatch;
    };

    // Apply filter to data and update TableView
    usersList.setItems(listdata.getPersons().filtered(filter));
});   
        
    }

    @FXML
    public void validateUser() {
        try{
           
           User selectedUser = usersList.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            UserDao u = UserDao.getInstance();
            u.validateUSer(selectedUser);
            String obj = " Inscription à Sana3ty";
            String core = "Votre inscription à Sana3ty a ete acceptée";
            this.EnvoyerMail(selectedUser.getEmail(),obj,core);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Inscription validé avec succés!");
            alert.show();
        }
        }catch( Exception e){
            System.out.println("hhhhhhhhhh");
        }
        
      
    }

    @FXML
    public void refuseUser() {
        User selectedUser = usersList.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            String obj = " Inscription à Sana3ty";
            String core = "Votre inscription à Sana3ty n'a pas ete acceptée";
            try{this.EnvoyerMail(selectedUser.getEmail(),obj,core);}catch(Exception e ){}
            UserDao u = UserDao.getInstance();
            u.refuseUser(selectedUser);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Inscription validé avec succés!");
            alert.show();
        }

    }

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
