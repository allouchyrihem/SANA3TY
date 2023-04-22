/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import java.util.Date;
import edu.esprit.dao.CmdDao;
import edu.esprit.dao.ProductDao;
import edu.esprit.entity.Commande;
import edu.esprit.entity.Product;
import edu.esprit.entity.ProductCmd;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AfficherCmdController implements Initializable {

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

    @FXML
    private Button supprimer;
    @FXML
    private Button updateU;
    @FXML
    private Button back;
    @FXML
    private Button exit;
    private ListData listdata = new ListData();
    @FXML
    private BorderPane bp;
    @FXML
    private Button promotionNavBarid;
    @FXML
    private TableColumn<Commande, Float> totale;
    @FXML
    private TableColumn<Commande, Date> datecmd;
    @FXML
    private ImageView imageView;
    @FXML
    private ComboBox<String> etatC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        etatC.setItems(FXCollections.observableArrayList("en attente","en cours","livré"));
         back.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/Dashboard.fxml"));
                Scene scene
                        = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
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
@FXML
private void update(MouseEvent event) {
    CmdDao pdao = CmdDao.getInstance();
    Commande p = commandeTable.getSelectionModel().getSelectedItem();
    String newEtat = etatC.getValue(); // get the new value from the ComboBox
    p.setEtat(newEtat); // update the etat property of the selected Commande
    pdao.updateCommandState(p); // call the update method in the CmdDao class
}


    public void delete(){
    CmdDao pdao =new CmdDao();
    pdao.delete(commandeTable.getSelectionModel().getSelectedItem().getId());
    System.out.println(commandeTable.getSelectionModel().getSelectedItem().getId());
    }
   @FXML
    public void supprimer(javafx.scene.input.MouseEvent event) {
   delete();
   commandeTable.getItems().removeAll(commandeTable.getSelectionModel().getSelectedItem());
   System.out.println(commandeTable);
          
    }

    @FXML
    public void EXIT(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
}