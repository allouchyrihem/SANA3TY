/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entity;

import java.time.LocalDate;

/**
 *
 * @author nada
 */


public class Comment {
    private int id;
    private String description;
    private LocalDate date;

    public Comment() {
    }

    public Comment(String description) {
        this.description = description;
    }

    
    public Comment(int id, String description, LocalDate date) {
        this.id = id;
        this.description = description;
        this.date = date;
    }

    public Comment(int id, String description) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}

   
    

    
