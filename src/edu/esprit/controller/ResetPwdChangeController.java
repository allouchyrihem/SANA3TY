/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;


import edu.esprit.dao.UserDao;
import edu.esprit.test.ConnexionBD;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Fattouma PC
 */
public class ResetPwdChangeController implements Initializable {

    @FXML
    private PasswordField  mdpField;

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
    private Button submitBtn;

    @FXML
    private PasswordField  confMdpField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        submitBtn.setOnAction(event -> {
            if (mdpField.getText().trim().length() < 8 || confMdpField.getText().trim().length() < 8) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs.");
                alert.show();
            } else if (!mdpField.getText().trim().equals(confMdpField.getText().trim())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Les mots de passe saisis ne correspondent pas.");
                alert.show();
            } else {
                UserDao pdao = UserDao.getInstance();
                ConnexionBD app = new ConnexionBD();
                pdao.resetPassword(app.getEmail(), mdpField.getText().trim());
                try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/Login.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(SignUpClientController.class.getName()).log(Level.SEVERE, null, ex);
                }

                mdpField.setText("");
                confMdpField.setText("");

            }
        });

    }
}
