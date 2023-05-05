/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ProfileController implements Initializable {

    @FXML
    private ImageView imageView;
    @FXML
    private Text userNAme;
    @FXML
    private Button backbtn;
    @FXML
    private Text name;
    @FXML
    private Text email;
    @FXML
    private Text description;
    @FXML
    private Button updateProfile;
    @FXML
    private Text name1;
    @FXML
    private Text name11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
