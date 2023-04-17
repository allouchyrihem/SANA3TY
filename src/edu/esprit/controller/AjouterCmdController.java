/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.dao.CategoryDao;
import edu.esprit.dao.CmdDao;
import edu.esprit.dao.ProductDao;
import edu.esprit.entity.Category;
import edu.esprit.entity.Commande;
import edu.esprit.entity.Product;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjouterCmdController implements Initializable {
    @FXML
    private Button btnCmd;
    @FXML
    private TextField adresse;
    @FXML
    private TextField description;
    @FXML
    private TextField etat;
    @FXML
    private DatePicker  datecmd;
     @FXML
    private TextField totale;
    @FXML
    private Button back;
    private boolean present;
    private final String[] listeMots = {"mot1", "mot2", "mot3"};
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       Date date = new Date();
Timestamp timestamp = new Timestamp(date.getTime());
         btnCmd.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               if (adresse.getText().trim().length() <4 || description.getText().trim().length() <3  ) {
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText(null);
                   alert.setContentText("Veuillez remplir tous les champs.");
                   alert.show();
                   
               }else {
                   
                   Commande p = new Commande(adresse.getText(), description.getText());
                   CmdDao cdao = CmdDao.getInstance();
                   cdao.insert(p);
                   
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Information Dialog");
                   alert.setHeaderText(null);
                   alert.setContentText("Commande insérée avec succés!");
                   alert.show();
                   adresse.setText("");
                   description.setText("");
               }
           }
       });
    }  
}
