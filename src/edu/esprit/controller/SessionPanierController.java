/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.dao.CmdDao;
import edu.esprit.dao.ProductDao;
import edu.esprit.entity.Commande;
import edu.esprit.entity.Product;
import edu.esprit.entity.ProductCmd;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class SessionPanierController implements Initializable {
    private ShoppingCart cart = new ShoppingCart();

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
private void acheter(ActionEvent event) {
    // Vérifier si le panier n'est pas vide
    if (panier.isEmpty()) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Aucun produit dans le panier");
        alert.setContentText("Le panier est vide, veuillez ajouter des produits avant de continuer");
        alert.showAndWait();
        return;
    }
    
    // Demander l'adresse de livraison au client
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Adresse de livraison");
    dialog.setHeaderText(null);
    dialog.setContentText("Entrez votre adresse de livraison:");
    Optional<String> result = dialog.showAndWait();
    if (!result.isPresent() || result.get().isEmpty()) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Adresse de livraison non valide");
        alert.setContentText("Veuillez entrer une adresse de livraison valide avant de continuer");
        alert.showAndWait();
        return;
    }
    String adresseLivraison = result.get();
    
    // Créer la nouvelle commande
    Commande nouvelleCommande = new Commande();
    nouvelleCommande.setAdresse(adresseLivraison);
    nouvelleCommande.setCommandeProducts(FXCollections.observableList(new ArrayList<ProductCmd>()));
 
    // Ajouter les produits du panier à la nouvelle commande
    for (Product produit : panier) {
        ProductCmd commandeProduit = new ProductCmd(produit,nouvelleCommande);
        commandeProduit.setProduct(produit);
        commandeProduit.setCommande(nouvelleCommande);
        nouvelleCommande.getCommandeProducts().add(commandeProduit);
        
    }
    
    // Enregistrer la nouvelle commande dans la base de données
    CmdDao commandeDAO = new CmdDao();
    commandeDAO.insert(nouvelleCommande);
    
    // Effacer le panier et afficher un message de confirmation
    panier.clear();
    panierView.setItems(panier);
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Commande enregistrée");
    alert.setContentText("Votre commande a été enregistrée avec succès");
    alert.showAndWait();
}

}
