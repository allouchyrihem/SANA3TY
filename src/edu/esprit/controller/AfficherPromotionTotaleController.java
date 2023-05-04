/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import static com.fasterxml.jackson.databind.util.ClassUtil.name;
import static com.fasterxml.jackson.databind.util.ClassUtil.name;
import edu.esprit.dao.EventsDao;
import edu.esprit.dao.PromotionTotaleDao;
import edu.esprit.entity.Events;
import edu.esprit.entity.PromotionTotale;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rouao
 */
public class AfficherPromotionTotaleController implements Initializable {
    @FXML
    private ObservableList<PromotionTotale> eventsdata= FXCollections.observableArrayList();;
  
    @FXML
    private Button updatebuttonid;
    @FXML
    private Button deletebuttonid;
    @FXML
    private Button addevid;
    @FXML
    private TableView<PromotionTotale> personsTable;
       @FXML
    private TableColumn<?, ?> DatedColonne;

    @FXML
    private TableColumn<?, ?> DatefColonne;

    @FXML
    private TableColumn<?, ?> NameColonne;

    @FXML
    private TableColumn<?, ?> ValueColonne;

    /**
     * Initializes the controller class.
     */
    
     private PromotionTotaleDao evd= new PromotionTotaleDao() ;
    private ObservableList<PromotionTotale> evnmdata = FXCollections.observableArrayList();
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          // TODO
       // personsTable.setItems(listdata.getPersons());
       DatedColonne.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
       DatefColonne.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
      // IdColonne.setCellValueFactory(new PropertyValueFactory<>("id"));
        NameColonne.setCellValueFactory(new PropertyValueFactory<>("name"));
        ValueColonne.setCellValueFactory(new PropertyValueFactory<>("value"));
       
        
        // Load the date from the database and add it to the table
        
        List<PromotionTotale> evnm = evd.displayAll();
        eventsdata.addAll(evnm);
        personsTable.setItems(eventsdata);
    }    

    @FXML
    private void updatebtn(ActionEvent event) {
        
   

    // Get selected Event from the table
    PromotionTotale selectedPromo = personsTable.getSelectionModel().getSelectedItem();
   PromotionTotaleDao promoDao = new PromotionTotaleDao();
    if (selectedPromo != null) {
        try {
            // Create the FXMLLoader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/view/UpdatePromotionTotale.fxml"));

            // Load the UpdateEvents view
            Parent root = loader.load();

            // Get the UpdateEvents controller
            UpdatePromotionTotaleController updatePromoController = loader.getController();

            // Pass the selected Event to the controller
            updatePromoController.setPromo(selectedPromo);

            // Create a new scene and set it on the stage
            Scene updatePromoScene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(updatePromoScene);
            stage.show();
            
            personsTable.refresh();
             personsTable.getSelectionModel().clearSelection();
        evnmdata.clear();
    evnmdata.addAll(promoDao.displayAll());
    personsTable.setItems(evnmdata);
            

           /* // Refresh the table after updating the Event
            eventsdata.setAll(evd.displayAll()); */

        } catch (IOException e) {
            e.printStackTrace();
        }
        
       
    }
    }

    @FXML
    private void deletebtn(ActionEvent event) {
        
    ObservableList<PromotionTotale> selectedItems = personsTable.getSelectionModel().getSelectedItems();
    PromotionTotaleDao promodao = new PromotionTotaleDao();
    for ( PromotionTotale pro : selectedItems){
        promodao.delete(pro);
        evnmdata.remove(pro);
    }
    personsTable.getSelectionModel().clearSelection();
        evnmdata.clear();
    evnmdata.addAll(promodao.displayAll());
    personsTable.setItems(evnmdata);
    }

    @FXML
    private void addbtn(ActionEvent event) {
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/AjouterPromotionTotale.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AfficherEventsController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
