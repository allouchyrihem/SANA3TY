/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import static edu.esprit.controller.SignUpClientController.isValidEmailFormat;
import edu.esprit.dao.UserDao;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Fattouma PC
 */
public class UpdateProfileController implements Initializable {

     @FXML
    private Button backbtn;

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private Button updateBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
           ConnexionBD app = new ConnexionBD();
           User connectedUser = app.getConnectedUser();
           System.out.println(connectedUser);
           emailField.setText(connectedUser.getEmail());
           nameField.setText(connectedUser.getName());
           backbtn.setOnAction(event -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/Profile.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         updateBtn.setOnAction(event -> {
            if (nameField.getText().trim().isEmpty() || emailField.getText().trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs.");
                alert.show();
            }else if(!isValidEmailFormat(emailField.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("L'email fournit n'est pas valide.");
                alert.show();
            }
            else {
                User u = new User(connectedUser.getId(),nameField.getText(), emailField.getText());
                UserDao pdao = UserDao.getInstance();
                pdao.update(u);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Profil modifié avec succés!");
                alert.show();
                nameField.setText("");
                emailField.setText("");
            }
        });

    }
}    
    

