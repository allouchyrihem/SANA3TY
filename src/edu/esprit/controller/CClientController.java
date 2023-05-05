/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//import com.itextpdf.text.Cell;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;


import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.util.Date;
import edu.esprit.dao.CmdDao;
import edu.esprit.dao.ProductDao;
import edu.esprit.entity.Commande;
import edu.esprit.entity.Product;
import edu.esprit.entity.ProductCmd;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.SVGPath;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
/**
 * FXML Controller class
 *
 * @author HP
 */
public class CClientController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private Pane BoutonValiderC;
    @FXML
    private TableView<Commande> commandeTable;
    @FXML
    private TableColumn<Commande, String> adresse;
    @FXML
    private TableColumn<Commande, String> description;
    @FXML
    private TableColumn<Commande, String> etat;
     @FXML
    private TableColumn<Commande, String> produit;
    @FXML
    private TableColumn<Commande, String> quantite;
  //  private Button back;
    private ListData listdata = new ListData();
    @FXML
    private Hyperlink boutique;
    @FXML
    private Hyperlink dashboard;
@FXML
    private Hyperlink acceuil;

    @FXML
    private TableColumn<Commande, Float> totale;
    @FXML
    private TableColumn<Commande, Date> datecmd;
    @FXML
    private ImageView imageView;
    private ComboBox<String> etatC;
    @FXML
    private SVGPath chariot;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // commandeTable.getColumns().add(emailColumn);
       // etatC.setItems(FXCollections.observableArrayList("en attente","en cours","livré"));
         acceuil.setOnAction(e -> {
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
      chariot.setOnMouseClicked(event -> {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/view/CartView.fxml"));
        Parent root = loader.load();
       // AjouterCmdController ajouterCmdController = loader.getController();
        //ajouterCmdController.setCartProducts(products); // passer la liste des produits dans le panier
        //ajouterCmdController.setSelectedProduct(selectedProduct); // passer le produit sélectionné
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(BoutiqueController.class.getName()).log(Level.SEVERE, null, ex);
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
           commandeTable.setItems(listdata.getcommandes());
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        totale.setCellValueFactory(new PropertyValueFactory<>("totale"));
        datecmd.setCellValueFactory(new PropertyValueFactory<>("datecmd"));
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        produit.setCellFactory(column -> {
    return new TableCell<Commande, String>() {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
            } else {
                Commande commande = getTableView().getItems().get(getIndex());
                ObservableList<ProductCmd> commandeProducts = commande.getCommandeProducts();
                String produits = "";
                for (ProductCmd commandeProduct : commandeProducts) {
                    produits += commandeProduct.getProduct().getName() + ", ";
                }
                setText(produits);
            }
        }
    };
});

   
    
    }    

private void updateCommandState() {
        CmdDao cmdDao =new CmdDao();

    Commande selectedCommande = commandeTable.getSelectionModel().getSelectedItem();
    if (selectedCommande == null) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("No Command Selected");
        alert.setHeaderText(null);
        alert.setContentText("Please select a command in the table.");
        alert.showAndWait();
    } else {
        TextInputDialog dialog = new TextInputDialog(selectedCommande.getEtat());
        dialog.setTitle("Update Command State");
        dialog.setHeaderText(null);
        dialog.setContentText("New state:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            selectedCommande.setEtat(result.get());
            cmdDao.updateCommandState(selectedCommande);
            // refresh the table to reflect the changes
            commandeTable.refresh();
            System.out.println("Command state updated.");
        }
    }
}
    }    
    

