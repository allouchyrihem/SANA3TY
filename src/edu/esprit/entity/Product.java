/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author asus
 */
public class Product {
    
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty description;   
    private SimpleFloatProperty price;
    private SimpleStringProperty stock;
    private SimpleStringProperty image;

     public static ObservableList<Product> getProducts() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        // retrieve products from database or other data source
        // add each Product object to the products list
        return products;
    }
    public Product() {
    }

 
    

    public Product(int id, String name, String description, Float price, String stock, String image) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.price = new SimpleFloatProperty(price);
        this.stock = new SimpleStringProperty(stock);
        this.image = new SimpleStringProperty(image);
         }

    public Product(String name, String description, Float price, String stock, String image) {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.price = new SimpleFloatProperty(price);
        this.stock = new SimpleStringProperty(stock);
        this.image = new SimpleStringProperty(image);
       
    }
    public Product(int id,String name) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
    }
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description = new SimpleStringProperty(description);
    }

    public Float getPrice() {
        return price.get();
    }

    public void setPrice(Float price) {
        this.price = new SimpleFloatProperty(price);
    }

    public String getStock() {
        return stock.get();
    }

    public void setStock(String stock) {
        this.stock = new SimpleStringProperty(stock);
    }

    public String getImage() {
        return image.get();
    }

    public void setImage(String image) {
        this.image = new SimpleStringProperty(image);
    }


    public SimpleStringProperty getNameProperty(){
        return name;
    }
    public SimpleStringProperty getDescriptionProperty(){
        return description;
    }
    public SimpleFloatProperty getPriceProperty(){
        return price;
    }
    public SimpleStringProperty getStockProperty(){
        return stock;
    }
    
    public SimpleStringProperty getImageProperty(){
        return image;
    }

    
    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", stock=" + stock + ", image=" + image +'}';
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }


    
    
    
}

