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

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


/**
 *
 * @author rouao
 */
public class EventCardController {
    
    @FXML
    private StackPane qrCodePane;
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
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = events.getLink();
        int width = 180;
        int height = 180;
        String fileType = "png";

        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            System.out.println("Success...");

        } catch (WriterException ex) {
            Logger.getLogger(EventCardController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ImageView qrView = new ImageView();
        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        qrCodePane.getChildren().add(qrView);
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
