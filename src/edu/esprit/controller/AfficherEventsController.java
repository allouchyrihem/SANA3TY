/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.dao.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import edu.esprit.controller.ListData;
import edu.esprit.dao.EventsDao;
import edu.esprit.entity.Events;

/**
 * FXML Controller class
 *
 * @author rouao
 */
public class AfficherEventsController implements Initializable {
@FXML
    private ObservableList<Events> eventsdata= FXCollections.observableArrayList();;
   @FXML
    private TableView<Events> personsTable;
   //@FXML
    //private TableColumn<Events, Integer> IdColonne;
    @FXML
    private TableColumn<Events, String> NameColonne;
     @FXML
    private TableColumn<Events, String> AdresseColonne;
      @FXML
    private TableColumn<Events, String> LinkColonne;
       @FXML
    private TableColumn<Events, String> DescriptionColonne;
       @FXML
    private TableColumn<Events, LocalDate> DateColonne;
         @FXML
    private Button deletebuttonid;

   
    @FXML
    private Button updatebuttonid;

    

    
    @FXML
    private Button addevid;
    //private ListData listdata = new ListData();
    /**
     * Initializes the controller class.
     */
    private EventsDao evd= new EventsDao() ;
    private ObservableList<Events> evnmdata = FXCollections.observableArrayList();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       // personsTable.setItems(listdata.getPersons());
       DateColonne.setCellValueFactory(new PropertyValueFactory<>("date"));
      // IdColonne.setCellValueFactory(new PropertyValueFactory<>("id"));
        NameColonne.setCellValueFactory(new PropertyValueFactory<>("name"));
        AdresseColonne.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        LinkColonne.setCellValueFactory(new PropertyValueFactory<>("link"));
        DescriptionColonne.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        // Load the date from the database and add it to the table
        
        List<Events> evnm = evd.displayAll();
        eventsdata.addAll(evnm);
        personsTable.setItems(eventsdata);
    }
    @FXML
    void addbtn(ActionEvent event) {
        
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/javafxapplication30/view/AjouterEvents.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AfficherEventsController.class.getName()).log(Level.SEVERE, null, ex);
            }

          
    }    
    
    
    
    @FXML
void deletebtn(ActionEvent event) {
    ObservableList<Events> selectedItems = personsTable.getSelectionModel().getSelectedItems();
    EventsDao eventsdao = new EventsDao();
    for ( Events evennement : selectedItems){
        eventsdao.delete(evennement);
        evnmdata.remove(evennement);
    }
    personsTable.getSelectionModel().clearSelection();
        evnmdata.clear();
    evnmdata.addAll(eventsdao.displayAll());
    personsTable.setItems(evnmdata);
}


@FXML
void updatebtn(ActionEvent event) {
   

    // Get selected Event from the table
    Events selectedEvent = personsTable.getSelectionModel().getSelectedItem();
   EventsDao eventsdao = new EventsDao();
    if (selectedEvent != null) {
        try {
            // Create the FXMLLoader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/view/UpdateEvent.fxml"));

            // Load the UpdateEvents view
            Parent root = loader.load();

            // Get the UpdateEvents controller
            UpdateEventsController updateEventsController = loader.getController();

            // Pass the selected Event to the controller
            updateEventsController.setEvent(selectedEvent);

            // Create a new scene and set it on the stage
            Scene updateEventsScene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(updateEventsScene);
            stage.show();
            
            personsTable.refresh();
             personsTable.getSelectionModel().clearSelection();
        evnmdata.clear();
    evnmdata.addAll(eventsdao.displayAll());
    personsTable.setItems(evnmdata);
            

           /* // Refresh the table after updating the Event
            eventsdata.setAll(evd.displayAll()); */

        } catch (IOException e) {
            e.printStackTrace();
        }
        
       
    }
}



    }
    
    
