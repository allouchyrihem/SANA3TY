/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.entity.Product;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class BoutiqueController implements Initializable {

    @FXML
    private Text text1;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField search;
    @FXML
    private Hyperlink dashboard;
    @FXML
    private ComboBox<String> catbox;
    @FXML
    private ImageView img;
    @FXML
    private Pane rootContainer;
    @FXML
    private Hyperlink boutique;
    private ObservableList<String> observableOptions ;
    private ListData listData = new ListData(); // initialize listData
    private ObservableList<Product> products=FXCollections.observableArrayList();
    private ObservableList<String> categories=FXCollections.observableArrayList();
    private ObservableList<Product> Filtredproducts=FXCollections.observableArrayList();
    @FXML
    private Hyperlink accueil;
    @FXML
    private AnchorPane root;
    @FXML
    private Hyperlink reclamation;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
          reclamation.setOnAction(e -> {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/AjouterReclamation.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
});
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
    boutique.setOnAction(e -> {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/Boutique.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
});
    categories = listData.getNames();
    observableOptions = FXCollections.observableArrayList(categories);
    catbox.setItems(categories);

    products = listData.getProducts();
   // Assume products is a List<Product> containing all the products to be displayed
int productsPerPage = 9; // Number of products to display per page
int numPages = (int) Math.ceil(products.size() / (double) productsPerPage); // Calculate the number of pages needed

    Pagination pagination = new Pagination(numPages, 0); // Create a new Pagination object with the correct number of pages
    pagination.setPageFactory(pageIndex -> {
    // Create a VBox to hold the products for the current page
    VBox vboxContainer = new VBox();
    vboxContainer.setSpacing(10);

    // Calculate the start and end index of the products for the current page
    int startIndex = pageIndex * productsPerPage;
    int endIndex = Math.min(startIndex + productsPerPage, products.size());

    // Create an HBox to hold each row of products (3 per row)
    for (int i = startIndex; i < endIndex; i += 3) {
        HBox hbox = new HBox();
        hbox.setSpacing(10);

        // Create a VBox to hold each individual product
        for (int j = i; j < Math.min(i + 3, endIndex); j++) {
            Product p = products.get(j);
            VBox vbox = new VBox();
            try {
                Image image = decodeBase64Image(p.getImage());
                ImageView img = new ImageView(image);
                img.setFitWidth(180);
                img.setFitHeight(180);
                img.setOnMouseClicked(e -> {
                    try {
                        // Navigate to the product details page when the product name is clicked
                        goToProductDetailsPage(p);
                    } catch (IOException ex) {
                        Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                Label productNameLabel = new Label(p.getName());
                productNameLabel.setOnMouseClicked(e -> {
                    try {
                        // Navigate to the product details page when the product name is clicked
                        goToProductDetailsPage(p);
                    } catch (IOException ex) {
                        Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                vbox.getChildren().addAll(productNameLabel, img, new Label(p.getDescription()));
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            hbox.getChildren().add(vbox);
        }

        vboxContainer.getChildren().add(hbox);
    }

    return vboxContainer; // Return the VBox containing the products for the current page
});

rootContainer.getChildren().addAll(pagination); // Add the pagination control to the rootContainer
catbox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    if (newValue != null) {
        Filtredproducts=products.filtered(product -> product.getCategory().getName().equals(newValue));
        pagination.setPageFactory(pageIndex -> {
    // Create a VBox to hold the products for the current page
        VBox vboxContainer = new VBox();
        vboxContainer.setSpacing(10);

    // Calculate the start and end index of the products for the current page
        int startIndex = pageIndex * productsPerPage;
        int endIndex = Math.min(startIndex + productsPerPage, Filtredproducts.size());
    // Create an HBox to hold each row of products (3 per row)
        for (int i = startIndex; i < endIndex; i += 3) {
            HBox hbox = new HBox();
            hbox.setSpacing(10);

        // Create a VBox to hold each individual product
        for (int j = i; j < Math.min(i + 3, endIndex); j++) {
            Product p = Filtredproducts.get(j);
            VBox vbox = new VBox();
            try {
                Image image = decodeBase64Image(p.getImage());
                ImageView img = new ImageView(image);
                img.setFitWidth(180);
                img.setFitHeight(180);
                img.setOnMouseClicked(e -> {
                    try {
                        // Navigate to the product details page when the product name is clicked
                        goToProductDetailsPage(p);
                    } catch (IOException ex) {
                        Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                Label productNameLabel = new Label(p.getName());
                productNameLabel.setOnMouseClicked(e -> {
                    try {
                        // Navigate to the product details page when the product name is clicked
                        goToProductDetailsPage(p);
                    } catch (IOException ex) {
                        Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                vbox.getChildren().addAll(productNameLabel, img, new Label(p.getDescription()));
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            hbox.getChildren().add(vbox);
        }

        vboxContainer.getChildren().add(hbox);
    }

    return vboxContainer; // Return the VBox containing the products for the current page
});
    }
});
    }

    public static Image decodeBase64Image(String imageString) throws IOException {
    byte[] imageData = Base64.getDecoder().decode(imageString);
    ByteArrayInputStream stream = new ByteArrayInputStream(imageData);
    return new Image(stream);

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

    }    
    

