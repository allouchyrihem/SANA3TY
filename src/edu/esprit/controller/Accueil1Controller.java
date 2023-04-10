package edu.esprit.controller;

import edu.esprit.entity.Product;
import java.awt.image.BufferedImage;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class Accueil1Controller implements Initializable {

    @FXML
    private Text text1;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField search;
    @FXML
    private Button buttonSearch;
    @FXML
    private ComboBox<String> catbox;
    @FXML
    private ImageView img;
    private ObservableList<String> observableOptions ;
    private ListData listData = new ListData(); // initialize listData
    private ObservableList<String> categories=FXCollections.observableArrayList();
    private ObservableList<Product> products=FXCollections.observableArrayList();
    private Label label1;
    private Label label2;
    private VBox vbox;
    @FXML
    private Pane rootContainer;
    @FXML
    private Hyperlink link ;
    @FXML
    private Hyperlink cateAdd;

    /**
     * Initializes the controller class.
     */
@Override
public void initialize(URL url, ResourceBundle rb) {
    link.setOnAction(e -> {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/AjouterProduct.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
});
    cateAdd.setOnAction(e -> {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/AjouterCategory.fxml"));
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
    catbox.setItems(observableOptions);
    products = listData.getProducts();

    VBox vboxContainer = new VBox(); // Créer un VBox pour contenir les HBox
vboxContainer.setSpacing(10); // Définir l'espacement entre les HBox

HBox hbox = new HBox(); // Créer un HBox pour contenir 3 produits
for (int i = 0; i < products.size(); i++) {
    Product p = products.get(i);
    VBox vbox = new VBox(); // Créer un VBox pour chaque produit
    // Ajouter le contenu du VBox pour le produit courant
    try {
        Image image = decodeBase64Image(p.getImage());
        ImageView img = new ImageView(image);
        img.setFitWidth(200);
        img.setFitHeight(200);
        vbox.getChildren().addAll(new Label(p.getName()), img, new Label(p.getDescription()));
    } catch (IOException ex) {
        Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
    }
    hbox.getChildren().add(vbox); // Ajouter le VBox pour le produit courant au HBox
    if ((i + 1) % 3 == 0) { // Si on a ajouté 3 produits au HBox
        vboxContainer.getChildren().add(hbox); // Ajouter le HBox au VBox container
        hbox = new HBox(); // Créer un nouveau HBox pour les prochains produits
    }
}

if (hbox.getChildren().size() > 0) { // Si on a encore des produits restants
    vboxContainer.getChildren().add(hbox); // Ajouter le dernier HBox au VBox container
}

rootContainer.getChildren().add(vboxContainer); // Ajouter le VBox container au rootContainer

}


    public static Image decodeBase64Image(String imageString) throws IOException {
    byte[] imageData = Base64.getDecoder().decode(imageString);
    ByteArrayInputStream stream = new ByteArrayInputStream(imageData);
    return new Image(stream);

}
}
