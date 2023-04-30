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
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
public class PanierController implements Initializable {

@FXML
    private ImageView imageView;

    @FXML
    private TextField search;

    @FXML
    private Button buttonSearch;

    @FXML
    private Text Acceuil;

    @FXML
    private Text Boutique;

    @FXML
    private Text Details;

    @FXML
    private Text Contact;

    @FXML
    private Text Réclamation;

    @FXML
    private Text Seconnecter;

    @FXML
    private Text Sinscrire;

    private TableView<Product> cartTable;

    private TableColumn<Product, String> nameColumn;

    private TableColumn<Product, Double> priceColumn;

    private ObservableList<Product> panier = FXCollections.observableArrayList();

    private ObservableList<Product> produits = FXCollections.observableArrayList();

    @FXML
     ListView<Product> panierView;

    @FXML
    private Pane BoutonValiderC;

    private ShoppingCart cart;

    private Product selectedProduct;

    @FXML
    private Button acheterbtn;
    @FXML
    private TableColumn<Product, String> nameCol;
    @FXML
    private TableColumn<?, ?> descriptionCol;
    @FXML
    private TableColumn<Product, Double> priceCol;

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }/*
public void setProducts(List<Product> products) {
    for(Product product : products) {
        // Ajoutez le produit à la table des produits
    }
}*/

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
        nameColumn.setText(selectedProduct.getName());
        priceColumn.setText(String.valueOf(selectedProduct.getPrice()));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        produits.addAll(ProductDao.getInstance().displayAll());

        // Ajout des produits à la vue
        // produitsView.setItems(produits);
    }

    private void addToCart(ActionEvent event) {
        if (selectedProduct != null) {
            panier.add(selectedProduct);
            panierView.setItems(panier);
            cart.addProduct(selectedProduct);
        }
    }
@FXML
 TableView<Product> productTable;

public void setProducts(List<Product> products) {
    productTable.setItems(FXCollections.observableArrayList(products));
}
public void setProduits(List<Product> produits) {
    productTable.getItems().addAll(produits);
    nameCol.setCellValueFactory(new PropertyValueFactory<>("etat"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("totale"));
}
    @FXML
    private void acheter(ActionEvent event) throws SQLException {
        // Vérifier si le panier n'est pas vide
        if (panier.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Adresse de livraison non valide");
            alert.setContentText("Veuillez entrer une adresse de livraison valide avant de continuer");
            alert.showAndWait();
            return;
        }
        String adresseLivraison= result.get();

        //String qunatiteCmd = result.get();
TextInputDialog quantiteDialog = new TextInputDialog();
quantiteDialog.setTitle("Quantité");
quantiteDialog.setHeaderText(null);
quantiteDialog.setContentText("Entrez la quantité souhaitée:");
Optional<String> quantiteResult = quantiteDialog.showAndWait();
if (!quantiteResult.isPresent() || quantiteResult.get().isEmpty()) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
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
        for (Product produit : panier) {
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
         // Get the last inserted commande id
    int lastInsertedId = commandeDAO.getLastInsertedId(); // Retrieve the last inserted ID
for (Product produit : panier) {
    commandeDAO.insertP(lastInsertedId, produit.getId());
}
         panier.clear();
    panierView.setItems(panier);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Commande enregistrée");
    alert.setContentText("Votre commande a été enregistrée avec succès");
    alert.showAndWait();
    
    
}

}
/*
    @FXML
    private ImageView imageView;

    @FXML
    private TextField search;

    @FXML
    private Button buttonSearch;

    @FXML
    private Text Acceuil;

    @FXML
    private Text Boutique;

    @FXML
    private Text Details;

    @FXML
    private Text Contact;

    @FXML
    private Text Réclamation;

    @FXML
    private Text Seconnecter;

    @FXML
    private Text Sinscrire;

    private TableView<Product> cartTable;

    private TableColumn<Product, String> nameColumn;

    private TableColumn<Product, Double> priceColumn;
 ObservableList<Product> panier = FXCollections.observableArrayList();
 ObservableList<Product> produits = FXCollections.observableArrayList();

    @FXML
     ListView <Product> panierView;
    @FXML
    private Pane BoutonValiderC;

    private ShoppingCart cart;

    private Product selectedProduct;
  
    @FXML
    private Button acheterbtn;

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
        nameColumn.setText(selectedProduct.getName());
        priceColumn.setText(String.valueOf(selectedProduct.getPrice()));
    }

    /**
     * Initializes the controller class.
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     //   nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
       // priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        //cartTable.setItems(cart.getProducts());
        produits.addAll(ProductDao.getInstance().displayAll());

        // Ajout des produits à la vue
       // produitsView.setItems(produits);
    }

     @FXML
    private void acheter(ActionEvent event) throws SQLException {
        // Vérifier si le panier n'est pas vide
        if (panier.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Adresse de livraison non valide");
            alert.setContentText("Veuillez entrer une adresse de livraison valide avant de continuer");
            alert.showAndWait();
            return;
        }
        String adresseLivraison = result.get();

        //String qunatiteCmd = result.get();
TextInputDialog quantiteDialog = new TextInputDialog();
quantiteDialog.setTitle("Quantité");
quantiteDialog.setHeaderText(null);
quantiteDialog.setContentText("Entrez la quantité souhaitée:");
Optional<String> quantiteResult = quantiteDialog.showAndWait();
if (!quantiteResult.isPresent() || quantiteResult.get().isEmpty()) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
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
        for (Product produit : panier) {
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
         // Get the last inserted commande id
    int lastInsertedId = commandeDAO.getLastInsertedId(); // Retrieve the last inserted ID
for (Product produit : panier) {
    commandeDAO.insertP(lastInsertedId, produit.getId());
}
         panier.clear();
    panierView.setItems(panier);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Commande enregistrée");
    alert.setContentText("Votre commande a été enregistrée avec succès");
    alert.showAndWait();
    
    
}}

/**
 * FXML Controller class
 *
 * @author HP
 */
/*public class PanierController implements Initializable {

    private TableView<Product> cartTable;

    private TableColumn<Product, String> nameColumn;

    private TableColumn<Product, Double> priceColumn;

    private ShoppingCart cart;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField search;
    @FXML
    private Button buttonSearch;
    @FXML
    private Text Acceuil;
    @FXML
    private Text Boutique;
    @FXML
    private Text Details;
    @FXML
    private Text Contact;
    @FXML
    private Text Réclamation;
    @FXML
    private Text Seconnecter;
    @FXML
    private Text Sinscrire;
    @FXML
    private Pane BoutonValiderC;

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }
      private Product selectedProduct;

    
    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
        nameColumn.setText(selectedProduct.getName());
       // priceColumn.setText(selectedProduct.getDescription());
        priceColumn.setText(String.valueOf(selectedProduct.getPrice()));
    }

    /**
     * Initializes the controller class.
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    
    cartTable.setItems(cart.getProducts());
    }    
}    */

