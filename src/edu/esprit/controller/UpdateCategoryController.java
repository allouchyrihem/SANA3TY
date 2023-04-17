/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.dao.CategoryDao;
import edu.esprit.entity.Category;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class UpdateCategoryController implements Initializable {

    @FXML
    private Pane BoutonValider;
    @FXML
    private Button valider;
    @FXML
    private TextField name;
    @FXML
    private TextField description;
    @FXML
    private Button back;
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
    CategoryDao cdao;
    private Stage anchorPane;
    private Category categories;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       cdao = new CategoryDao();
    }    
    
    public void setDialogueStage(Stage anchorPane){
    this.anchorPane = anchorPane;
}   
    
    public void setCategory(Category categories){
    this.categories = categories;
    name.setText(categories.getName());
    description.setText(categories.getDescription());
}
    
    @FXML
    public void valider() {
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
        String namee = name.getText();
        String descriptione = description.getText();        
        Category c = new Category(categories.getId(),namee, descriptione);
        cdao.update(c);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText("catégorie modifié avec succès!");
        alert.showAndWait();
        
    }}
    
   
     
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
