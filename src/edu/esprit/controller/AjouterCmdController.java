/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.entity.Product;
import edu.esprit.test.ConnexionBD;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author HP
 */
 public class AjouterCmdController implements Initializable {

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
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, String> nameCol;
    @FXML
    private TableColumn<Product, String> descriptionCol;
    @FXML
    private TableColumn<Product, Double> priceCol;
    private Product selectedProduct;
    private List<Product> cartProducts = new ArrayList<>();
private ShoppingCart cart = new ShoppingCart();

private List<Product> products;
    @FXML
    private Button back;
    @FXML
    private Button acheterButton;
    @FXML
    private HBox producttable;
public Product getSelectedProduct() {
    return productTable.getSelectionModel().getSelectedItem();
}


/*public void setSelectedProduct(Product product) {
        this.selectedProduct = product;
    }*/
public void setSelectedProduct(Product selectedProductt) {
    this.selectedProduct = selectedProductt;
}

    /**
     * Initializes the controller class.
     
    public void setProducts(List<Product> products) {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        ObservableList<Product> productList = FXCollections.observableArrayList(products);
        productTable.setItems(productList);
    }*/
  
public void setProduct(Product product) throws IOException {
    List<Product> productList = new ArrayList<>();
    productList.add(product);
    productTable.setItems(FXCollections.observableArrayList(productList));
    nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
    priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
}

   public void setCartProducts(List<Product> cartProducts) {
    this.cartProducts = cartProducts;
    ObservableList<Product> cartProductsObservable = FXCollections.observableArrayList(cartProducts);
    //AjouterCmd.setItems(cartProductsObservable);
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ConnexionBD connexion = new ConnexionBD();
    List<Product> products = connexion.contenuPanier();

    // Remplir la table avec les produits
    ObservableList<Product> productList = FXCollections.observableArrayList(products);
    productTable.setItems(productList);

    // Définir les valeurs des colonnes
    nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
    priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

      //   nameCol.setText(selectedProduct.getName());
    //descriptionCol.setText(selectedProduct.getDescription());
    //priceCol.setText(String.valueOf(selectedProduct.getPrice()));

    // Add the selected product to the cart
    //cart.addItem(selectedProduct);
        back.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/Boutique.fxml"));
                Scene scene
                        = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    
     acheterButton.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/Boutique.fxml"));
                Scene scene
                        = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }   
   
}
