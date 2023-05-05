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
import edu.esprit.entity.User;
import edu.esprit.test.ConnexionBD;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
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
    @FXML
    private Label idname;
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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        promoDao = new PromotionTotaleDao();
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
});
}
       

    @FXML
    private void Addeventbtn(ActionEvent event) throws IOException {
if (isInputValid()) {
    
   ConnexionBD app = new ConnexionBD();
          User connectedUser = app.getConnectedUser(); 
        String name = nameid.getText();
        float value = Float.parseFloat(valueid.getText());
        LocalDate dated = datedid.getValue();
        LocalDate datef = datefid.getValue();
       
         promos = new PromotionTotale(name, value, dated,datef);
         promos.setUser(connectedUser);
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
       
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/view/AfficherPromotionTotale.fxml"));
        Parent root = loader.load();
          Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
       
        }}

    @FXML
    private void retourmainbtn(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/view/AfficherPromotionTotale.fxml"));
    Parent root = loader.load();

    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    
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