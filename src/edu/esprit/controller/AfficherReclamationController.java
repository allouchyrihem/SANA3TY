/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import static com.itextpdf.text.pdf.BidiOrder.PDF;
import com.itextpdf.text.pdf.PdfWriter;
import edu.esprit.dao.ReclamationDao;
import edu.esprit.entity.Product;
import edu.esprit.entity.Reclamation;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.Predicate;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class AfficherReclamationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Reclamation> productTable;
    @FXML
    private TableColumn<Reclamation, String> name;
    @FXML
    private TableColumn<Reclamation, String> description;
    
         @FXML
    private Button supprimer;
   
    private ListData1 listdata = new ListData1();
    @FXML
    private Button createPDFButton;
    @FXML
    private TextField searchP;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        productTable.setItems(listdata.getReclamations());
        name.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
         // Disable the button when no row is selected
        
   searchP.textProperty().addListener((observable, oldValue, newValue) -> {
            // Create filter Predicate
            Predicate<Reclamation> filter = (Reclamation item) -> {
                // Implement filter logic here
                String query = newValue.toLowerCase();
                return item.getSujet().toLowerCase().contains(query)||item.getDescription().toLowerCase().contains(query);
            };

            // Apply filter to data and update TableView
            productTable.setItems(listdata.getReclamations().filtered(filter));
        });
 
     
    }
  

 
@FXML
private void createPDFButtonClicked(ActionEvent event) throws FileNotFoundException, BadElementException, IOException {
    Reclamation selectedReclamation = productTable.getSelectionModel().getSelectedItem();
    if (selectedReclamation != null) {
        PdfCreator pdfCreator = new PdfCreator();
        pdfCreator.createPDF(selectedReclamation);
        // Show a message to indicate success
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("PDF Created");
        alert.setHeaderText(null);
        alert.setContentText("The PDF file has been created successfully.");
        alert.showAndWait();
    } else {
        // Show an error message if no row is selected
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("No Selection");
        alert.setHeaderText("No Reclamation Selected");
        alert.setContentText("Please select a reclamation in the table.");
        alert.showAndWait();
    }
}

        public void update(javafx.scene.input.MouseEvent event) {

        ReclamationDao pdao=new ReclamationDao();
        Reclamation p=new Reclamation();
        p=productTable.getSelectionModel().getSelectedItem();
        p.setSujet(productTable.getSelectionModel().getSelectedItem().getSujet());
        p.setDescription(productTable.getSelectionModel().getSelectedItem().getDescription());
        p.setDate(productTable.getSelectionModel().getSelectedItem().getDate());
        
        pdao.update(p);
              
        
    }
    public void delete(){
    ReclamationDao pdao =new ReclamationDao();
    pdao.delete(productTable.getSelectionModel().getSelectedItem().getId());
    System.out.println(productTable.getSelectionModel().getSelectedItem().getId());
    }
@FXML
    public void supprimer(javafx.scene.input.MouseEvent event) {
   delete();
   productTable.getItems().removeAll(productTable.getSelectionModel().getSelectedItem());
   System.out.println(productTable);
          
    }
/*@FXML
public void PDF(javafx.scene.input.MouseEvent event) throws FileNotFoundException, DocumentException {
    productTable.getSelectionModel().select(productTable.getSelectionModel().getSelectedItem());
}
*/ 
   


    @FXML
    private void EXIT(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
  
    }