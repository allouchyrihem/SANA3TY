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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    private TableView<Comment> categoryTable;
    @FXML
    private TableColumn<Comment, String> description;
    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */

    /**
     * Initializes the controller class.
     */
    private ListData1 listdata = new ListData1();
    @FXML
    private Button supprimer;
    @FXML
    private Button updatebuttonid;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          categoryTable.setItems(listdata.getcomments());
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        
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
    String comment = descriptionC.getText();

    // Filtrer les gros mots dans le commentaire
    String filteredComment = filterComments(comment);

    // Vérifier si le commentaire filtré est identique au commentaire d'origine
    if (!comment.equals(filteredComment)) {
        errorMessage += "Le commentaire que vous avez soumis contient des mots inappropriés qui ne respectent pas les règles de langage approprié. Veuillez reformuler votre commentaire sans utiliser de langage vulgaire ou inapproprié. Merci de votre compréhension.\n";
    }

    if (comment == null || comment.isEmpty()) {
        errorMessage += "Ne laisser pas ce champ vide.\n";
    }
    if (comment.length() < 2 || comment.length() > 50) {
        errorMessage += "Vous devez ajouter entre 2 et 50 caractères.\n";
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

private String filterComments(String comment) {
    String filteredComment = comment.replaceAll("(?i)\\b(gros mot 1|gros mot 2|gros mot 3|gros mot n)\\b", "");
    return filteredComment;
}


      public void delete(){
    CommentDao cdao =new CommentDao();
    cdao.delete(categoryTable.getSelectionModel().getSelectedItem().getId());
    System.out.println(categoryTable.getSelectionModel().getSelectedItem().getId());
    }
@FXML
    public void supprimer(javafx.scene.input.MouseEvent event) {
   delete();
   categoryTable.getItems().removeAll(categoryTable.getSelectionModel().getSelectedItem());
   System.out.println(categoryTable);
          
    }
   
 
private CommentDao evd= new CommentDao() ;
    private ObservableList<Comment> evnmdata = FXCollections.observableArrayList();
  
@FXML
void updatebtn(ActionEvent event) {
   

    // Get selected Event from the table
    Comment selectedComment = categoryTable.getSelectionModel().getSelectedItem();
   CommentDao commentsdao = new CommentDao();
    if (selectedComment != null) {
        try {
            // Create the FXMLLoader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/view/modifierComment.fxml"));

            // Load the UpdateEvents view
            Parent root = loader.load();

            // Get the UpdateEvents controller
            ModifierCommentController updateCommentsController = loader.getController();

            // Pass the selected Event to the controller
            updateCommentsController.setComment(selectedComment);

            // Create a new scene and set it on the stage
            Scene updateEventsScene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(updateEventsScene);
            stage.show();
            
            categoryTable.refresh();
             categoryTable.getSelectionModel().clearSelection();
        evnmdata.clear();
    evnmdata.addAll(commentsdao.displayAll());
    categoryTable.setItems(evnmdata);
            

           /* // Refresh the table after updating the Event
            eventsdata.setAll(evd.displayAll()); */

        } catch (IOException e) {
            e.printStackTrace();
        }
        
       
    }
}
}