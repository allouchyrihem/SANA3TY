/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.dao1.CommentDao;
import edu.esprit.entity.Comment;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author nada
 */
public class ModifierCommentController {

    @FXML
    private Pane BoutonValiderC;
    @FXML
    private Button btnC;
    @FXML
    private TextField descriptionC;
    @FXML
    private ImageView imageView;
    @FXML
    private ImageView imageView1;
    @FXML
    private Button updateeventbuttonid;
    private Stage dialogStage;
    private Comment comments;
    private CommentDao cDao;

     public void initialize() {
        CommentDao CommentDao = new CommentDao(); //To change body of generated methods, choose Tools | Templates.
    }
  public void setDialogueStage(Stage dialogStage){
    this.dialogStage = dialogStage;
}    
public void setComment(Comment comments){
   this.comments = comments;
    descriptionC.setText(comments.getDescription());
   


}
     CommentDao CommentDao = new CommentDao();
    @FXML
private void Updateeventbtn(ActionEvent event) {
      if (isInputValid()) {
    String description = descriptionC.getText();

    Comment c = new Comment(comments.getId(), description);
    
    CommentDao.update(c);
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Message");
    alert.setHeaderText("Événement modifié avec succès!");
    alert.showAndWait();
}
}
    
     private boolean isInputValid() {
        String errorMessage = "";
         if (descriptionC.getText() == null || descriptionC.getText().isEmpty()) {
            errorMessage += "ne laisser pas ce champ vide.\n";
        }
         if (descriptionC.getText().length() < 2 || descriptionC.getText().length() > 50) {
    errorMessage += "vous  devez ajouter entre  entre 2 et 50 caractères.\n";
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
