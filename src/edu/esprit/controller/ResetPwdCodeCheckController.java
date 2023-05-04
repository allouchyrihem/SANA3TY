/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import com.mycompany.myapp.MyApplication;
import static com.pidev.controller.ResetPwdEmailCheckController.isValidEmailFormat;
import com.pidev.dao.UserDao;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Fattouma PC
 */
public class ResetPwdCodeCheckController implements Initializable {

      @FXML
    private TextField codeField;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField search;

    @FXML
    private Button buttonSearch;

    @FXML
    private Text Acceuil;

    @FXML
    private Text Boutique;

    @FXML
    private Text Details;

    @FXML
    private Text Contact;

    @FXML
    private Text Réclamation;

    @FXML
    private Text Seconnecter;

    @FXML
    private Text Sinscrire;

    @FXML
    private Button verifCodeBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserDao pdao = UserDao.getInstance();
        MyApplication app = new MyApplication();
      
       String code = pdao.findCode(app.getEmail());
       verifCodeBtn.setOnAction(event -> {
       
        if(code.compareTo(codeField.getText().trim())==0){
                try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/pidev/view/ResetPwdChange.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(SignUpClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("L'email fournit n'est pas valide.");
            alert.show();
        }
       });    
}}
