/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.dao.UserDao;
import edu.esprit.entity.User;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Fattouma PC
 */
public class SignUpVendeurController implements Initializable {
        @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField  passField;

    @FXML
    private PasswordField  confPassField;

    @FXML
    private TextField addField;

    @FXML
    private TextArea descField;

    @FXML
    private Button submitBtn;

    
   
    @FXML
    private ImageView logoPicture;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField search;
    @FXML
    private Button buttonSearch;
    @FXML
    private Hyperlink acceuil;

    @FXML
    private Hyperlink boutique;

    @FXML
    private Hyperlink details;

    @FXML
    private Hyperlink reclamation;

    @FXML
    private Hyperlink seConnecter;

    @FXML
    private Hyperlink sinscrire;

    @FXML
    private Hyperlink evenement;
    private Button addImage;
    @FXML
    private ImageView ImageView;
    private File f = null;
    List<String> lstFile;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
        sinscrire.setOnAction(e -> {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/SignUp.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
});
        seConnecter.setOnAction(e -> {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/Login.fxml"));
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
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/AfficherEventClient.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Accueil1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
});
    acceuil.setOnAction(e -> {
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
     
          submitBtn.setOnAction(event -> {
              
        
           if (nameField.getText().trim().length() < 3 || emailField.getText().trim().length() < 3
             || passField.getText().trim().length() < 8 || confPassField.getText().trim().length() < 8
             || descField.getText().trim().length() < 3 || addField.getText().trim().length() < 3) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs avec au moins 3 caracteres.");
                alert.show();
            } else if (!passField.getText().trim().equals(confPassField.getText().trim())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Les mots de passe saisis ne correspondent pas.");
                alert.show();
            }else if(!isValidEmailFormat(emailField.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("L'email fournit n'est pas valide.");
                alert.show();
            }else if(verifMailUser(emailField.getText())==false){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Un compte existe déjà avec l'adresse mail fournie");
                alert.show();
            }
            else{
                try {
                User u = new User(nameField.getText(), emailField.getText(), passField.getText(),addField.getText(),descField.getText(),imageEncoderDecoder(f.getAbsolutePath()));
                UserDao pdao = UserDao.getInstance();
                pdao.insertVendeur(u);
                 } catch (IOException ex) {
                Logger.getLogger(SignUpVendeurController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Personne insérée avec succés!");
                alert.show();
                nameField.setText("");
                emailField.setText("");
                passField.setText("");
                confPassField.setText("");
                addImage.setText("");
        ImageView.setImage(null);
                addField.setText("");
                descField.setText("");
            
        };
     
          });
     
        lstFile = new ArrayList<>();
        lstFile.add("*.png");
        lstFile.add("*.PNG");
        lstFile.add("*.jpg");
        lstFile.add("*.JPEG");
   }
    public static boolean isValidEmailFormat(String email) {
    // Regular expression for email validation
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                         "[a-zA-Z0-9_+&*-]+)*@" +
                         "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                         "A-Z]{2,7}$";
    Pattern pattern = Pattern.compile(emailRegex);
    if (email == null) {
        return false;
    }
    return pattern.matcher(email).matches();
}
    
    
    public static boolean verifMailUser(String email){
        UserDao pdao = UserDao.getInstance();
        if(pdao.findUserByEmail(email)==null){
            return true;
        }
        return false;
    }
   public String imageEncoderDecoder(String path) throws IOException {

    // read image from file
    FileInputStream stream = new FileInputStream(path);

    // get byte array from image stream
    int bufLength = 2048;
    byte[] buffer = new byte[2048];
    byte[] data;

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    int readLength;
    while ((readLength = stream.read(buffer, 0, bufLength)) != -1) {
        out.write(buffer, 0, readLength);
    }

    data = out.toByteArray();
    String imageString = Base64.getEncoder().withoutPadding().encodeToString(data);

    out.close();
    stream.close();
    return imageString;
}
  @FXML
    void SingleImage(ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpg and png file",lstFile));
        f = fc.showOpenDialog(null);
        if (f!= null ){
            addImage.setText(f.getName());
            ImageView.setImage(new Image("file:" + f));
            
        }
    }
}