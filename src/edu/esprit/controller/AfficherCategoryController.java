/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.dao.CategoryDao;
import edu.esprit.dao.ProductDao;
import edu.esprit.entity.Category;
import edu.esprit.entity.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AfficherCategoryController implements Initializable {
@FXML
    private TableView<Category> categoryTable;
    @FXML
    private TableColumn<Category, String> name;
    @FXML
    private TableColumn<Category, String> description;
    private ListData listdata = new ListData();
    @FXML
    private Button back;
    @FXML
    private Button supprimer;
    @FXML
    private Button updateC;
    @FXML
    private Hyperlink dashboard;
    @FXML
    private Hyperlink product;
    @FXML
    private Hyperlink category;
    @FXML
    private Hyperlink accueil;
    
    private ObservableList<Category> catdata = FXCollections.observableArrayList();
    @FXML
    private TextField searchC;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         // TODO
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
    product.setOnAction(e -> {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/AfficherProduct.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
});
    category.setOnAction(e -> {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/AfficherCategory.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
});
         back.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/AjouterCategory.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
      
        categoryTable.setItems(listdata.getcategories());
        categoryTable.getSortOrder().add(name); // add the column to sort by
name.setSortType(TableColumn.SortType.ASCENDING); // set the sort type
categoryTable.sort(); 
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
           searchC.textProperty().addListener((observable, oldValue, newValue) -> {
            // Create filter Predicate
            Predicate<Category> filter = item -> {
                // Implement filter logic here
                String query = newValue.toLowerCase();
                return item.getName().toLowerCase().contains(query)||item.getDescription().toLowerCase().contains(query);
            };

            // Apply filter to data and update TableView
            categoryTable.setItems(listdata.getcategories().filtered(filter));
        });
    }
                 
        public void update(javafx.scene.input.MouseEvent event) {

        CategoryDao cdao=new CategoryDao();
        Category p=new Category();
        p=categoryTable.getSelectionModel().getSelectedItem();
        p.setName(categoryTable.getSelectionModel().getSelectedItem().getName());
        p.setDescription(categoryTable.getSelectionModel().getSelectedItem().getDescription());
        cdao.update(p);           
    }
    public void delete(){
    CategoryDao cdao =new CategoryDao();
    cdao.delete(categoryTable.getSelectionModel().getSelectedItem().getId());
    System.out.println(categoryTable.getSelectionModel().getSelectedItem().getId());
    }
@FXML
    public void supprimer(javafx.scene.input.MouseEvent event) {
   delete();
   categoryTable.getItems().removeAll(categoryTable.getSelectionModel().getSelectedItem());
   System.out.println(categoryTable);
          
    }

    private void EXIT(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void updatebtn(ActionEvent event) {
   

    // Get selected Event from the table
    Category selectedcategory = categoryTable.getSelectionModel().getSelectedItem();
    CategoryDao cdao = new CategoryDao();
    if (selectedcategory != null) {
        try {
            // Create the FXMLLoader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/esprit/view/UpdateCategory.fxml"));

            // Load the UpdateEvents view
            Parent root = loader.load();

            // Get the UpdateEvents controller
            UpdateCategoryController updatecategoriesController = loader.getController();

            // Pass the selected Event to the controller
            updatecategoriesController.setCategory(selectedcategory);

            // Create a new scene and set it on the stage
            Scene updatecategoryScene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(updatecategoryScene);
            stage.show();
            
            categoryTable.refresh();
             categoryTable.getSelectionModel().clearSelection();
        catdata.clear();
    catdata.addAll(cdao.displayAll());
    categoryTable.setItems(catdata);
            

           /* // Refresh the table after updating the Event
            eventsdata.setAll(evd.displayAll()); */

        } catch (IOException e) {
            e.printStackTrace();
        }
        
       
    }
}


    }    
   
    

