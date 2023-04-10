/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.dao.EventsDao;
import edu.esprit.dao.PromotionTotaleDao;
import edu.esprit.entity.Events;
import edu.esprit.entity.PromotionTotale;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rouao
 */
public class UpdatePromotionTotaleController implements Initializable {

    @FXML
    private TextField nameid;
    @FXML
    private TextField valueid;
    @FXML
    private DatePicker datefid;
    @FXML
    private Button UpdatePromotionTbtn;
    @FXML
    private DatePicker datedid;
    
     @FXML
    private Button updateeventbuttonid;
    private Stage dialogStage;
    private PromotionTotaleDao PromoDao;
    private PromotionTotale promos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PromoDao = new PromotionTotaleDao();  //To change body of generated methods, choose Tools | Templates.
    }    

    public void setDialogueStage(Stage dialogStage){
    this.dialogStage = dialogStage;
}    
    public void setPromo(PromotionTotale promos){
    this.promos = promos;
    nameid.setText(promos.getName());
valueid.setText(Float.toString(promos.getValue()));
    datedid.setValue(promos.getDateDebut());
    datefid.setValue(promos.getDateFin());
 


}

    
    @FXML
    private void UpdatePromotionTbtn(ActionEvent event) {
        if (isInputValid()) {
        String name = nameid.getText();
        Float value = Float.parseFloat(valueid.getText()) ;
        
        LocalDate dated = datedid.getValue();
        LocalDate datef = datefid.getValue();

        
        PromotionTotale prom = new PromotionTotale(promos.getId(),name, value, dated, datef);
        PromoDao.update(prom);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");

            alert.setHeaderText("Événement modifié avec succès!");
         

        
        alert.showAndWait();
        
    }}
    
    private boolean isInputValid() {
        String errorMessage = "";
         if (nameid.getText() == null || nameid.getText().isEmpty()) {
            errorMessage += "Le nom du code promo est requis.\n";
        } 
         if (nameid.getText().length() < 2 || nameid.getText().length() > 20) {
    errorMessage += "La longueur du nom doit être entre 2 et 20 caractères.\n";
}
         if (valueid.getText() == null || valueid.getText().isEmpty()) {
    errorMessage += "La valeur du code promo est requise.\n";
} else {
    try {
        float value = Float.parseFloat(valueid.getText());
        if (value < 0 || value > 100) {
            errorMessage += "La valeur du code promo doit être un nombre entre 0 et 100.\n";
        }
    } catch (NumberFormatException e) {
        errorMessage += "La valeur du code promo doit être un nombre.\n";
    }
}
         
         if (datedid.getValue() == null || datedid.getValue().toString().isEmpty()) {
            errorMessage += "La date de l'activation est requis.\n";
         }
         if (datefid.getValue() == null || datefid.getValue().toString().isEmpty()) {
            errorMessage += "La date de désactivation est requis.\n";
         }
        
          LocalDate selectedDate = datedid.getValue();
if (selectedDate != null && selectedDate.isBefore(LocalDate.now())) {
    errorMessage += "La date sélectionnée ne peut pas être antérieure à la date du jour.\n";
}

if (datedid.getValue() != null && datefid.getValue() != null
                && datefid.getValue().isBefore(datedid.getValue())) {
            errorMessage += "La date de désactivation doit etre ultérieure à la date d'activation.\n";
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
