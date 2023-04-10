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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class AjouterProductController  implements Initializable {

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
    private File f = null;
    @FXML
    private ImageView ImageView;
    List<String> lstFile;
    private ListData listdata = new ListData();
    @FXML
    private Pane BoutonValider;
    @FXML
    private ComboBox<String> cat;
    private ObservableList<String> observableOptions ;
    private ListData listData = new ListData(); // initialize listData
    private ObservableList<String> categories=FXCollections.observableArrayList();
    @FXML
    private Button back;
    @FXML
    private Button listp;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    
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
        fc.getExtensionFilters().add(new ExtensionFilter("jpg and png file",lstFile));
        f = fc.showOpenDialog(null);
        if (f!= null ){
            addImage.setText(f.getName());
            ImageView.setImage(new Image("file:" + f));
            
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO     
          back.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/Accueil1.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
          listp.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/AfficherProduct.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        categories = listData.getNames();
        CategoryDao cdao =new CategoryDao();
        observableOptions = FXCollections.observableArrayList(categories);
        cat.setItems(observableOptions);
        btn.setOnAction((ActionEvent event) -> {
            String text = name.getText();
            Product p;
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

            try {
                int id = cdao.displayIdByName(cat.getValue());
                p = new Product(name.getText(), description.getText(),price.getText(),stock.getText(),imageEncoderDecoder(f.getAbsolutePath()),id);
                ProductDao pdao = ProductDao.getInstance();
                pdao.insert(p);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/view/Accueil1.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                showAlert("Insertion terminée", true);

                name.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AjouterProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        name.setText("");
        description.setText("");
        price.setText("");
        stock.setText("");
        addImage.setText("");
        ImageView.setImage(null);
        cat.setItems(observableOptions);
        }});
       
        lstFile = new ArrayList<>();
        lstFile.add("*.png");
        lstFile.add("*.PNG");
        lstFile.add("*.jpg");
        lstFile.add("*.JPEG");
        
    }
    
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

   

}

