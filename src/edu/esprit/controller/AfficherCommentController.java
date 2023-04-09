/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.dao1.CommentDao;
import edu.esprit.entity.Comment;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AfficherCommentController implements Initializable {
@FXML
    private TableView<Comment> categoryTable;
    @FXML
    private TableColumn<Comment, Integer> id;
    @FXML
    private TableColumn<Comment, String> description;
    private ListData1 listdata = new ListData1();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         // TODO
        categoryTable.setItems(listdata.getcomments());
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        
    }
    @FXML
        public void update(javafx.scene.input.MouseEvent event) {

        CommentDao cdao=new CommentDao();
        Comment p=new Comment();
        p=categoryTable.getSelectionModel().getSelectedItem();
        p.setDescription(categoryTable.getSelectionModel().getSelectedItem().getDescription());
        cdao.update(p);
              
        
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

    @FXML
    private void EXIT(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        
    }
 /* public void goToHomePage(ActionEvent event) throws IOException {
    // Load the home page FXML file
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
    Parent homePageParent = loader.load();
    Scene homePageScene = new Scene(homePageParent);
    
    // Get the current stage and set the new scene
    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    currentStage.setScene(homePageScene);
}
*/
    
    }    
    

