/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.dao.CmdDao;
import edu.esprit.entity.Commande;
import edu.esprit.entity.Product;
import edu.esprit.entity.ProductCmd;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CartView implements Initializable {

    @FXML
    private TableView<Product> cartTable;
    @FXML
    private TableColumn<Product, String> nameCol;
    @FXML
    private TableColumn<Product, String> descriptionCol;
    @FXML
    private TableColumn<Product, Double> priceCol;
    @FXML
    private Button checkoutButton;

    private List<Product> cartProducts;

    public void setCartProducts(List<Product> cartProducts) {
        this.cartProducts = cartProducts;
        ObservableList<Product> cartProductsObservable = FXCollections.observableArrayList(cartProducts);
        cartTable.setItems(cartProductsObservable);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        checkoutButton.setOnAction(event -> {
            try {
                // TODO: implémenter le traitement de paiement
                if (cartProducts.isEmpty()) {
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
                
                TextInputDialog quantiteDialog = new TextInputDialog();
                quantiteDialog.setTitle("Quantité");
                quantiteDialog.setHeaderText(null);
                quantiteDialog.setContentText("Entrez la quantité souhaitée:");
                Optional<String> quantiteResult = quantiteDialog.showAndWait();
                if (!quantiteResult.isPresent() || quantiteResult.get().isEmpty()) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Quantité non valide");
                    alert.setContentText("Veuillez entrer une quantité valide avant de continuer");
                    alert.showAndWait();
                    return;
                }
                int quantite = Integer.parseInt(quantiteResult.get());
                
                // Créer la nouvelle commande
                Commande nouvelleCommande = new Commande();
                nouvelleCommande.setAdresse(adresseLivraison);
                nouvelleCommande.setQuantite(quantite);
                nouvelleCommande.setCommandeProducts(FXCollections.observableList(new ArrayList<ProductCmd>()));
                float totale=0.0f;
                
                // Ajouter les produits du panier à la nouvelle commande
                for (Product produit : cartProducts) {
                    ProductCmd commandeProduit = new ProductCmd(produit, nouvelleCommande);
                    commandeProduit.setProduct(produit);
                    commandeProduit.setCommande(nouvelleCommande);
                    nouvelleCommande.getCommandeProducts().add(commandeProduit);
                    float price=produit.getPrice();
                    totale+= price *quantite;
                }
                nouvelleCommande.setTotale(totale);
                
                // Enregistrer la nouvelle commande dans la base de données
                CmdDao commandeDAO = new CmdDao();
                commandeDAO.insert(nouvelleCommande);
                // Récupérer l'id de la dernière commande insérée
                int lastInsertedId = commandeDAO.getLastInsertedId();
                for (Product produit : cartProducts) {
                    commandeDAO.insertP(lastInsertedId, produit.getId());
                }
                cartProducts.clear();
                cartTable.setItems(FXCollections.observableArrayList(cartProducts));
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Commande enregistrée");
                alert.setContentText("Votre commande a été enregistrée avec succès");
                alert.showAndWait();
                System.out.println(nouvelleCommande);
            } catch (SQLException ex) {
                Logger.getLogger(CartView.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }


    public void backToBoutique(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/edu/esprit/view/Boutique.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
