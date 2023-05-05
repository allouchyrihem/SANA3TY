/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.entity.User;
import edu.esprit.test.ConnexionBD;
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
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Fattouma PC
 */
public class ProfileController implements Initializable {

   
    @FXML
    private ImageView imageView;

    @FXML
    private Text userNAme;

    @FXML
    private Button backbtn;

    @FXML
    private Text name;

    @FXML
    private Text email;

    @FXML
    private Text description;

    @FXML
    private Button updateProfile;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
           ConnexionBD app = new ConnexionBD();
           User connectedUser = app.getConnectedUser();
           userNAme.setText(connectedUser.getName());
           email.setText(connectedUser.getEmail());
           name.setText(connectedUser.getName());
           if(connectedUser.getDescription()!=null){
               description.setText(connectedUser.getDescription());
           }else{
               description.setText("");
           }
         updateProfile.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/UpdateProfile.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UpdateProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         backbtn.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/Users.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    
}
