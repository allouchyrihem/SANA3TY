/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import javafx.scene.layout.Pane;
import java.util.Date;
import edu.esprit.dao.CmdDao;
import edu.esprit.entity.Commande;
import edu.esprit.entity.ProductCmd;
import edu.esprit.entity.User;
import edu.esprit.test.ConnexionBD;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
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
    ConnexionBD app = new ConnexionBD();
                User connectedUser = app.getConnectedUser();
            

List<Commande> userCommands = new ArrayList<>();
for (Commande c : listdata.getcommandes()) {
    if (c.getUser().getId() == connectedUser.getId()) {
        userCommands.add(c);
    }
}
           commandeTable.setItems(FXCollections.observableArrayList(userCommands));
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
    
