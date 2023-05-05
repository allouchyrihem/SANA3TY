/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.view;

import edu.esprit.controller.Session;
import edu.esprit.controller.ShoppingCart;
import edu.esprit.entity.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
/**
 * FXML Controller class
 *
 * @author HP
 */
public class ShoppingCartController implements Initializable {

    @FXML
    private VBox cartItems;
@FXML
    private ListView<String> cartList;
    
    private ShoppingCart shoppingCart;
    private List<Product> productsInCart = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         shoppingCart = (ShoppingCart) Session.getInstance().getAttribute("cart");
        if(shoppingCart != null) {
            List<String> cartItems = new ArrayList<>();
            for(Product p : shoppingCart.getItems().keySet()) {
                cartItems.add(p.getName() + " x " + shoppingCart.getItems().get(p));
            }
            ObservableList<String> cartItemsObs = FXCollections.observableArrayList(cartItems);
            cartList.setItems(cartItemsObs);
        }
    }

    public void addProductToCart(Product product) {
        // Check if the product is already in the cart
        for (Product p : productsInCart) {
            if (p.getId() == product.getId()) {
                // If the product is already in the cart, increase the quantity and update the label
               // p.setQuantity(p.getQuantit() + 1);
                Label label = (Label) cartItems.lookup("#product" + p.getId() + "Quantity");
                //label.setText("Quantity: " + p.getQuantity());
                return;
            }
        }

        // If the product is not in the cart, add it and create a new label
        productsInCart.add(product);
        Label label = new Label(product.getName() );
        label.setId("product" + product.getId() );
        cartItems.getChildren().add(label);
    }

    public void clearCart() {
        productsInCart.clear();
        cartItems.getChildren().clear();
    }

    public void checkout() {
        // TODO: Implement checkout functionality
    }
}
