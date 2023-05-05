/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//import com.itextpdf.text.Cell;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;


import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.util.Date;
import edu.esprit.dao.CmdDao;
import edu.esprit.dao.ProductDao;
import edu.esprit.entity.Commande;
import edu.esprit.entity.Product;
import edu.esprit.entity.ProductCmd;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AfficherCmdController implements Initializable {

    @FXML
    private TableView<Commande> commandeTable;
    @FXML
    private TableColumn<Commande, String> adresse;
    @FXML
    private TableColumn<Commande, String> description;
    @FXML
    private TableColumn<Commande, String> etat;
     @FXML
    private TableColumn<Commande, String> produit;
    @FXML
    private TableColumn<Commande, String> quantite;
@FXML
private TableColumn<Commande, Void> facture;

    @FXML
    private Button supprimer;
    @FXML
    private Button updateU;
    private Button back;
    @FXML
    private Button exit;
    private ListData listdata = new ListData();
    @FXML
    private BorderPane bp;
    @FXML
    private TableColumn<Commande, Float> totale;
    @FXML
    private TableColumn<Commande, Date> datecmd;
    @FXML
    private ComboBox<String> etatC;
    @FXML
    private TableColumn<Commande, String > client;
    @FXML
    private Hyperlink dashboard;
    @FXML
    private Hyperlink product;
    @FXML
    private Hyperlink category;
    @FXML
    private Hyperlink accueil;
    @FXML
    private Hyperlink evenement;
    @FXML
    private Hyperlink commande;
  
    /**
     * Initializes the controller class.
     */
    private void sendEmail(String toEmail, String subject, String messageBody) throws MessagingException {
    String username = "sana3ty@gmail.com"; // your email address
    String password = "qdxerugmjpbamfhl"; // your email password

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com"); // or your SMTP server hostname
    props.put("mail.smtp.port", "587"); // or your SMTP server port

    Session session = Session.getInstance(props, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });

    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(username));
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
    message.setSubject(subject);
    message.setText(messageBody);

    Transport.send(message);
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      TableColumn<Commande, Void> emailColumn = new TableColumn<>("Email");
   emailColumn.setCellFactory(column -> {
    return new TableCell<Commande, Void>() {
        final Button email = new Button("Envoyer Email");

        {
            email.setOnAction(event -> {
            Commande commande = getTableView().getItems().get(getIndex());
                // code to send email
                String toEmail = "salma.beddakhlia17@gmail.com"; // replace with the recipient's email address
                String subject = "Commande de SANA3TY";
                String messageBody = "votre commande a été livré avec succés, merci pour votre confiance";
                try {
                    sendEmail(toEmail, subject, messageBody);
                    System.out.println("Email sent successfully");
                } catch (MessagingException ex) {
                    System.out.println("Failed to send email");
                    ex.printStackTrace();
                }
            });
        }
        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(email);
            }
        }
    };
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
       commande.setOnAction(e -> {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/AfficherCommande.fxml"));
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
     
      evenement.setOnAction(e -> {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/AfficherEvents.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
});
// set the email column to the table
    commandeTable.getColumns().add(emailColumn);
        etatC.setItems(FXCollections.observableArrayList("en attente","en cours","livré"));
         
        facture.setCellFactory(column -> {
    return new TableCell<Commande, Void>() {
        private final Button button = new Button("Télécharger");
        
        {
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Commande commande = getTableView().getItems().get(getIndex());
                    
                    // Generate the PDF invoice
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    try {
                        Document document = new Document();
                        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
                        document.open();
                        
                        // Add the invoice header
                        document.add(new Paragraph("Facture #" + commande.getId()));
                        document.add(new Paragraph("Date: " + commande.getDatecmd().toString()));
                        document.add(new Paragraph("Adresse: " + commande.getAdresse()));
                        document.add(new Paragraph("Client: foulen ben foulen"));
                        
                        // Add the invoice items
                        PdfPTable table = new PdfPTable(3);
                        table.addCell("Produit");
                        //table.addCell("Description");
                        table.addCell("Quantité");
                        table.addCell("Prix");
                        
                        for (ProductCmd commandeProduct : commande.getCommandeProducts()) {
                            table.addCell(commandeProduct.getProduct().getName());
                            //table.addCell(commandeProduct.getProduct().getDescription());
                            table.addCell(String.valueOf(commande.getQuantite()));
                            table.addCell(String.valueOf(commande.getTotale()));
                        }
                        /*  PdfPTable table = new PdfPTable(3); // Creates a table with 3 columns
                        table.setWidthPercentage(100); // Sets the width of the table to 100% of the page
                        
                        // Adds the column headers to the table
                        table.addCell("Header 1");
                        table.addCell("Header 2");
                        table.addCell("Header 3");
                        
                        // Adds rows to the table (can be done in a loop to add multiple rows)
                        table.addCell("Data 1");
                        table.addCell("Data 2");
                        table.addCell("Data 3");*/
                        
// Adds the table to the document


document.add(table);

// Add the invoice footer
// document.add(new Paragraph("Quantite: " + commande.getQuantite()));
document.add(new Paragraph("Total: " + commande.getTotale()));

document.close();
                    } catch (DocumentException ex) {
                        Logger.getLogger(AfficherCmdController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    // Download the PDF invoice
                    ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Save Facture");
                    fileChooser.setInitialFileName("facture_" + commande.getId() + ".pdf");
                    File file = fileChooser.showSaveDialog(button.getScene().getWindow());
                    if (file != null) {
                        OutputStream outputStream1 = null;
                        try {
                            outputStream1 = new FileOutputStream(file);
                            byte[] buffer = new byte[1024];
                            int length;
                            while ((length = inputStream.read(buffer)) > 0) {
                                outputStream1.write(buffer, 0, length);
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } finally {
                            if (outputStream1 != null) {
                                try {
                                    outputStream1.close();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                }
            });
        }
        
        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(button);
            }
        }
    };
});


        commandeTable.setItems(listdata.getcommandes());
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        totale.setCellValueFactory(new PropertyValueFactory<>("totale"));
        datecmd.setCellValueFactory(new PropertyValueFactory<>("datecmd"));
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        produit.setCellFactory(column -> {
    return new TableCell<Commande, String>() {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
            } else {
                Commande commande = getTableView().getItems().get(getIndex());
                ObservableList<ProductCmd> commandeProducts = commande.getCommandeProducts();
                String produits = "";
                for (ProductCmd commandeProduct : commandeProducts) {
                    produits += commandeProduct.getProduct().getName() + ", ";
                }
                setText(produits);
            }
        }
    };
});     client.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getName()));


   
    
    }    

