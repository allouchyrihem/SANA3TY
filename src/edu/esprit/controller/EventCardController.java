/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.entity.Events;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author rouao
 */
public class EventCardController {

     @FXML
    private Label DonAmount;

    @FXML
    private Label DonDesc;

    @FXML
    private Label DonExp;

    @FXML
    private Label DonTitle;

    @FXML
    private VBox box;
    
    private String[] colors = {"E8B298", "EDCC8B", "BDD1C5", "A26360"};
    
    public void setData(Events events) {
        DonTitle.setText(events.getName());
        DonDesc.setText(events.getAdresse());
        DonAmount.setText(String.valueOf(events.getDescription()));
        DonExp.setText(String.valueOf(events.getDate().toString()));




   /* public void TestEmail(Dons dons){
        LocalDate currentDate = LocalDate.now();
        LocalDate expirationDate = dons.getDate_expiration();
        long daysUntilExpiration = currentDate.until(expirationDate).getDays();
        if (daysUntilExpiration > 2) {
            System.out.println("il y a");
            EmailService emailService = new EmailService();
            emailService.envoyer("aimen.majoul@gmail.com");
        }
    } */


  /*  LocalDate currentDate = LocalDate.now();&

    LocalDate expirationDate = Dons.getDate_expiration();
    long daysUntilExpiration = currentDate.until(expirationDate).getDays(); */
        box.setStyle("-fx-background-color: #" + colors[(int) (Math.random() * colors.length)] + ";" +
                " -fx-background-radius: 15;" +
                "-fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0.1), 10, 0 , 0 ,10);");
    }
    
}
