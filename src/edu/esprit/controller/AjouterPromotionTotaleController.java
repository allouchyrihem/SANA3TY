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

/**
 * FXML Controller class
 *
 * @author rouao
 */
public class AjouterPromotionTotaleController implements Initializable {

    @FXML
    private TextField nameid;
    @FXML
    private TextField valueid;
   @FXML
    private DatePicker datefid;
    
    @FXML
    private DatePicker datedid;
    @FXML
    private Button addeventbuttonid;
    @FXML
    private Button retourmainbuttonid;
   private PromotionTotale promos;
    private PromotionTotaleDao promoDao;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        promoDao = new PromotionTotaleDao();
}
       

    @FXML
    private void Addeventbtn(ActionEvent event) throws IOException {

        String name = nameid.getText();
        float value = Float.parseFloat(valueid.getText());
        LocalDate dated = datedid.getValue();
        LocalDate datef = datefid.getValue();
       
         promos = new PromotionTotale(name, value, dated,datef);
        promoDao.insert(promos);
        // TODO     
       /* addeventbuttonid.setOnAction((ActionEvent event) -> {
            //LocalDate datee = dateid.getValue();
            Events p = new Events(nameid.getText(), adresseid.getText(),linkid.getText(),descriptionid.getText(),dateid.getValue() );
            EventsDao pdao = EventsDao.getInstance();
            pdao.insert(p); */
       
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText("Promotion ajoutée avec succés!");
        alert.show();
        nameid.setText("");
       
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/view/NavbarBackoffice.fxml"));
        Parent root = loader.load();
          Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
       
        }

    @FXML
    private void retourmainbtn(ActionEvent event) {
    }
    
}
