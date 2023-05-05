/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import edu.esprit.dao.ReclamationDao;
import edu.esprit.entity.Category;
import edu.esprit.entity.Product;
import edu.esprit.entity.Reclamation;
import edu.esprit.entity.SendSMS;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class AjouterReclamationController  implements Initializable {

    @FXML
    private Button btn;
    @FXML
    private TextField name;
    @FXML
    private TextField description;
    @FXML
    private Pane BoutonValider;
    @FXML
    private ImageView imageView;
    @FXML
    private Button back;
  
    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO     
        btn.setOnAction((ActionEvent event) -> {
             if ( isInputValid() ){
            Reclamation p = new Reclamation(name.getText(), description.getText());
            ReclamationDao pdao = ReclamationDao.getInstance();
            pdao.insert(p);
            SendSMS.sendSms();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reclamation insérée avec succés!");
        alert.show();
        name.setText("");
        description.setText("");
             }
       
        });
          back.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/Accueil1.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
    }
private void back(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/esprit/view/Accueil1.fxml"));
    Parent root = loader.load();

    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}


 
    private void EXIT(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    private boolean isInputValid() {
        String errorMessage = "";
         if (name.getText() == null || name.getText().isEmpty()) {
            errorMessage += "le sujet est requis.\n";
        }
         if (description.getText() == null || description.getText().isEmpty()) {
            errorMessage += "ne laisser pas ce champ vide.\n";
        }
         if (description.getText().length() < 2 || description.getText().length() > 100) {
    errorMessage += "vous  devez ajouter entre  entre 2 et 100 caractères.\n";
}
        
         if (errorMessage.isEmpty()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Des champs invalides");
            alert.setHeaderText("Veuillez corriger les champs invalides");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
     }
   
    }  



