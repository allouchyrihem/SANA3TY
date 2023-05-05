/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entity;

/**
 *
 * @author asus
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
public class CommandeProduit {
    private int idCommande;
    private int idProduit;
    private int quantite;
    // Ajoutez d'autres propriétés et méthodes nécessaires

    public CommandeProduit(int idCommande, int idProduit, int quantite) {
        this.idCommande = idCommande;
        this.idProduit = idProduit;
        this.quantite = quantite;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public int getIdProduct() {
        return idProduit;
    }
}
