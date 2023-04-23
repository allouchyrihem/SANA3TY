/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;


import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class AccueilController implements Initializable{

    @FXML
    private Text text1;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField search;
    @FXML
    private Button buttonSearch;
    @FXML
    private Text Acceuil;
    @FXML
    private Text Boutique;
    @FXML
    private Text Details;
    @FXML
    private Text Contact;
    @FXML
    private Text Seconnecter;
    @FXML
    private Text Sinscrire;
    @FXML
    private ComboBox<?> catbox;
    @FXML
    private ImageView img;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_addc;
     /**
     * Initializes the controller class.
     */
  
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btn_add.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/AjouterReclamation.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        btn_addc.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/AjouterComment.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
           
       
    }
  



    
}
