/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entity;

import java.time.LocalDate;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import edu.esprit.entity.Product;

/**

/**
 *
 * @author nada
 */
 


public class Comment {
    private int id;
    private String description;
    private LocalDate date;
@ManyToOne
    @JoinColumn(name = "product_id")
     private Product product;

    public Comment() {
    }

    public Comment(String description,Product product) {
        this.description = description;
        this.product = product;
    }

    
    public Comment(int id, String description, LocalDate date) {
        this.id = id;
        this.description = description;
        this.date = date;
    }

    public Comment(int id, String description) {
        this.id = id;
        this.description = description;
    }

   

    public Comment(int id) {
        this.id = id;
    }

   

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", description=" + description + ", date=" + date + ", product=" + product + '}';
    }

  
}

   
    

    