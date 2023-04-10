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

/**
 * FXML Controller class
 *
 * @author rouao
 */
public class UpdateEventsController{

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
    @FXML
    private Button updateeventbuttonid;
    private Stage dialogStage;
    private EventsDao eventsDao;
    private Events events;


    /**
     * Initializes the controller class.
     */

    
     @FXML
    public void initialize() {
        eventsDao = new EventsDao();  //To change body of generated methods, choose Tools | Templates.
    }
    
public void setDialogueStage(Stage dialogStage){
    this.dialogStage = dialogStage;
}    
public void setEvent(Events events){
    this.events = events;
    nameid.setText(events.getName());
    adresseid.setText(events.getAdresse());
    dateid.setValue(events.getDate());
    descriptionid.setText(events.getDescription());
    linkid.setText(events.getLink());


}

    @FXML
    private void Updateeventbtn() {
         if (isInputValid()) {
        String name = nameid.getText();
        String adresse = adresseid.getText();
        LocalDate date = dateid.getValue();
        String description = descriptionid.getText();
        String link = linkid.getText();
        
        Events event1 = new Events(events.getId(),name, adresse, date, description, link);
        eventsDao.update(event1);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");

            alert.setHeaderText("Événement modifié avec succès!");
         

        
        alert.showAndWait();
        
    }}

   
    
     private boolean isInputValid() {
        String errorMessage = "";
         if (nameid.getText() == null || nameid.getText().isEmpty()) {
            errorMessage += "Le nom de l'événement est requis.\n";
        } 
         if (nameid.getText().length() < 2 || nameid.getText().length() > 20) {
    errorMessage += "La longueur du nom doit être entre 2 et 20 caractères.\n";
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


}
