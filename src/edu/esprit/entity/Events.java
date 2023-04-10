package edu.esprit.entity;

import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;

public class Events {
    private int id;

    private String name;
    private String adresse;
    private LocalDate date;
    private String description;
    private String link;

    public Events() {

    }

    public Events(String name, String adresse) {
        this.name = name;
        this.adresse = name;
    }

    public Events(int id, String name, String adresse, LocalDate date, String description, String link) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        this.date = date;
        this.description = description;
        this.link = link;
    }
    

    public Events(int id, int user_id, String name, String adresse, LocalDate date, String description, String link) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        this.date = date;
        this.description = description;
        this.link = link;
    }
    

    public Events(String name, String adresse, LocalDate date, String description, String link) {
        this.name = name;
        this.adresse = adresse;
        this.date = date;
        this.description = description;
        this.link = link;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
               
                ", name='" + name + '\'' +
                ", adresse='" + adresse + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    public String getNameProperty(){
        return name;
    }
    public String getAdresseProperty(){
        return adresse;
    }
}
