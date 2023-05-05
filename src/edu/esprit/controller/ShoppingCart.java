/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.entity.CommandeProduit;
import edu.esprit.entity.Product;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 *
 * @author HP
 */

public class ShoppingCart {
    private List<Product> products = new ArrayList<>();

    private Map<Product, Integer> items;
private static ShoppingCart instance = null;
    public ShoppingCart() {
        items = new HashMap<>();
    }
 public void addProduct(Product product) {
        this.products.add(product);
    }

    public void addItem(Product product, int quantity) {
        if (items.containsKey(product)) {
            quantity += items.get(product);
        }
        items.put(product, quantity);
    }

    public void removeItem(Product product) {
        items.remove(product);
    }

    public void updateItemQuantity(Product product, int quantity) {
        items.put(product, quantity);
    }
    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }

    
    public Map<Product, Integer> getItems() {
        return items;
    }
}
