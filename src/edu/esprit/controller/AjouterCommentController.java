/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.dao1.CommentDao;
import edu.esprit.dao.ReclamationDao;
import edu.esprit.entity.Category;
import edu.esprit.entity.Comment;
import edu.esprit.entity.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjouterCommentController implements Initializable {
  @FXML
    private Button btnC;
  
    @FXML
    private TextField descriptionC;
    @FXML
    private Pane BoutonValiderC;
    @FXML
    private ImageView imageView;
    @FXML
    private ImageView imageView1;
    @FXML
    private ListView<Comment> listcomment;
    
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
       
         btnC.setOnAction((ActionEvent event) -> {
            
             if ( isInputValid() ){
                 Comment p = new Comment( descriptionC.getText());
            CommentDao cdao = CommentDao.getInstance();
            cdao.insert(p);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Comment insérée avec succés!");
        alert.show();
        descriptionC.setText("");
}
        });
         
        
        
    

    }    
    private boolean isInputValid() {
        String errorMessage = "";
         if (descriptionC.getText() == null || descriptionC.getText().isEmpty()) {
            errorMessage += "ne laisser pas ce champ vide.\n";
        }
         if (descriptionC.getText().length() < 2 || descriptionC.getText().length() > 20) {
    errorMessage += "vous  devez ajouter entre  entre 2 et 20 caractères.\n";
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