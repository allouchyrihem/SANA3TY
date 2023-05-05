/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.dao.ProductDao;
import edu.esprit.entity.Category;
import edu.esprit.entity.Product;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author asus
 */
public class UpdateProductController implements Initializable {

    @FXML
    private Pane BoutonValider;
    @FXML
    private Button btn;
    @FXML
    private TextField name;
    @FXML
    private TextField description;
    @FXML
    private TextField price;
    @FXML
    private TextField stock;
    @FXML
    private Button addImage;
    @FXML
    private ImageView ImageView;
    @FXML
    private ComboBox<Category> cat;
    @FXML
    private Button listp;
    @FXML
    private Button back;
    @FXML
    private Hyperlink dashboard;
    @FXML
    private Hyperlink product;
    @FXML
    private Hyperlink category;
    @FXML
    private Hyperlink accueil;
    ProductDao pdao;
    private Stage anchorPane;
    private Product products;
    List<String> lstFile;
    private File f = null;
    private ObservableList<Category> observableOptions ;
    private ListData listData = new ListData(); // initialize listData
    private ObservableList<Category> categories=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               pdao = new ProductDao();
               categories = listData.getcategories();
        observableOptions = FXCollections.observableArrayList(categories);
        cat.setItems(observableOptions);
    lstFile = new ArrayList<>();
        lstFile.add("*.png");
        lstFile.add("*.PNG");
        lstFile.add("*.jpg");
        lstFile.add("*.JPEG");
    }   
    
    
     public void setDialogueStage(Stage anchorPane){
    this.anchorPane = anchorPane;
}   

        public void setProduct(Product products) throws IOException{
    this.products = products;
    name.setText(products.getName());
    description.setText(products.getDescription());
    price.setText(Float.toString(products.getPrice()));
    stock.setText(products.getStock());
    addImage.setText(products.getImage());
    ImageView.setImage(decodeBase64Image(products.getImage()));
    cat.setValue(products.getCategory());
    
}
           public static Image decodeBase64Image(String imageString) throws IOException {
    byte[] imageData = Base64.getDecoder().decode(imageString);
    ByteArrayInputStream stream = new ByteArrayInputStream(imageData);
    return new Image(stream);

}
    @FXML
    public void valider(ActionEvent event) throws IOException {
        if (name.getText().isEmpty()) {
                showAlert("entrer une nom",false);
            } else if (!name.getText().matches("[a-zA-Z ]+")) {
                showAlert("le nom du produit doit contenir que des lettres ou espace",false);
            }
            else if (description.getText().isEmpty()) {
                showAlert("entrer une description",false);
            } else if (!description.getText().matches("[a-zA-Z ]+")) {
                showAlert("la description doit contenir que des lettres ou espace",false);
            }
            else if (price.getText().isEmpty()) {
                showAlert("entrer un prix",false);
            } else if (!price.getText().matches("[0-9]+")) {
                showAlert("le prix doit contenir que des nombres",false);
            }
            else if (stock.getText().isEmpty()) {
                showAlert("entrer la quantité du stock",false);
            } else if (!stock.getText().matches("[0-9]+")) {
                showAlert("le stock doit contenir que des nombres",false);
            }
            else if (f==null){
                showAlert("l'image est obligatoire",false);

            }else {
                String namee = name.getText();
        String descriptione = description.getText();    
        Float pricee = Float.parseFloat(price.getText());
        String stockk=stock.getText();
        String imagee = imageEncoderDecoder(f.getAbsolutePath());
        Category catee = cat.getValue();
        Product p = new Product(products.getId(),namee,descriptione,pricee,stockk,imagee,catee);
        pdao.update(p);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText("produit modifié avec succès!");
        alert.showAndWait();
        
        
    }}
  private void showAlert(String message ,boolean b) {
        Alert alert;
        if (b)
            alert = new Alert(Alert.AlertType.INFORMATION);
        else
            alert = new Alert(Alert.AlertType.ERROR);


        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }
  public String imageEncoderDecoder(String path) throws IOException {

    // read image from file
    FileInputStream stream = new FileInputStream(path);

    // get byte array from image stream
    int bufLength = 2048;
    byte[] buffer = new byte[2048];
    byte[] data;

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    int readLength;
    while ((readLength = stream.read(buffer, 0, bufLength)) != -1) {
        out.write(buffer, 0, readLength);
    }

    data = out.toByteArray();
    String imageString = Base64.getEncoder().withoutPadding().encodeToString(data);

    out.close();
    stream.close();
    return imageString;
}
  @FXML
    void SingleImage(ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpg and png file",lstFile));
        f = fc.showOpenDialog(null);
        if (f!= null ){
            addImage.setText(f.getName());
            ImageView.setImage(new Image("file:" + f));
            
        }
    }

    
}
