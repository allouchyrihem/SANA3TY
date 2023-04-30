/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.entity.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HP
 */
 public class ShoppingCart {
     private ObservableList<Product> products;

    public ShoppingCart() {
        products = FXCollections.observableArrayList();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public ObservableList<Product> getProducts() {
        return products;
    }

    void clear() {
          products.clear();
    
    System.out.println("Le panier a été vidé.");
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
 
  /*  private ObservableList<Product> products = FXCollections.observableArrayList();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public ObservableList<Product> getProducts() {
        return products;
    }
}*/