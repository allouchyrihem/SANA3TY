/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.MouseEvent;


/**
 * FXML Controller class
 *
 * @author rouao
 */
public class NavbarBackofficeController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private Button eventNavBarid;
    @FXML
    private Button promotionNavBarid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //loadPage("/edu/esprit/view/AfficherEvents");
        // TODO
    }    

  
    
    @FXML
    private void eventNavBarbtn(ActionEvent event) {
        loadPage("/edu/esprit/view/AfficherEvents");
    }

    @FXML
    private void promotionNavBarbtn(ActionEvent event) {
        loadPage("/edu/esprit/view/AfficherPromotionTotale");
    }
    
    private void loadPage(String page){
    FXMLLoader loader = new FXMLLoader(getClass().getResource(page+".fxml"));
    try {
        Parent root = loader.load();
        bp.setCenter(root);
    } catch(IOException ex){
        Logger.getLogger(NavbarBackofficeController.class.getName()).log(Level.SEVERE, null, ex); 
    }
}

    
}
