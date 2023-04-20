/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entity;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.time.LocalDate;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
/**
 *
 * @author HP
 */
public class Commande {
    private SimpleIntegerProperty id;
    private SimpleStringProperty adresse;
    private SimpleStringProperty description;
    private SimpleStringProperty etat;
    private SimpleFloatProperty totale;
    private LocalDate datecmd;
    private ObservableList<ProductCmd> produits;
 
        public static ObservableList<Commande> getCommandes() {
        ObservableList<Commande> commandes = FXCollections.observableArrayList();
        // retrieve commandes from database or other data source
        // add each Commande object to the commandes list
        return commandes;
    }

    public Commande() {
    this.id = new SimpleIntegerProperty(0);
    this.adresse = new SimpleStringProperty("");
    this.description = new SimpleStringProperty("");
    this.etat = new SimpleStringProperty("");
    this.totale = new SimpleFloatProperty(0.0f);
    this.datecmd = null;
    this.produits = FXCollections.observableArrayList();
}

    public ObservableList<ProductCmd> getProducts() {
        return produits;
    }
    public Commande(int id, String adresse, String description, String etat, float totale) {
        this.id = new SimpleIntegerProperty(id);
        this.adresse = new SimpleStringProperty(adresse);
        this.description = new SimpleStringProperty(description);
        this.etat = new SimpleStringProperty(etat);
        this.totale = new SimpleFloatProperty(totale);
  
    }

    public Commande(String adresse, String description,String etat) {
        this.adresse = new SimpleStringProperty(adresse);
        this.description = new SimpleStringProperty(description);
        this.etat = new SimpleStringProperty(etat);
        

    }
    public Commande(String adresse, String description,String etat,Float totale,LocalDate datecmd) {
        this.adresse = new SimpleStringProperty(adresse);
        this.description = new SimpleStringProperty(description);
        this.etat = new SimpleStringProperty(etat);
        this.totale = new SimpleFloatProperty(totale);
        this.datecmd = datecmd;
        
    }
    public Commande(String adresse, String description,String etat,Float totale,LocalDate datecmd,ObservableList<ProductCmd> products) {
        this.adresse = new SimpleStringProperty(adresse);
        this.description = new SimpleStringProperty(description);
        this.etat = new SimpleStringProperty(etat);
        this.totale = new SimpleFloatProperty(totale);
        this.datecmd = datecmd;
        this.produits = products;
    }
    public ObservableList<ProductCmd> getCommandeProducts() {
        return produits;
    }

    public void setCommandeProducts(ObservableList<ProductCmd> commandeProducts) {
        this.produits = commandeProducts;
    }
    

public Commande(String adresse, String description) {
        this.adresse = new SimpleStringProperty(adresse);
        this.description = new SimpleStringProperty(description);}
public Commande(int id) {
        this.id = new SimpleIntegerProperty(id);
       }
        
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }
   
    public String getAdresse() {
        return adresse.get();
    }

    public void setAdresse(String adresse) {
        this.adresse = new SimpleStringProperty(adresse);
    }
    public Float getTotale() {
        return totale.get();
    }
public void setDatecmd(LocalDate datecmd) {
        this.datecmd = datecmd;
    }

    public LocalDate getDatecmd() {
        return datecmd;
    }
    public void setTotale(float totale) {
        this.totale = new SimpleFloatProperty(totale);
    }
    public String getEtat() {
        return etat.get();
    }

    public void setEtat(String etat) {
        this.etat = new SimpleStringProperty(etat);
    }
     
    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description = new SimpleStringProperty(description);
    }
    public SimpleStringProperty getDescriptionProperty(){
        return description;
    }
    public SimpleStringProperty getAdresseProperty(){
        return adresse;
    }
    public SimpleStringProperty getEtatProperty(){
        return etat;
    }
    public SimpleFloatProperty getTotaleProperty(){
        return totale;
    }
    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", adresse=" + adresse + ", description="+description+"etat"+etat+"totale"+totale +"datecmd"+datecmd+'}';
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
        final Commande other = (Commande) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
