/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;
import edu.esprit.entity.Product;
/**
 *
 * @author HP
 */
import java.util.ArrayList;

public class Panier {
    private ArrayList<Product> panier;

    public Panier() {
        panier = new ArrayList<>();
    }

    public void ajouter(Product objet) {
        panier.add(objet);
    }

    public void supprimer(Product objet) {
        panier.remove(objet);
    }

    public ArrayList<Product> getPanier() {
        return panier;
    }
}