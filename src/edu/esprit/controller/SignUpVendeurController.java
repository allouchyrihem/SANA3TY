/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import com.pidev.dao.UserDao;
import com.pidev.entity.User;
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
    private Button connectBtn;
   
    @FXML
    private Button uploadPicture;
    private File f = null;

    @FXML
    private ImageView logoPicture;
    List<String> lstFile;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connectBtn.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/pidev/view/Login.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(SignUpClientController.class.getName()).log(Level.SEVERE, null, ex);
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
            uploadPicture.setText(f.getName());
            logoPicture.setImage(new Image("file:" + f));
            
        }
    }
}