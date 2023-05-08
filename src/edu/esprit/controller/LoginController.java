/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.dao.UserDao;
import edu.esprit.entity.User;
import edu.esprit.test.ConnexionBD;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Fattouma PC
 */
public class LoginController implements Initializable {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField pwdField;

    @FXML
    private Button loginBtn;

    
    @FXML
    private Button resetPwdBtn;
    
    
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

    /**
     * Initializes the controller class.
     */
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
     

        resetPwdBtn.setOnAction(event -> {
            try {
                System.out.println("done");
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/ResetPwdEmailCheck.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(SignUpClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        loginBtn.setOnAction(event -> {
            UserDao udao = UserDao.getInstance();
            User user = udao.findUserByEmail(emailField.getText().trim());
            if (user == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Aucun compte ne correspond à l'adresse mail fournie");
                alert.show();
            } else {
                try {
                    System.out.println(user.getPassword()+"***"+pwdField.getText().trim());
                    if (checkPassword(user.getPassword())==true) {
                        
                        if (user.getStatus() == true) {
                            ConnexionBD app = new ConnexionBD();
                            app.setConnectedUser(user);
                            this.renderUser(user, (Stage) loginBtn.getScene().getWindow());
                            emailField.setText("");
                            pwdField.setText("");
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Information Dialog");
                            alert.setHeaderText(null);
                            alert.setContentText("Votre compte n'est pas valide");
                            alert.show();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Mot de passe incorrecte");
                        alert.show();
                    }
                } catch (NoSuchAlgorithmException e) {
                }
            }
        });
    }

    public static boolean isValidEmailFormat(String email) {
        // Regular expression for email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pattern.matcher(email).matches();
    }

    public boolean checkPassword(String userPassword) throws NoSuchAlgorithmException {
        UserDao u = UserDao.getInstance();
        String givenpwd = u.hashPassword(pwdField.getText().trim());
        System.out.println(userPassword.equals(givenpwd));
        return userPassword.equals(givenpwd);
    }

    public void renderUser(User user, Stage currentStage) {
        System.out.println(user.toString());
        if (user.checkRole("ROLE_ADMIN") || (user.getEmail().compareTo("nada@gmail.com"))==0) {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/Users.fxml"));
                currentStage.setScene(new Scene(page1));
            } catch (IOException ex) {
                Logger.getLogger(SignUpClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (user.checkRole("ROLE_CLIENT")) {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/Boutique.fxml"));
                currentStage.setScene(new Scene(page1));
            } catch (IOException ex) {
                Logger.getLogger(SignUpClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/edu/esprit/view/Dashboard.fxml"));
                currentStage.setScene(new Scene(page1));
            } catch (IOException ex) {
                Logger.getLogger(SignUpClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
