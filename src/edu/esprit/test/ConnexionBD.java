/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.test;

import edu.esprit.entity.Product;
import edu.esprit.entity.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class ConnexionBD extends Application {
    public static User connectedUser = null ;
    public static String email = null;
    private Stage primaryStage;
    private Parent parentPage;
     public  List<Product> panier = new ArrayList<>();   
    @Override
    public void start(Stage primaryStage) throws IOException {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Sana3ty");
        parentPage = FXMLLoader.load(getClass().getResource("/edu/esprit/view/Accueil1.fxml"));
        Scene scene = new Scene(parentPage);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
panier.clear();
    }
    public void ajouterProduit(Product p){
        panier.add(p);
    }
    public List<Product> contenuPanier(){
        
       return panier;
    }
   
public void setConnectedUser(User user){
        this.connectedUser= user;  
    }
    
    public void signOut() throws IOException{
        this.connectedUser = null;
        Stage s = new Stage();
        this.start(s);
         
    }

    public static void setEmail(String email) {
        ConnexionBD.email = email;
    }

    public static String getEmail() {
        return email;
    }
    
    
   public User getConnectedUser(){
       return this.connectedUser;
   }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
