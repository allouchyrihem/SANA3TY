/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entity;

import java.time.LocalDate;

/**
 *
 * @author rouao
 */
public class PromotionTotale {
    private int id;

    private String name;
    private float value;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    

    public PromotionTotale() {
        
    }

    public PromotionTotale(String name, float value, LocalDate dateDebut, LocalDate dateFin) {
        this.name = name;
        this.value = value;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
    
    

    public PromotionTotale(int id, String name, float value, LocalDate dateDebut, LocalDate dateFin) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "PromotionTotale{" + "id=" + id + ", name=" + name + ", value=" + value + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + '}';
    }
    
    
    
    
    
}