/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entity;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author nada
 */
public class Reclamation {
     private int id;
     private String sujet;
     private String description;
     private LocalDate date;

    public Reclamation() {
    }

    public Reclamation(String sujet, String description) {
        this.sujet = sujet;
        this.description = description;
    }

    public Reclamation(int id, String sujet, String description, LocalDate date) {
        this.id = id;
        this.sujet = sujet;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getSujet() {
        return sujet;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", sujet=" + sujet + ", description=" + description + ", date=" + date + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id;
        hash = 11 * hash + Objects.hashCode(this.sujet);
        hash = 11 * hash + Objects.hashCode(this.description);
        hash = 11 * hash + Objects.hashCode(this.date);
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
        final Reclamation other = (Reclamation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.sujet, other.sujet)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    String getReclamation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
