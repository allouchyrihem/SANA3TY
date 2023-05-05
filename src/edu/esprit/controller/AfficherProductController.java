/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import static edu.esprit.controller.BoutiqueController.decodeBase64Image;
import edu.esprit.dao.CategoryDao;
import edu.esprit.dao.ProductDao;
import edu.esprit.entity.Category;
import edu.esprit.entity.Product;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.List;
import static java.util.Optional.empty;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
    private TableColumn<Product, String> image;
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
    private TextField searchP;
    private Pane rootContainer;
    private ListData listData = new ListData(); // initialize listData
    private ObservableList<Product> products=FXCollections.observableArrayList();
    @FXML
    private TableColumn<Product,String> vendeur;
    @FXML
    private Hyperlink category;
    @FXML
    private Hyperlink evenement;
    @FXML
    private Hyperlink commande;



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
       commande.setOnAction(e -> {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/AfficherCommande.fxml"));
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
    category.setOnAction(e -> {
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
     
      evenement.setOnAction(e -> {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/AfficherEvents.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
});
        productTable.setItems(listdata.getProducts());
        
        productTable.getSortOrder().add(name); // add the column to sort by
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        image.setCellFactory(column -> {
        return new TableCell<Product, String>() {
        private final ImageView imageView = new ImageView();
        {
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            setGraphic(imageView);
        }
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                imageView.setImage(null);
                
            } else {
                try {
                    Image image = decodeBase64Image(item);
                    imageView.setImage(image);
                    imageView.setFitWidth(70);
                imageView.setFitHeight(50);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };
});
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        vendeur.setCellValueFactory(cellData -> {
        SimpleStringProperty property = new SimpleStringProperty();
        if (cellData.getValue() != null && cellData.getValue().getUser() != null) {
        property.setValue(cellData.getValue().getUser().getName());
    }
        return property;
});
        name.setSortType(TableColumn.SortType.ASCENDING); // set the sort type
        productTable.sort(); 
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
                return item.getName().toLowerCase().contains(query)||item.getDescription().toLowerCase().contains(query)||item.getStock().toLowerCase().contains(query);
            };

            // Apply filter to data and update TableView
            productTable.setItems(listdata.getProducts().filtered(filter));
        });
    } 
  private void goToProductDetailsPage(Product p) throws IOException {
    // Navigate to the product details page and pass the selected product as a parameter
    // You can use a URL parameter or a session to pass the product information to the next page
    // For example, you can use the following code to navigate to the product details page with a URL parameter:
     Stage stage = (Stage) rootContainer.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/view/Detail.fxml"));
    Scene scene = new Scene(loader.load());
    stage.setScene(scene);
    DetailController controller = loader.getController();
    controller.setProduct(p);
    stage.show();
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
        public static Image decodeBase64Image(String imageString) throws IOException {
    byte[] imageData = Base64.getDecoder().decode(imageString);
    ByteArrayInputStream stream = new ByteArrayInputStream(imageData);
    return new Image(stream);

}

   @FXML
    void updatebtn(ActionEvent event) {
   

    // Get selected Event from the table
    Product selectedproduct = productTable.getSelectionModel().getSelectedItem();
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