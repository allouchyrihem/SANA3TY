package edu.esprit.controller;
import static edu.esprit.controller.Session.getUserData;
import static edu.esprit.controller.Session.setUserData;
import edu.esprit.entity.Product;
import edu.esprit.dao.ProductDao;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class SessionPanier extends Application {

    // Déclaration de la liste des produits
    ObservableList<Product> produits = FXCollections.observableArrayList();

    // Déclaration du panier
    ObservableList<Product> panier = FXCollections.observableArrayList();
    @FXML
    private HBox hbox;
    
    @Override
    public void start(Stage primaryStage) {
        // Initialisation de la liste des produits depuis la base de données
        produits.addAll(ProductDao.getInstance().displayAll());
        
        // Création de la vue pour afficher les produits
        ListView<Product> produitsView = new ListView<>(produits);
        
        // Création de la vue pour afficher le panier
        ListView<Product> panierView = new ListView<>(panier);
        
        // Ajout d'un bouton pour ajouter un produit au panier
        Button ajouterAuPanierBtn = new Button("Ajouter au panier");
        ajouterAuPanierBtn.setOnAction(event -> {
            Product produitSelectionne = produitsView.getSelectionModel().getSelectedItem();
            if (produitSelectionne != null) {
                panier.add(produitSelectionne);
            }
        });
        
        // Ajout des vues et des boutons à une boîte horizontale
        HBox hbox = new HBox(10, produitsView, panierView, ajouterAuPanierBtn);
        
        // Affichage de la scène
        Scene scene = new Scene(hbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
