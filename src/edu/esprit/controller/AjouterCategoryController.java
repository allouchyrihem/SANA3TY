/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.dao.CategoryDao;
import edu.esprit.dao.ProductDao;
import edu.esprit.entity.Category;
import edu.esprit.entity.Product;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjouterCategoryController implements Initializable {
  @FXML
    private Button btnC;
    @FXML
    private Button back;
    @FXML
    private Pane BoutonValider;
    @FXML
    private TextField name;
    @FXML
    private TextField description;
    @FXML
    private Button listc;
    @FXML
    private Hyperlink dashboard;
    @FXML
    private Hyperlink product;
    @FXML
    private Hyperlink category;
    @FXML
    private Hyperlink accueil;
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
       back.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/AfficherCategory.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       listc.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/AfficherCategory.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         btnC.setOnAction((ActionEvent event) -> {
            if (name.getText().isEmpty()) {
                showAlert("entrer une nom",false);
            } else if (!name.getText().matches("[a-zA-Z ]+")) {
                showAlert("le nom de la catégorie doit contenir que des lettres ou espace",false);
            }
            else if (description.getText().isEmpty()) {
                showAlert("entrer une description",false);
            } else if (!description.getText().matches("[a-zA-Z ]+")) {
                showAlert("la description doit contenir que des lettres ou espace",false);
            }
            else{
                Category p = new Category(name.getText(), description.getText());
                CategoryDao cdao = CategoryDao.getInstance();
                cdao.insert(p);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/view/Accueil1.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                showAlert("Insertion terminée", true);
                name.getScene().setRoot(root);
            
        
        name.setText("");
        description.setText("");

        }});
        
        
    

    }  
    
      private void showAlert(String message ,boolean b) {
        Alert alert;
        if (b)
            alert = new Alert(Alert.AlertType.INFORMATION);
        else
            alert = new Alert(Alert.AlertType.ERROR);


        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }
    
}
