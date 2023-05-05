/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entity;

import edu.esprit.dao.CmdDao;
import edu.esprit.dao.ProductDao;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author asus
 */
public class ProductCmd {
  private SimpleObjectProperty<Commande> commande;
    private SimpleObjectProperty<Product> product;

    public ProductCmd(Commande commande, Product product, int par) {
         this.commande = new SimpleObjectProperty<>(commande);
        this.product = new SimpleObjectProperty<>(product);
    }
public ProductCmd(Commande commande, Product product) {
         this.commande = new SimpleObjectProperty<>(commande);
        this.product = new SimpleObjectProperty<>(product);
    }
public ProductCmd(int productId, int commandeId) {
    Product produit = ProductDao.getInstance().find(productId);
    Commande commande = CmdDao.getInstance().find(commandeId);
    this.product = new SimpleObjectProperty<>(produit);
    this.commande = new SimpleObjectProperty<>(commande);
}

public ProductCmd(Product product,Commande commande) {
         this.commande = new SimpleObjectProperty<>(commande);
        this.product = new SimpleObjectProperty<>(product);
    }

    public ProductCmd() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     public Commande getCommande() {
        return commande.get();
    }
    
    public void setCommande(Commande commande) {
        this.commande.set(commande);
    }
    
    public Product getProduct() {
        return product.get();
    }
    
    public void setProduct(Product product) {
        this.product.set(product);
    }
     public static ObservableList<ProductCmd> getCommandeProducts() {
        ObservableList<ProductCmd> commandeProducts = FXCollections.observableArrayList();
        
        // Assume we have a list of Commandes and Products
        ObservableList<Commande> commandes = Commande.getCommandes();
        ObservableList<Product> products = Product.getProducts();
        
        // Create a many-to-many relationship between Commandes and Products
        for (Commande commande : commandes) {
            for (Product product : products) {
                commandeProducts.add(new ProductCmd(commande, product, 0));
            }
        } 
        
        return commandeProducts;
    }   
}
