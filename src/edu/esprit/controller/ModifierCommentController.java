/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.dao1.CommentDao;
import edu.esprit.entity.Comment;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

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
    
     public void initialize() {
        CommentDao CommentDao = new CommentDao(); //To change body of generated methods, choose Tools | Templates.
    }
     public void setComment(Comment comments){
   // this.comments = comments;
    descriptionC.setText(comments.getDescription());
  


}
     
    @FXML
    private void UpdatComment() {
         
        String description = descriptionC.getText();
        
       // Comment event1 = new Comment(Comment.getId(),description);
        //CommentDao.update(event1);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("L'évenement est modifié avec succès");
        alert.showAndWait();
        
    }

    
    
}