private void updateCommandState() {
        CmdDao cmdDao =new CmdDao();

    Commande selectedCommande = commandeTable.getSelectionModel().getSelectedItem();
    if (selectedCommande == null) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("No Command Selected");
        alert.setHeaderText(null);
        alert.setContentText("Please select a command in the table.");
        alert.showAndWait();
    } else {
        TextInputDialog dialog = new TextInputDialog(selectedCommande.getEtat());
        dialog.setTitle("Update Command State");
        dialog.setHeaderText(null);
        dialog.setContentText("New state:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            selectedCommande.setEtat(result.get());
            cmdDao.updateCommandState(selectedCommande);
            // refresh the table to reflect the changes
            commandeTable.refresh();
            System.out.println("Command state updated.");
        }
    }
}
@FXML
private void update(MouseEvent event) {
    CmdDao pdao = CmdDao.getInstance();
    Commande p = commandeTable.getSelectionModel().getSelectedItem();
    String newEtat = etatC.getValue(); // get the new value from the ComboBox
    p.setEtat(newEtat); // update the etat property of the selected Commande
    pdao.updateCommandState(p); // call the update method in the CmdDao class
}


    public void delete(){
    CmdDao pdao =new CmdDao();
    pdao.delete(commandeTable.getSelectionModel().getSelectedItem().getId());
    System.out.println(commandeTable.getSelectionModel().getSelectedItem().getId());
    }
   @FXML
    public void supprimer(javafx.scene.input.MouseEvent event) {
   delete();
   commandeTable.getItems().removeAll(commandeTable.getSelectionModel().getSelectedItem());
   System.out.println(commandeTable);
          
    }

    @FXML
    public void EXIT(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
}