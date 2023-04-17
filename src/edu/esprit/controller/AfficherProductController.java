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
import static java.util.Optional.empty;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class AfficherProductController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, String> name;
    @FXML
    private TableColumn<Product, String> description;
    @FXML
    private TableColumn<Product, String> price;
    @FXML
    private TableColumn<Product, String> stock;
    @FXML
    private TableColumn<Product, ImageView> image;
    @FXML
    private TableColumn<Product, String> categoryname;
    @FXML
    private Button back;
  
    private ListData listdata = new ListData();
    @FXML
    private Hyperlink dashboard;
    @FXML
    private Hyperlink product;
    
    @FXML
    private Button supprimer;
    @FXML
    private Button updateU;
    @FXML
    private Hyperlink accueil;
    private ProductDao pdao= new ProductDao() ;
    private CategoryDao cdao= new CategoryDao() ;
    private ObservableList<Product> productdata = FXCollections.observableArrayList();
        Product p;
    @FXML
    private Hyperlink categoryy;
    @FXML
    private TextField searchP;



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
    categoryy.setOnAction(e -> {
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
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/AjouterProduct.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        productTable.setItems(listdata.getProducts());
productTable.getSortOrder().add(name); // add the column to sort by
name.setSortType(TableColumn.SortType.ASCENDING); // set the sort type
productTable.sort(); 
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        categoryname.setCellValueFactory(cellData -> {
    SimpleStringProperty property = new SimpleStringProperty();
    if (cellData.getValue() != null && cellData.getValue().getCategory() != null) {
        property.setValue(cellData.getValue().getCategory().getName());
    }
    return property;
});

        searchP.textProperty().addListener((observable, oldValue, newValue) -> {
            // Create filter Predicate
            Predicate<Product> filter = item -> {
                // Implement filter logic here
                String query = newValue.toLowerCase();
                return item.getName().toLowerCase().contains(query)||item.getDescription().toLowerCase().contains(query)||item.getPrice().toLowerCase().contains(query)||item.getStock().toLowerCase().contains(query);
            };

            // Apply filter to data and update TableView
            productTable.setItems(listdata.getProducts().filtered(filter));
        });
    } 
    

        
    public void delete(){
    pdao.delete(productTable.getSelectionModel().getSelectedItem().getId());
    System.out.println(productTable.getSelectionModel().getSelectedItem().getId());
    }
    @FXML
    public void supprimer(javafx.scene.input.MouseEvent event) {
   delete();
   productTable.getItems().removeAll(productTable.getSelectionModel().getSelectedItem());
   System.out.println(productTable);
          
    }

    public void EXIT(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

   @FXML
    void updatebtn(ActionEvent event) {
   

    // Get selected Event from the table
    Product selectedproduct = productTable.getSelectionModel().getSelectedItem();
    CategoryDao cdao = new CategoryDao();
    if (selectedproduct != null) {
        try {
            // Create the FXMLLoader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/view/UpdateProduct.fxml"));

            // Load the UpdateEvents view
            Parent root = loader.load();

            // Get the UpdateEvents controller
            UpdateProductController updateproductsController = loader.getController();

            // Pass the selected Event to the controller
            updateproductsController.setProduct(selectedproduct);

            // Create a new scene and set it on the stage
            Scene updatecategoryScene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(updatecategoryScene);
            stage.show();
            
            productTable.refresh();
             productTable.getSelectionModel().clearSelection();
        productdata.clear();
    productdata.addAll(pdao.displayAll());
    productTable.setItems(productdata);
            

           /* // Refresh the table after updating the Event
            eventsdata.setAll(evd.displayAll()); */

        } catch (IOException e) {
            e.printStackTrace();
        }
        
       
    }
}
        
  }