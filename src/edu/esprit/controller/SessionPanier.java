package edu.esprit.controller;
import static edu.esprit.controller.Session.getUserData;
import static edu.esprit.controller.Session.setUserData;
import edu.esprit.entity.Product;
import edu.esprit.dao.ProductDao;
import java.net.URL;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class SessionPanier implements Initializable {

    // Déclaration de la liste des produits
    ObservableList<Product> produits = FXCollections.observableArrayList();

    // Déclaration du panier
    ObservableList<Product> panier = FXCollections.observableArrayList();
    @FXML
    private HBox hbox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
    
}
