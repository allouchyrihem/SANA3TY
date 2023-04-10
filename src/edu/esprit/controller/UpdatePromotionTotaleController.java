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
        
    }
    
}
