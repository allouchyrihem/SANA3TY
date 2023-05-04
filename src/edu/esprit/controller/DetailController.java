/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.dao.CommentDao;
import edu.esprit.entity.Comment;
import edu.esprit.entity.Product;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class DetailController implements Initializable {
    private Product products;
    private Comment comments;
    
    @FXML
    private Text text1;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField search;
    @FXML
    private Hyperlink accueil;
    @FXML
    private Hyperlink boutique;
    @FXML
    private Hyperlink dashboard;
    @FXML
    private Pane rootContainer;
    @FXML
    private ImageView addImage;
    @FXML
    private Label name;
    @FXML
    private Label description;
    @FXML
    private Label price;
    @FXML
    private Label stock;
    @FXML
    private Button btnC;
    @FXML
    private TextField descriptionC;
    @FXML
    private TableView<Comment> categoryTable;
    @FXML
    private TableColumn<Comment, String> descriptionco;
    @FXML
    private Button supprimer;
    @FXML
    private Button updatebuttonid;
    private Button back;
    private ListData1 listdata = new ListData1();
    @FXML
    private Hyperlink reclamation;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
dashboard.setOnAction(e -> {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/Dashboard.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
});
    accueil.setOnAction(e -> {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/Accueil1.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
});
    boutique.setOnAction(e -> {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/Boutique.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
});   
        reclamation.setOnAction(e -> {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/AjouterReclamation.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
});
          
          /*ObservableList<Comment> cdata = FXCollections.observableArrayList();
                    CommentDao ccdao = CommentDao.getInstance();
                    int productId = this.products.getId();
          cdata.addAll(ccdao.displayAll((productId)));
          
          
        categoryTable.setItems(cdata);
        descriptionco.setCellValueFactory(new PropertyValueFactory<>("description"));*/
        
         btnC.setOnAction((ActionEvent event) -> {
            
             if ( isInputValid() ){
                 
                 
                 Comment p = new Comment(descriptionC.getText(),products);
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
           public void setProduct(Product products) throws IOException{
    this.products = products;
    name.setText(products.getName());
    description.setText(products.getDescription());
    price.setText(products.getPrice());
    stock.setText(products.getStock());
    addImage.setImage(decodeBase64Image(products.getImage()));
    
}
           
                    public void setComment(ObservableList<Comment> c) throws IOException{
                        this.categoryTable.setItems(c);
                        descriptionco.setCellValueFactory(new PropertyValueFactory<>("description"));
     
    
}
                    
           public static Image decodeBase64Image(String imageString) throws IOException {
    byte[] imageData = Base64.getDecoder().decode(imageString);
    ByteArrayInputStream stream = new ByteArrayInputStream(imageData);
    return new Image(stream);

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
   
 

  
@FXML
void updatebtn(ActionEvent event) {
   

    // Get selected Event from the table
    Comment selectedComment = categoryTable.getSelectionModel().getSelectedItem();
      ObservableList<Comment> evnmdata = FXCollections.observableArrayList();
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
            evnmdata.addAll(listdata.getcomments());
            //categoryTable.setItems(evnmdata);
        

           /* // Refresh the table after updating the Event
            eventsdata.setAll(evd.displayAll()); */

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    
    }
}

}