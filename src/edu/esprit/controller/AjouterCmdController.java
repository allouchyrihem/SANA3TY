/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.dao.CategoryDao;
import edu.esprit.dao.CmdDao;
import edu.esprit.dao.ProductDao;
import edu.esprit.entity.Category;
import edu.esprit.entity.Commande;
import edu.esprit.entity.Product;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
    
private List<Product> products;
    @FXML
    private Button back;
    @FXML
    private Button acheterButton;
public Product getSelectedProduct() {
    return productTable.getSelectionModel().getSelectedItem();
}
private Product selectedProduct;

public void setSelectedProduct(Product product) {
        this.selectedProduct = product;
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

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    }    
    
}
