/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.dao.ReclamationDao;
import edu.esprit.entity.Product;
import edu.esprit.entity.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class AfficherReclamationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Reclamation> productTable;
    @FXML
    private TableColumn<Reclamation, String> name;
    @FXML
    private TableColumn<Reclamation, String> description;
    
         @FXML
    private Button supprimer;
   
    private ListData1 listdata = new ListData1();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        productTable.setItems(listdata.getReclamations());
        name.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
     
    } 
    
        public void update(javafx.scene.input.MouseEvent event) {

        ReclamationDao pdao=new ReclamationDao();
        Reclamation p=new Reclamation();
        p=productTable.getSelectionModel().getSelectedItem();
        p.setSujet(productTable.getSelectionModel().getSelectedItem().getSujet());
        p.setDescription(productTable.getSelectionModel().getSelectedItem().getDescription());
        p.setDate(productTable.getSelectionModel().getSelectedItem().getDate());
        
        pdao.update(p);
              
        
    }
    public void delete(){
    ReclamationDao pdao =new ReclamationDao();
    pdao.delete(productTable.getSelectionModel().getSelectedItem().getId());
    System.out.println(productTable.getSelectionModel().getSelectedItem().getId());
    }
@FXML
    public void supprimer(javafx.scene.input.MouseEvent event) {
   delete();
   productTable.getItems().removeAll(productTable.getSelectionModel().getSelectedItem());
   System.out.println(productTable);
          
    }

    @FXML
    private void EXIT(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
   
}