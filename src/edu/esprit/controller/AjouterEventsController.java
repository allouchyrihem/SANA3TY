/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import edu.esprit.dao.EventsDao;
import edu.esprit.entity.Events;
import edu.esprit.entity.Product;
import edu.esprit.entity.User;
import edu.esprit.test.ConnexionBD;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Hyperlink;

/**
 * FXML Controller class
 *
 * @author rouao
 */
public class AjouterEventsController implements Initializable {
    private SmsService smsService = new SmsService();
    @FXML
    private TextField nameid;
    @FXML
    private TextField adresseid;
    @FXML
    private TextField linkid;
    @FXML
    private TextField descriptionid;
    @FXML
    private DatePicker dateid;
    private Events events;
    private EventsDao eventsDao;
    @FXML
    private Button retourmainbuttonid;
    @FXML
    private Button addeventbuttonid;
    @FXML
    private Hyperlink dashboard;
    @FXML
    private Hyperlink product;
    @FXML
    private Hyperlink category;
    @FXML
    private Hyperlink accueil;
    @FXML
    private Hyperlink reclamation;
    @FXML
    private Hyperlink evenement;
    @FXML
    private Hyperlink promotion;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        eventsDao = new EventsDao(); 
    accueil.setOnAction(e -> {
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
    dashboard.setOnAction(e -> {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/Dashboard.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
});
    product.setOnAction(e -> {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/AfficherProduct.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
});
    category.setOnAction(e -> {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/AfficherCategory.fxml"));
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
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/AfficherReclamation.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
});
    
     promotion.setOnAction(e -> {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/AfficherPromotionTotale.fxml"));
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
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/AfficherEvents.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
});}
    @FXML
    private void Addeventbtn(ActionEvent event) throws IOException {
        if (isInputValid()) {
         ConnexionBD app = new ConnexionBD();
          User connectedUser = app.getConnectedUser();
                User userr = new User(9,"fatma Creation","fatmajrad2000@gmail.com");
 
             
               
        String name = nameid.getText();
        String adresse = adresseid.getText();
        LocalDate date = dateid.getValue();
        String description = descriptionid.getText();
        String link = linkid.getText();
       
        events = new Events(name, adresse, date,description ,link);
        events.setUser(connectedUser);
        eventsDao.insert(events);
        // TODO     
       /* addeventbuttonid.setOnAction((ActionEvent event) -> {
            //LocalDate datee = dateid.getValue();
            Events p = new Events(nameid.getText(), adresseid.getText(),linkid.getText(),descriptionid.getText(),dateid.getValue() );
            EventsDao pdao = EventsDao.getInstance();
            pdao.insert(p); */
       
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText("Événement ajouté avec succés!");
        alert.show();
        String message = "A new event has been added: " + name;
        smsService.sendSms("+21656437457", message);
        nameid.setText("");
        adresseid.setText("");
        linkid.setText("");
        descriptionid.setText("");
        dateid.setValue(null);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/view/AfficherEvents.fxml"));
        Parent root = loader.load();
          Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
       
        };
        // TODO

    }
     private boolean isInputValid() {
        String errorMessage = "";
         if (nameid.getText() == null || nameid.getText().isEmpty()) {
            errorMessage += "Le nom de l'événement est requis.\n";
        } 
         if (nameid.getText().length() < 2 || nameid.getText().length() > 20) {
    errorMessage += "La longueur du nom doit être entre 2 et 50 caractères.\n";
}
         if (adresseid.getText() == null || adresseid.getText().isEmpty()) {
            errorMessage += "L'adresse de l'événement est requis.\n";
        }
         if (descriptionid.getText() == null || descriptionid.getText().isEmpty()) {
            errorMessage += "La description de l'événement est requis.\n";
        }
         if (linkid.getText() == null || linkid.getText().isEmpty()) {
            errorMessage += "Le lien de l'événement est requis.\n";
        }
         if (dateid.getValue() == null || dateid.getValue().toString().isEmpty()) {
            errorMessage += "La date de l'événement est requis.\n";
         }
        
          LocalDate selectedDate = dateid.getValue();
if (selectedDate != null && selectedDate.isBefore(LocalDate.now())) {
    errorMessage += "La date sélectionnée ne peut pas être antérieure à la date du jour.\n";
}
         if (errorMessage.isEmpty()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Des champs invalides");
            alert.setHeaderText("Veuillez corriger les champs invalides");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
     }

    
     
     @FXML
private void retourmainbtn(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/view/NavbarBackoffice.fxml"));
    Parent root = loader.load();

    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}

     
    
}