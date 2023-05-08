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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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

    @FXML
    private Button submitBtn;

    @FXML
    private PasswordField  confMdpField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
