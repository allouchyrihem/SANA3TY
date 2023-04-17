/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.dao.ProductDao;
import edu.esprit.entity.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class SessionPanierController implements Initializable {
    // Déclaration de la liste des produits
    ObservableList<Product> produits = FXCollections.observableArrayList();
    
    @FXML
    private ListView<Product> produitsView;
    
    // Déclaration du panier
    ObservableList<Product> panier = FXCollections.observableArrayList();
    
    @FXML
    private ListView<Product> panierView;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        produits.addAll(ProductDao.getInstance().displayAll());
        
        // Ajout des produits à la vue
        produitsView.setItems(produits);
    }
    
    @FXML
    private void ajouterAuPanier() {
        Product produitSelectionne = produitsView.getSelectionModel().getSelectedItem();
        if (produitSelectionne != null) {
            panier.add(produitSelectionne);
            panierView.setItems(panier);
        }
    }
    
    @FXML
    private void acheter(ActionEvent event) throws IOException {
        // TODO: implémenter l'action pour acheter les produits du panier
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/Panier.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
